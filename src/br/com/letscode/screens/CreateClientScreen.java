package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class CreateClientScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Create Client Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
