package br.com.letscode.model;

public abstract class Cliente {
    private String nome;
    private Conta[] contas;



    public String getNome() {
        return nome;
    }
    public Conta getConta(int id) {
        return contas[id];
    }
    public Conta[] getContas() {
        return contas;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setContas(Conta[] contas) {
        this.contas = contas;
    }


    public abstract Conta abrirConta(TipoContaEnum tipo);

}
