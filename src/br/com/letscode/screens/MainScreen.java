package br.com.letscode.screens;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsoleUtil;

public class MainScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        ConsoleUtil.scrollScreen();
        System.out.println("Main Screen");

        return new Navigation(ScreensList.EXIT, null);
    }
}
