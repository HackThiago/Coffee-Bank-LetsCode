package br.com.letscode.database;

import java.util.ArrayList;
import br.com.letscode.model.ClienteInterface;

public class ClienteDAO {
    // Mock a database acces
    //  to use it: ClienteDAO.getListaCliente();

    private static ArrayList<ClienteInterface> listaCliente = new ArrayList<ClienteInterface>();

    public static ArrayList<ClienteInterface> getListaCliente(){
        return listaCliente;
    }

    public static void createCliente(ClienteInterface newCliente){
        listaCliente.add(newCliente);
    }

    public static void deleteCliente(ClienteInterface newCliente){
        listaCliente.remove(newCliente); // Don't know if it works
    }
}
