package br.com.letscode.screens;

import br.com.letscode.model.Navigation;

public class ExitScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        System.out.println("Exit Screen");

        return new Navigation(null, null);
    }
}