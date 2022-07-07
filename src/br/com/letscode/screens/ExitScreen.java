package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class ExitScreen implements ScreenInterface {
    private static void draw() {
        ConsoleUtil.clearScreen();
        ConsoleUtil.scrollScreen();

        System.out.print(ConsoleUtil.NEW_LINE
                + StringUtil.centralize("Muito obrigado por utilizar o Coffee Bank!!!",
                        SystemInterfaceUtil.DEFAULT_CONSOLE_WIDTH)
                + ConsoleUtil.NEW_LINE);

        System.out.print(ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.BLINK.getEscapeCode()
                + SystemInterfaceUtil.COFFEE_BANK_LOGO
                + ConsoleUtil.NEW_LINE
                + ConsoleUtil.Attribute.RESET.getEscapeCode() + ConsoleUtil.NEW_LINE);
    }

    public Navigation run(Scanner scanner, String[] args) {
        draw();

        return new Navigation(null, null);
    }
}