package br.com.letscode.model.conta;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.letscode.model.cliente.TipoClienteEnum;

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

    public int getCodigoConta(){
        return this.codigoConta;
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
        final int LESS_THAN = -1;
        if(this.consultarSaldo().compareTo(quantia) == LESS_THAN){
            throw new Error("Saldo insuficiente");
        }
        
        saldo = saldo.subtract(quantia);
        destinatario.saldo.add(quantia);
    };
}