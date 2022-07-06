package br.com.letscode.model;

import java.math.BigDecimal;

public interface ContaPFInterface extends ContaInterface {
    public void depositar(BigDecimal quantia);

    public void transferir(ContaInterface destinatario, BigDecimal quantia);
}
