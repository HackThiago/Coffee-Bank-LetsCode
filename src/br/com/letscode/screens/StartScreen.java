package br.com.letscode.screens;

import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class StartScreen implements ScreenInterface {
    public Navigation run(Scanner scanner, String[] args) {
        ConsoleUtil.scrollScreen();

        System.out.print(
                StringUtil.addBlankSpacesToAllLines(SystemInterfaceUtil.WELCOME_STRING, 18) + ConsoleUtil.NEW_LINE);

        System.out.print(ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.BLINK.getEscapeCode()
                + SystemInterfaceUtil.COFFEE_BANK_LOGO
                + ConsoleUtil.NEW_LINE
                + ConsoleUtil.Attribute.RESET.getEscapeCode() + ConsoleUtil.NEW_LINE);

        System.out.print(StringUtil.centralize("Tecle ENTER para continuar", SystemInterfaceUtil.DEFAULT_CONSOLE_WIDTH)
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("Digite \"\\back\" nas telas para retornar a tela anterior",
                        SystemInterfaceUtil.DEFAULT_CONSOLE_WIDTH)
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("Digite \"\\exit\" nas telas para encerrar o programa",
                        SystemInterfaceUtil.DEFAULT_CONSOLE_WIDTH));

        System.out.print(ConsoleUtil.Attribute.FCOL_BLACK.getEscapeCode());
        ConsolePosition consolePos = ConsoleUtil.getConsoleSize();
        System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

        try {
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            ConsoleUtil.clearScreen();
            return new Navigation(ScreensList.EXIT, args);
        }

        args = new String[2];
        args[0] = String.valueOf(consolePos.getRow());
        args[1] = String.valueOf(consolePos.getColumn());

        ConsoleUtil.clearScreen();
        return new Navigation(ScreensList.MAIN, args);
    }
}
