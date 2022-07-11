package br.com.letscode.model.conta;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePJ;

public abstract class Conta {
    protected static int qtdContas = 0;

    protected int codigoConta;
    protected Cliente cliente;
    protected BigDecimal saldo;
    protected BigDecimal rendimento = new BigDecimal(1);

    public Conta(Cliente cliente) {
        codigoConta = ++qtdContas;
        saldo = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
        this.cliente = cliente;
    }

    public int getCodigoConta() {
        return this.codigoConta;
    }

    public BigDecimal consultarSaldo() {
        return this.saldo;
    }

    private void cobrarTaxaOperacao(BigDecimal value) {
        // cobra 0.5% por cada saque e transf para PJs
        if (cliente instanceof ClientePJ) {
            this.saldo = saldo.subtract(BigDecimal.valueOf(0.005).multiply(value));
        }
    }

    public void sacar(BigDecimal quantia) throws InvalidCommandException {
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCommandException("Quantia inválida!");
        }
        if (saldo.compareTo(quantia) >= 0) {
            saldo = saldo.subtract(quantia);
        } else {
            throw new InvalidCommandException("Saldo insuficiente!");
        }

        this.cobrarTaxaOperacao(quantia);
    }

    public abstract void depositar(BigDecimal quantia) throws InvalidCommandException;

    public void transferir(Conta destinatario, BigDecimal quantia) throws InvalidCommandException {
        final int LESS_THAN = -1;
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCommandException("Quantia inválida!");
        }
        if (this.consultarSaldo().compareTo(quantia) == LESS_THAN) {
            throw new InvalidCommandException("Saldo insuficiente!");
        }

        saldo = saldo.subtract(quantia);
        destinatario.saldo = destinatario.saldo.add(quantia);

        this.cobrarTaxaOperacao(quantia);
    };

    public void efetuarRendimento() {
        this.saldo = this.saldo.multiply(rendimento);
    }

    public abstract String getTipoConta();
}
