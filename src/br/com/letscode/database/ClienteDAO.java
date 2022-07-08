package br.com.letscode.database;

import java.util.ArrayList;
import java.util.List;

import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
import br.com.letscode.model.conta.TipoContaEnum;

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
