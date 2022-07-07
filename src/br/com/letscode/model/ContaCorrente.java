package br.com.letscode.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaCorrente extends Conta implements ContaPFInterface {


    public ContaCorrente() {
        codigoConta = ++qtdContas;
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

    @Override
    public void depositar(BigDecimal quantia) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferir(Conta destinatario, BigDecimal quantia) {
        // TODO Auto-generated method stub

    }

}
