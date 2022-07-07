package br.com.letscode.model.conta;

import java.math.BigDecimal;

import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.TipoClienteEnum;

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
