package br.com.letscode.model;

public class ClientePJ extends Cliente {
    int cnpj;

    
    public int getCnpj() {
        return cnpj;
    }


    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }


    @Override
    public Conta abrirConta(TipoContaEnum tipo) {
        // TODO Auto-generated method stub
        return null;
    }


}
