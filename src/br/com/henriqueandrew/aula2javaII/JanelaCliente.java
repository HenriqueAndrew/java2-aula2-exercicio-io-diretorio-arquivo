package br.com.henriqueandrew.aula2javaII;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class JanelaCliente{

	private JFrame janela;
	int width = 800;
	int height = 500;

	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem abrirCadastro;
	private JMenuItem novoCadastro;
	private JMenuItem sair;

	private JPanel leftPanel;
	private JButton jBtnAnterior;
	private JButton jBtnProximo;
	private JButton jBtnnovoCliente;
	private JButton jBtngravarCliente;
	private JButton jBtneditarCliente;
	private JButton jBtnApagarCliente;

	private JPanel rightPanel;
	private JLabel info;
        
	private JPanel bottontPanel;
	private JLabel statusLabel;

    private JTextField jTFNome;
    private JTextField jTFFone;
    private JSpinner jSIdade;

    private ManipulaTexto mt;	

	public JanelaCliente() {

		mt = new ManipulaTexto(this);

		Componentes();

		addListeners();	
	}

	private void Componentes() {

		janela = new JFrame("Cadastro Clientes");
		janela.setSize(width, height);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

		menuBar = new JMenuBar();
		janela.add(menuBar, BorderLayout.NORTH);
		
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		abrirCadastro = new JMenuItem ("Abrir Cadastro");
		novoCadastro = new JMenuItem ("Novo Cadastro");
		sair = new JMenuItem ("Sair");
		menuArquivo.add(abrirCadastro);
		menuArquivo.add(novoCadastro);
		menuArquivo.add(new JSeparator());
		menuArquivo.add(sair);

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(10,1));
		Border padding =  BorderFactory.createEmptyBorder(5, 5, 5, 5);
		leftPanel.setBorder(padding);
		janela.add(leftPanel, BorderLayout.WEST);
		
		jBtnAnterior = new JButton ("<<< Anterior");
		jBtnProximo = new JButton("Proximo >>>");
		jBtnnovoCliente = new JButton("Novo Cliente");
		jBtngravarCliente = new JButton("Gravar Cliente");
		jBtneditarCliente = new JButton("Editar Cliente");
		jBtnApagarCliente = new JButton("Apagar Cliente");
		
		leftPanel.add(new JLabel("Navegação"));
		leftPanel.add(jBtnAnterior);
		leftPanel.add(jBtnProximo);
		leftPanel.add(new JLabel("Edição"));
		leftPanel.add(jBtnnovoCliente);
		leftPanel.add(jBtneditarCliente);
		leftPanel.add(jBtnApagarCliente);
		leftPanel.add(new JLabel(""));		
		leftPanel.add(jBtngravarCliente);

		jBtnAnterior.setEnabled(false);
		jBtnProximo.setEnabled(false);
		jBtnnovoCliente.setEnabled(false);
		jBtngravarCliente.setEnabled(false);
		jBtneditarCliente.setEnabled(false);
		jBtnApagarCliente.setEnabled(false);

		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(10,1));
                Border padding2 = BorderFactory.createEmptyBorder(20, 20, 5, 20);
                rightPanel.setBorder(padding2);
		rightPanel.setBackground(Color.lightGray);
		janela.add(rightPanel, BorderLayout.CENTER);

		bottontPanel = new JPanel();
		janela.add(bottontPanel, BorderLayout.SOUTH);
		statusLabel = new JLabel ("Clique em arquivo para abrir ou criar cadastro");
		bottontPanel.add(statusLabel);

		JPanel clienteLine = new JPanel();
		clienteLine.setBackground(Color.lightGray);
		clienteLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel clienteLabel = new JLabel ("Cliente");
		jTFNome = new JTextField(50);
		clienteLine.add(clienteLabel);
		clienteLine.add(jTFNome);
		rightPanel.add(clienteLine);
		
		JPanel foneLine = new JPanel();
		foneLine.setBackground(Color.lightGray);
		foneLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel foneLabel = new JLabel ("Fone");
		jTFFone = new JTextField(15);
		foneLine.add(foneLabel);
		foneLine.add(jTFFone);
		rightPanel.add(foneLine);

		JPanel idadeLine = new JPanel();
		idadeLine.setBackground(Color.lightGray);
		idadeLine.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel idadeLabel = new JLabel ("Idade");
		jSIdade = new JSpinner();
		idadeLine.add(idadeLabel);
		idadeLine.add(jSIdade);
		rightPanel.add(idadeLine);

		info = new JLabel(mt.informacaoCadastro());
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		infoPanel.setBackground(Color.GRAY);
		infoPanel.add(info);
		rightPanel.add(infoPanel);
		
		ativarFormularios(false);
	}

	private void addListeners() {

		//Cliente Anterior		
		jBtnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               //System.out.println("jBtnAnterior");
               jBtnAnteriorActionPerformed(evt);
            }
        });
		
		//Cliente Proximo		
		jBtnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               //System.out.println("jBtnProximo");
               jBtnProximoActionPerformed(evt);
            }
        });
		
		//Novo Cliente
		jBtnnovoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               System.out.println("jBtnnovoCliente");
               jBtnNovoActionPerformed(evt);
            }
        });
		
		//Gravar Cliente		
		jBtngravarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               System.out.println("jBtngravarCliente");
               jBtnGravarActionPerformed(evt);
            }
        });
		
		//Editar Cliente
		jBtneditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               System.out.println("jBtneditarCliente");
               jBtnEditarActionPerformed(evt);
            }
        });

		//Apagar Cliente
		jBtnApagarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               System.out.println("jBtnApagarCliente");
               jBtnApagarActionPerformed(evt);
            }
        });
		
		//Abrir Arquivo
		abrirCadastro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("abrirArquivo");
				jBtnAbrirArquivoActionPerformed(evt);
			}
		 });
		
		//Salvar Arquivo
		novoCadastro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("salvarArquivo");
				jBtnSalvarArquivoActionPerformed(evt);
			}
		 });
		
		//Sair
		sair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("sair");
				jBtnSairActionPerformed(evt);
			}
		 });
	}

	protected void jBtnSairActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	protected void jBtnSalvarArquivoActionPerformed(ActionEvent evt) {
		if (mt.criarArquivo()) {
			jBtnnovoCliente.setEnabled(true);
			jBtngravarCliente.setEnabled(true);
			
			atualizaDados();
		};
	}

	protected void jBtnAbrirArquivoActionPerformed(ActionEvent evt) {
		if (mt.abrirArquivo()) {
			atualizaDados();
			jBtnAnterior.setEnabled(true);
			jBtnProximo.setEnabled(true);
			jBtnnovoCliente.setEnabled(true);
			jBtngravarCliente.setEnabled(false);
			jBtneditarCliente.setEnabled(true);
			jBtnApagarCliente.setEnabled(true);
		}
	}

	protected void jBtnApagarActionPerformed(ActionEvent evt) {
		mt.apagarCliente(mt.clienteAtual());
		mt.salvarArquivo();
		atualizaDados();
	}

	protected void jBtnEditarActionPerformed(ActionEvent evt) {
		jTFNome.requestFocus();
		ativarFormularios(true);
		jBtngravarCliente.setEnabled(true);
	}

	public void desabilitarBotaoGravar() {
		jBtngravarCliente.setEnabled(false);
	}

	protected void jBtnGravarActionPerformed(ActionEvent evt) {
		Cliente c = new Cliente (jTFNome.getText(),jTFFone.getText(), (int)jSIdade.getValue());
		mt.gravarCadastro(c);
		ativarFormularios(false);
	}

	protected void jBtnNovoActionPerformed(ActionEvent evt) {
		//
		mt.novoCliente();
		ativarFormularios(true);
		jBtngravarCliente.setEnabled(true);
		jTFNome.setText("");
		jTFNome.requestFocus();
		jTFFone.setText("");
		jSIdade.setValue(0);
	}

	protected void jBtnProximoActionPerformed(ActionEvent evt) {
		if (mt.clienteProximo()) {
			atualizaDados();
		}
	}

	protected void jBtnAnteriorActionPerformed(ActionEvent evt) {
		if (mt.clienteAnterior()) {
			atualizaDados();
		}
	}

	public void atualizaDados() {
		if (mt.clienteAtual() != null) {
			Cliente c = mt.clienteAtual();
			jTFNome.setText(c.getNome());
			jTFFone.setText(c.getFone());
			jSIdade.setValue(c.getIdade());
			info.setText(mt.informacaoCadastro());
			statusLabel.setText(mt.selecionarArquivo());			
		}else {
			jTFNome.setText("");
			jTFFone.setText("");
			jSIdade.setValue(0);
			info.setText(mt.informacaoCadastro());
			statusLabel.setText(mt.selecionarArquivo());		
		}		
		
		if (mt.posicaoCadastro() >= 0) {
			jBtnAnterior.setEnabled(true);
			jBtnProximo.setEnabled(true);
			jBtneditarCliente.setEnabled(true);
			jBtnApagarCliente.setEnabled(true);
		}
	}

	private void ativarFormularios(boolean editable) {
		jTFNome.setEditable(editable);
		jTFFone.setEditable(editable);
		jSIdade.setEnabled(editable);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JanelaCliente();
			}
		});
	}
}