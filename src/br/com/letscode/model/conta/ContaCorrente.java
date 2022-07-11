package br.com.letscode.model.conta;

import java.math.BigDecimal;

import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.cliente.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void depositar(BigDecimal quantia) throws InvalidCommandException {
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCommandException("Quantia inválida!");
        }
        this.saldo = this.saldo.add(quantia);
    };

    @Override
    public String getTipoConta() {
        return "CORRENTE";
    }

}
