package br.com.henriqueandrew.aula2javaII;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ManipulaTexto {
	
	private File arquivo;
	private JanelaCliente interf;

	private ArrayList<Cliente> cc;
	private Cliente c;

	public ManipulaTexto(JanelaCliente interf) {
		arquivo = null;
		cc=null;
		c=null;
		this.interf = interf;
	}

	public boolean abrirArquivo() {
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showSaveDialog(null);//posiciona a janela no centro da tela

		if (result == JFileChooser.CANCEL_OPTION) {
			return false;
		}
		
		arquivo = fileChooser.getSelectedFile();
		System.out.println(fileChooser.getName());

		if (arquivo == null || arquivo.getName() == "") {	
			JOptionPane.showMessageDialog(null, "Nome de Arquivo Inválido", 
					"Nome de Arquivo Inválido", JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else {

			try { 	
				cc = (ArrayList<Cliente>) lerArquivo(arquivo);
				c = cc.get(0);
				interf.atualizaDados();
				return true;
			
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false; 
	}
	
	public static void salvarCliente(Object o, File file) throws IOException {
		
		ObjectOutputStream objectOutput = null;
		FileOutputStream fileOutput = null;
		
		try {
			
			if(file.exists() && file.canWrite()) {
				
				System.out.println("salvar em:"+file.getAbsolutePath());

				fileOutput = new FileOutputStream(file);
				objectOutput = new ObjectOutputStream(fileOutput);

				objectOutput.writeObject(o);

				objectOutput.close();
				fileOutput.close();
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Cadastro gravado!");
	}	

	private static Object lerArquivo(File file) throws IOException {
		
		ObjectInputStream objectInput = null;
		FileInputStream fileInput = null;
		Object obj=null;
		
		try {

			if (file.exists() && file.canRead()) {

				System.out.println("ler arquivo:"+file.getAbsolutePath());

				fileInput = new FileInputStream(file);
				objectInput = new ObjectInputStream(fileInput);

				obj = objectInput.readObject();

				fileInput.close();
				objectInput.close();
			}else {

				System.out.println("Arquivo inexistente ou impossível de ler");
			}
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo selecionado!");
		return obj;
	}	


	public boolean criarArquivo() {

    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.CANCEL_OPTION) {
            return false;
        }
        
        arquivo = fileChooser.getSelectedFile();
        System.out.println(fileChooser.getName());
    
        if (arquivo == null || arquivo.getName().equals("")) {
            JOptionPane.showMessageDialog(null, "Nome de Arquivo Inválido", "Nome de Arquivo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
 			
        } else {

            try {

            	if (arquivo.exists()) {

            		System.out.println("Arquivo existe: "+arquivo.getAbsolutePath());
            	}else {
 
            		System.out.println("Arquivo não existe: "+arquivo.getAbsolutePath());
            		arquivo.createNewFile();
            	}
            	
            	cc =  new ArrayList<Cliente>();
            	c = null;
            	interf.atualizaDados();
            	return true;
            	
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Erro ao Abrir Arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
	}

	public void imprimirCadastro() {
		for (Cliente i : cc) {
			System.out.println(i);
		}
	}

	public Cliente clienteAtual() {
		return c;
	}

	public boolean clienteProximo() {
		
		int index = cc.indexOf(c);//5
		
		if(index >= cc.size()-1) {//6
			System.out.println("ultimo");
			return false;
		}else {
			c = cc.get(index+1);
			System.out.println("próximo");
			return true;
		}
	}

	public boolean clienteAnterior() {
		
		int index = cc.indexOf(c);//5
		
		if(index <=0) {//6
			System.out.println("primeiro");
			return false;
		}else {
			c = cc.get(index-1);
			System.out.println("anterior");
			return true;
		}
	}

	public String selecionarArquivo() {
		if (arquivo != null) {
			return arquivo.getAbsolutePath();					
		}else {
			return "Nenhum Arquivo";
		}
	}

	public void salvarArquivo() {
		
		try {
			salvarCliente(cc, arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void novoCliente() {
		Cliente novo = new Cliente();
		cc.add(novo);
		c = novo;
		interf.atualizaDados();
	}

	public void apagarCliente(Cliente i) {

		int index = cc.indexOf(i);

		if (index == 0 && cc.size() ==1) {
			cc.remove(i);
			c=null;
			
		}else if (index == 0 && cc.size() > 1) {
			cc.remove(c);
			c = cc.get(0);
		
		}else if (index == cc.size()-1 && cc.size() > 1) {
			cc.remove(c);
			c = cc.get(cc.size()-1);
						
		}
		interf.atualizaDados();
	}

	public void gravarCadastro(Cliente i) {
		
		if(arquivo != null) {
			c.setNome(i.getNome());
			c.setFone(i.getFone());
			c.setIdade(i.getIdade());
			salvarArquivo();
			interf.atualizaDados();			
		}else {
			criarArquivo();
		}
		interf.desabilitarBotaoGravar();
	}

	public void editarCliente()	{
		gravarCadastro(c);
		salvarArquivo();
		interf.atualizaDados();	
	}

    public String informacaoCadastro(){
    	String info;
    	if (cc != null) {
    		info = "  Quantidade de Cadastros:"+cc.size()+
    				" [Atual:"+(cc.indexOf(c)+1)+"]"; 
    	}else {
    		info = "Nenhum Cadastro";
    	}
    	return info;
    }

    public int posicaoCadastro() {
		return cc.size();
	}
}