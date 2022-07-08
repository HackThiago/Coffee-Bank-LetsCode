package br.com.letscode.model.cliente;

import java.math.BigDecimal;

import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.ContaCorrente;
import br.com.letscode.model.conta.ContaInvestimento;
import br.com.letscode.model.conta.ContaPoupanca;
import br.com.letscode.model.conta.TipoContaEnum;

public class ClientePF extends Cliente {
    public static final BigDecimal RENDIMENTO_CONTA_INVESTIMENTO = new BigDecimal(1.01);
    public final BigDecimal RENDIMENTO_CONTA_POUPANCA = new BigDecimal(1.005);

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public BigDecimal getRendimentoContaPoupanca(){
        return RENDIMENTO_CONTA_POUPANCA;
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
            case POUPANCA:
                conta = new ContaPoupanca(this);
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento(this);
                break;
            default:
                throw new Error("Tipo de conta inválido");
        }

        // add conta to arraylist of contas
        this.addConta(conta);

        return conta;
    }


}
