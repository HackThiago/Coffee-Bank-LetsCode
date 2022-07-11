package br.com.letscode.model.conta;

import java.math.BigDecimal;

import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePJ;

public class ContaInvestimento extends Conta {
    public ContaInvestimento(Cliente cliente) {
        super(cliente);
        super.rendimento = cliente.getRendimentoContaInvestimento();
    }

    @Override
    public void depositar(BigDecimal quantia) throws InvalidCommandException {
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidCommandException("Quantia inválida!");
        }

        BigDecimal rendimento = BigDecimal.valueOf(1.01D);
        if (this.cliente instanceof ClientePJ) {
            rendimento = BigDecimal.valueOf(1.03D);
        }
        this.saldo = this.saldo.add(quantia.multiply(rendimento));
    };

    public void investir(BigDecimal quantia) throws InvalidCommandException {
        this.depositar(quantia);
    }

    @Override
    public String getTipoConta() {
        return "INVESTIMENTO";
    }
}
