package br.com.letscode.model.cliente;

import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.ContaCorrente;
import br.com.letscode.model.conta.ContaInvestimento;
import br.com.letscode.model.conta.ContaPoupanca;
import br.com.letscode.model.conta.TipoContaEnum;

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
                throw new Error("Tipo de conta inválido");
        }

        // add conta to arraylist of contas
        this.addConta(conta);

        return conta;
    }


}
