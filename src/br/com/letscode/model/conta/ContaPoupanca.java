package br.com.letscode.model.conta;

import br.com.letscode.model.cliente.Cliente;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        super.rendimento = cliente.getRendimentoContaPoupanca();
    }

}
