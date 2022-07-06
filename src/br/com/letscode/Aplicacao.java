package br.com.letscode;

import java.util.ArrayList;

import br.com.letscode.model.Navigation;
import br.com.letscode.screens.ExitScreen;
import br.com.letscode.screens.ScreenInterface;
import br.com.letscode.screens.ScreensList;
import br.com.letscode.util.ConsoleUtil;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        ConsoleUtil.clearScreen();

        Navigation navigate = new Navigation(ScreensList.START, null);
        ScreenInterface screen;

        while (navigate.getScreen() != ScreensList.EXIT) {
            screen = navigate.getScreen().createInstance();
            navigate = screen.run(navigate.getArgs());
        }
        screen = new ExitScreen();
        screen.run(navigate.getArgs());
    }
}
