package br.com.letscode.model.conta;

import br.com.letscode.model.cliente.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String getTipoConta() {
        return "CORRENTE";
    }

}
