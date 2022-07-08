package br.com.letscode.model.cliente;

import java.math.BigDecimal;

import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.ContaCorrente;
import br.com.letscode.model.conta.ContaInvestimento;
import br.com.letscode.model.conta.TipoContaEnum;

public class ClientePJ extends Cliente {
    public static final BigDecimal RENDIMENTO_CONTA_INVESTIMENTO =
        ClientePF.RENDIMENTO_CONTA_INVESTIMENTO.add(new BigDecimal(0.02));

    String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public BigDecimal getRendimentoContaInvestimento(){
        return RENDIMENTO_CONTA_INVESTIMENTO;
    }

    @Override
    public Conta abrirConta(TipoContaEnum tipo) {
        
        Conta conta = null;
        
        switch (tipo) {
            case CORRENTE:
                conta = new ContaCorrente(this);
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento(this);
                break;
            case POUPANCA:
                throw new Error("Pessoa jurídica não pode abrir conta poupança");
            default:
                throw new Error("Tipo de conta inválido");
        }
        //set the conta id to the next sequence value
        this.setId(nextId());
        // add conta to arraylist of contas
        this.addConta(conta);

        return conta;
    }


}
