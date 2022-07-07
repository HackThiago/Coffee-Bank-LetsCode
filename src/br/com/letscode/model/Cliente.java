package br.com.letscode.model;

public abstract class Cliente {
    private String nome;
    private Conta[] contas;


    public String getNome() {
        return nome;
    } 
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Conta getConta(int id) {
        return contas[id];
    }
    public Conta[] getContas() {
        return contas;
    }

    public abstract Conta abrirConta(TipoContaEnum tipo);

}
