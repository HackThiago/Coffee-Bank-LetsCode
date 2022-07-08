package br.com.letscode.model.conta;

import java.math.BigDecimal;

import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;

public class ContaInvestimento extends Conta {
    public ContaInvestimento(Cliente cliente) {
        super(cliente);
        super.rendimento = cliente.getRendimentoContaInvestimento();
    }

    public void investir(BigDecimal quantia, Cliente cliente) {
        if (cliente instanceof ClientePF){
            saldo.add(quantia.multiply((BigDecimal.valueOf(0.01))));
        }
    }

    @Override
    public String getTipoConta() {
        return "INVESTIMENTO";
    }
}
