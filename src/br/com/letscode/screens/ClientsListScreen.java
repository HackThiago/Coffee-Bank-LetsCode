package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;

public class ClientsListScreen implements ScreenInterface {
    public Navigation run(Scanner scanner, String[] args) {
        System.out.println("Clients List Screen");

        return new Navigation(ScreensList.MAIN, args);
    }
}
