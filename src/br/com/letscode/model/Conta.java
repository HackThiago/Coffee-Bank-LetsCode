package br.com.letscode.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta {
    protected static int qtdContas = 0;

    protected int codigoConta;
    protected BigDecimal saldo;

    public Conta() {
        codigoConta = ++qtdContas;
        saldo = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
    }

    
    public BigDecimal consultarSaldo() {
        return this.saldo;
    }

    public void sacar(BigDecimal quantia) {
        if (saldo.compareTo(quantia) >= 0){
            saldo = saldo.subtract(quantia);
        }else{
            throw new Error("Saldo Insuficiente");
        }

    }


}
