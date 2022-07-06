package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class AccountScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Account Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
