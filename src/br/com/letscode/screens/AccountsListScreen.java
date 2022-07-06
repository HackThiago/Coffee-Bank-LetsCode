package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class AccountsListScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Accounts List Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
