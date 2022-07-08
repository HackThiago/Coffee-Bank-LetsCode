package br.com.letscode.model.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.TipoContaEnum;

public abstract class Cliente {
    private String id = "";
    private static int idSequence = 1;
    private String nome;
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public String getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id.length() == 0) {
            this.id = String.valueOf(id);
        }
    }

    public static int nextId() {
        return idSequence++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta(int codigoConta) {
        return contas.stream().filter(conta -> conta.getCodigoConta() == codigoConta)
                .findFirst().orElse(null);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    protected void addConta(Conta conta) {
        this.contas.add(conta);
    }

    public BigDecimal getRendimentoContaPoupanca() {
        return new BigDecimal(1);
    }

    public BigDecimal getRendimentoContaInvestimento() {
        return new BigDecimal(1);
    }

    public abstract Conta abrirConta(TipoContaEnum tipo);

    public abstract String getDocument();

    public abstract void setDocument(String document);
}
