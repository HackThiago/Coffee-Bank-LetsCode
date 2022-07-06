package br.com.letscode.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaInvestimento implements ContaInterface {
    private static int qtdContas = 0;

    private int codigoConta;
    private BigDecimal saldo;

    public ContaInvestimento() {
        codigoConta = qtdContas++;
        saldo = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
    }

    @Override
    public BigDecimal consultarSaldo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sacar(BigDecimal quantia) {
        // TODO Auto-generated method stub
    }

    public void investir(BigDecimal quantia) {
        // TODO Auto-generated method stub
    }

}
