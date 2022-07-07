package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;

public class ClientScreen implements ScreenInterface {
    public Navigation run(Scanner scanner, String[] args) {
        System.out.println("Client Screen");

        return new Navigation(null, null);
    }
}