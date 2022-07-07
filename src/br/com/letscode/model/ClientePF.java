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
        
        Conta conta = null;
        
        switch (tipo) {
            case CORRENTE:
                conta = new ContaCorrente();
                break;
            case POUPANCA:
                conta = new ContaPoupanca();
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento();
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
