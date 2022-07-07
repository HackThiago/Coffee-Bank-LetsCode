package br.com.letscode.model;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(TipoClienteEnum tipoCliente) {
        super(tipoCliente);
    }

    public void investir(BigDecimal quantia, Cliente cliente) {
        if (cliente instanceof ClientePF){
            saldo.add(quantia.multiply((BigDecimal.valueOf(0.01))));
        }
    }
}
