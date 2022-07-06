package br.com.letscode.model;

import java.math.BigDecimal;

public interface ContaInterface {
    public BigDecimal consultarSaldo();

    public void sacar(BigDecimal quantia);
}
