package br.com.letscode.model.conta;

import java.math.BigDecimal;

import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.cliente.Cliente;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        super.rendimento = cliente.getRendimentoContaPoupanca();
    }

    @Override
    public void depositar(BigDecimal quantia) throws InvalidCommandException {
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCommandException("Quantia inválida!");
        }
        this.saldo = this.saldo.add(quantia.multiply(BigDecimal.valueOf(1.005D)));
    };

    @Override
    public String getTipoConta() {
        return "POUPANÇA";
    }
}
