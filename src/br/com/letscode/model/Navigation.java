package br.com.letscode.model;

import br.com.letscode.screens.ScreensList;

public class Navigation {
    private ScreensList screen;
    private String[] args;

    public Navigation(ScreensList screen, String[] args) {
        this.screen = screen;
        this.args = args;
    }

    public ScreensList getScreen() {
        return screen;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArg(int index) {
        return args[index];
    }
}
