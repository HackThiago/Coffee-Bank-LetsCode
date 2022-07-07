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
        
        Conta conta = null;
        
        switch (tipo) {
            case CORRENTE:
                conta = new ContaCorrente();
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento();
                break;
            case POUPANCA:
                // TODO throws error - PJ cannot open this type of account
                break;
            default:
                // TODO throws error - invalid type 
                break;
        }

        // add conta to arraylist of contas
        this.addConta(conta);

        return conta;
    }


}
