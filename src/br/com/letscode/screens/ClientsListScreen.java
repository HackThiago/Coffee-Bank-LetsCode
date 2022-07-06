package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class ClientsListScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Clients List Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
