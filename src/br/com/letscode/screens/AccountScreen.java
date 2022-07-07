package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;

public class AccountScreen implements ScreenInterface {
    public Navigation run(Scanner scanner, String[] args) {
        System.out.println("Account Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
