package br.com.henriqueandrew.aula2javaII;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
    private String fone;
    private int idade;


    Cliente() {
        nome = "não informado";
        fone = "não informado";
        idade = 0;
    }

    public Cliente(String nm, String fon, int id) {
        nome = nm;
        fone = fon;
        idade = id;
    }


    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getFone() {
        return fone;
    }


    public void setFone(String fone) {
        this.fone = fone;
    }

 
    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean Maior() {
    	if (getIdade() >= 18) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Override
    public String toString() {
    	return "Cliente [nome=" + nome + ", fone=" + fone + ", idade=" + idade + "]";
    }
}