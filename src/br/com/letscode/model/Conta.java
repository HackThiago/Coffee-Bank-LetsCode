package br.com.letscode.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta {
    protected static int qtdContas = 0;

    protected int codigoConta;
    protected TipoClienteEnum tipoCliente;
    protected BigDecimal saldo;

    public Conta(TipoClienteEnum tipoCliente) {
        codigoConta = ++qtdContas;
        saldo = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
        this.tipoCliente = tipoCliente;
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

    public void depositar(BigDecimal quantia){
        this.saldo.add(quantia);
    };

    public void transferir(Conta destinatario, BigDecimal quantia){
        // TODO
    };
}
