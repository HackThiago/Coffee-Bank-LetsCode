package br.com.letscode.model;

public class ClientePF extends Cliente {
    private int cpf;

    
    public int getCpf() {
        return cpf;
    }


    public void setCpf(int cpf) {
        this.cpf = cpf;
    }


    @Override
    public Conta abrirConta(TipoContaEnum tipo) {
        // TODO Auto-generated method stub
        return null;
    }


}
