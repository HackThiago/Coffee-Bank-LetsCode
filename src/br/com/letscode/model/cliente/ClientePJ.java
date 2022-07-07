package br.com.letscode.model.cliente;

import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.ContaCorrente;
import br.com.letscode.model.conta.ContaInvestimento;
import br.com.letscode.model.conta.TipoContaEnum;

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
                conta = new ContaCorrente(TipoClienteEnum.PESSOA_JURIDICA);
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento(TipoClienteEnum.PESSOA_JURIDICA);
                break;
            case POUPANCA:
                throw new Error("Pessoa jurídica não pode abrir conta poupança");
            default:
                throw new Error("Tipo de conta inválido");
        }

        // add conta to arraylist of contas
        this.addConta(conta);

        return conta;
    }


}
