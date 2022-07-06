package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class CreateAccountScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Client Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
