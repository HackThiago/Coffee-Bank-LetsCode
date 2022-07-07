package br.com.letscode.model;

import java.math.BigDecimal;

public interface ContaPFInterface {
    public void depositar(BigDecimal quantia);

    public void transferir(Conta destinatario, BigDecimal quantia);
}
