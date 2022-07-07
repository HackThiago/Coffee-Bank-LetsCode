package br.com.letscode.model.conta;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;


/** TODO - add regras de taxas e rendimentos -- nova modelagem
 * Os clientes podem ser pessoa física ou jurídica,
 * sendo que para PJ existe a cobrança de uma taxa de 0.5%
 * para cada saque ou transferência (mudar isso na modelagem).
 * 
 * Além do produto conta-corrente,
 * os clientes PF podem abrir uma conta-poupança e conta-investimento.
 * 
 * Clientes PJ não abrem poupança,
 * mas seus rendimentos em conta-investimento rendem 2% a mais que a PF.
 * 
 * Conta-poupanca rende 0.5%
 * Conta-investimento PF rende 1%
 * Para fins didaticos,
 * o rendimento pode ser calculado quando ha investimento
 * 
 * Qualquer outro tipo de calculo de investimento sera valido para este exercicio.
 * (virada do dia, por exemplo)
 * */

public abstract class Conta {
    protected static int qtdContas = 0;

    protected int codigoConta;
    protected Cliente cliente;
    protected BigDecimal saldo;

    public Conta(Cliente cliente) {
        codigoConta = ++qtdContas;
        saldo = new BigDecimal(0).setScale(3, RoundingMode.FLOOR);
        this.cliente = cliente;
    }

    public int getCodigoConta(){
        return this.codigoConta;
    }
    
    public BigDecimal consultarSaldo() {
        return this.saldo;
    }

    private void cobraTaxaParaPJ(){
        // cobra 0.5% por cada saque e transf para PFs
        if(cliente instanceof ClientePF){
            saldo = saldo.multiply(BigDecimal.valueOf(0.995));
        }
    }

    public void sacar(BigDecimal quantia) {
        if (saldo.compareTo(quantia) >= 0){
            saldo = saldo.subtract(quantia);
        }else{
            throw new Error("Saldo Insuficiente");
        }

        this.cobraTaxaParaPJ();
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

        this.cobraTaxaParaPJ();
    };

    public void efetuaRendimento(){
        // TODO - método que será executado todo dia, a meia noite, para
        //   efetuar o rendimento em todas as contas
    }
}
