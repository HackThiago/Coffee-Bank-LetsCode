package br.com.letscode.database;

import java.util.ArrayList;

import br.com.letscode.model.cliente.Cliente;

public class ClienteDAO {
    // Mock a database acces
    //  to use it: ClienteDAO.getListaCliente();

    private static ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

    public static ArrayList<Cliente> getListaCliente(){
        return listaCliente;
    }

    public static void createCliente(Cliente newCliente){
        listaCliente.add(newCliente);
    }

    public static void deleteCliente(Cliente newCliente){
        listaCliente.remove(newCliente); // Don't know if it works
    }
}
