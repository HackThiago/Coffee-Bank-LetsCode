package br.com.letscode.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.letscode.error.ClientNotFoundException;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
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

    public static void generateMockDatabase(int quantity) {
        Random rand = new Random();
        for (int i = 0; i < quantity; i++) {
            Cliente client;
            if (rand.nextInt(2) == 0) {
                client = new ClientePJ();
                client.setDocument(String.valueOf(rand.nextLong(10000000000000L, 99999999999999L)));
                client.setNome("Cliente Pessoa Jurídica " + (i + 1));
            } else {
                client = new ClientePF();
                client.setDocument(String.valueOf(rand.nextLong(10000000000L, 99999999999L)));
                client.setNome("Cliente Pessoa Física " + (i + 1));
            }
            client.setId(Cliente.nextId());
            createCliente(client);
        }
    }

    public static List<Cliente> getMockClientList() {
        List<Cliente> clients = new ArrayList<Cliente>();

        ClientePF cliente1 = new ClientePF();
        cliente1.setNome("Teste Cliente PF");
        cliente1.setCpf("11111111111");

        ClientePJ cliente2 = new ClientePJ();
        cliente2.setNome("Teste Cliente PJ");
        cliente2.setCnpj("22222222222222");

        clients.add(cliente1);
        clients.add(cliente2);

        return clients;
    }

    public static Cliente getMockClient() {
        ClientePF cliente = new ClientePF();
        cliente.setNome("Teste Cliente PF");
        cliente.setCpf("11111111111");

        cliente.abrirConta(TipoContaEnum.CORRENTE);

        return cliente;
    }

    public static Cliente getMockClient2() {
        ClientePJ cliente = new ClientePJ();
        cliente.setNome("Teste Cliente PJ");
        cliente.setCnpj("22222222222222");

        cliente.abrirConta(TipoContaEnum.INVESTIMENTO);

        return cliente;
    }
}
