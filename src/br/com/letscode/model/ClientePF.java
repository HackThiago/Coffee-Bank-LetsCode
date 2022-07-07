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
                conta = new ContaCorrente(TipoClienteEnum.PESSOA_FISICA);
                break;
            case POUPANCA:
                conta = new ContaPoupanca(TipoClienteEnum.PESSOA_FISICA);
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento(TipoClienteEnum.PESSOA_FISICA);
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
