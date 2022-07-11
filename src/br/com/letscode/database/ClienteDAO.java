package br.com.letscode.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import br.com.letscode.error.ClientNotFoundException;
import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.TipoContaEnum;
import br.com.letscode.util.ArrayListUtil;

public class ClienteDAO {
    // Mock a database acces
    // to use it: ClienteDAO.getListaCliente();

    private static ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

    public static ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public static void createCliente(Cliente newCliente) {
        listaCliente.add(newCliente);
    }

    public static Cliente getClienteById(String id) {
        return listaCliente.stream().filter(cliente -> cliente.getId() == id)
                .findFirst().orElse(null);
    }

    public static Conta getContaById(int id) {
        for (Cliente cliente : listaCliente) {
            Conta conta = cliente.getConta(id);
            if (conta != null) {
                return conta;
            }
        }
        return null;
    }

    public static ClientePF getClienteByCPF(String cpf) throws ClientNotFoundException {
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente cliente = listaCliente.get(i);
            if (cliente instanceof ClientePF) {
                ClientePF clientePf = (ClientePF) cliente;
                if (clientePf.getCpf().equals(cpf)) {
                    return clientePf;
                }
            }
        }
        throw new ClientNotFoundException("Cliente com esse CPF não encontrado");
    }

    public static ClientePJ getClienteByCNPJ(String cnpj) throws ClientNotFoundException {
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente cliente = listaCliente.get(i);
            if (cliente instanceof ClientePJ) {
                ClientePJ clientePj = (ClientePJ) cliente;
                if (clientePj.getCnpj().equals(cnpj)) {
                    return clientePj;
                }
            }
        }
        throw new ClientNotFoundException("Cliente com este CNPJ não encontrado");
    }

    public static ArrayList<Cliente> getClientesByNome(String nome) {
        return listaCliente.stream().filter(cliente -> cliente.getNome() == nome)
                .collect(ArrayListUtil.toArrayList());
    }

    public static void deleteCliente(Cliente newCliente) {
        listaCliente.remove(newCliente); // Don't know if it works
    }

    public static boolean existCliente(String document) {
        for (Cliente c : listaCliente) {
            if (c.getDocument().equals(document)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existCliente(Cliente cliente) {
        return existCliente(cliente.getDocument());
    }

    public static void generateMockDatabase(int clientsQuantity, int maxAccountsPerClient,
            BigDecimal maxAccountBalance) {
        final long minBoundCpf = 10000000000L;
        final long maxBoundCpf = 99999999999L;
        final long minBoundCnpj = 10000000000000L;
        final long maxBoundCnpj = 99999999999999L;
        Random rand = new Random();
        for (int i = 0; i < clientsQuantity; i++) {
            Cliente client;
            if (rand.nextInt(2) == 0) {
                client = new ClientePJ();
                long generatedCnpj = minBoundCnpj + (long) (Math.random() * (maxBoundCnpj - minBoundCnpj));
                client.setDocument(String.valueOf(generatedCnpj));
                client.setNome("Cliente Pessoa Jurídica " + (i + 1));
            } else {
                client = new ClientePF();
                long generatedCpf = minBoundCpf + (long) (Math.random() * (maxBoundCpf - minBoundCpf));
                client.setDocument(String.valueOf(generatedCpf));
                client.setNome("Cliente Pessoa Física " + (i + 1));
            }
            client.setId(Cliente.nextId());
            createCliente(client);

            for (int j = 0; j < maxAccountsPerClient; j++) {
                TipoContaEnum accountType = null;
                switch (rand.nextInt(client instanceof ClientePF ? 4 : 3)) {
                    case 1:
                        accountType = TipoContaEnum.CORRENTE;
                        break;
                    case 2:
                        accountType = TipoContaEnum.INVESTIMENTO;
                        break;
                    case 3:
                        accountType = TipoContaEnum.POUPANCA;
                        break;
                    default:
                        continue;
                }

                Conta account = null;
                try {
                    account = client.abrirConta(accountType);
                } catch (InvalidCommandException e) {
                    // do nothing
                }

                try {
                    account.depositar(maxAccountBalance.multiply(BigDecimal.valueOf(rand.nextDouble())));
                } catch (InvalidCommandException e) {
                    // do nothing
                }
            }
        }
    }

}
