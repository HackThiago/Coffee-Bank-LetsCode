package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;

public interface ScreenInterface {
    public Navigation run(Scanner scanner, String[] args);
}
