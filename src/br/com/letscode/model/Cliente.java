package br.com.letscode.model;

import java.util.ArrayList;

public abstract class Cliente {
    private String nome;
    private ArrayList<Conta> contas;


    public String getNome() {
        return nome;
    } 
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Conta getConta(int codigoConta) {
        return contas.stream().filter(conta -> conta.codigoConta == codigoConta).findFirst().orElse(null);
    }
    public ArrayList<Conta> getContas() {
        return contas;
    }

    protected void addConta(Conta conta){
        this.contas.add(conta);
    }

    public abstract Conta abrirConta(TipoContaEnum tipo);

}
