package br.com.letscode.screens;

import java.io.IOException;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;

public class StartScreen implements ScreenInterface {
    private static final String COFFEE_BANK_LOGO = "  ______              ______    ______                            _______                       __       "
            + ConsoleUtil.NEW_LINE
            + " /      \\            /      \\  /      \\                          |       \\                     |  \\      "
            + ConsoleUtil.NEW_LINE
            + "|  $$$$$$\\  ______  |  $$$$$$\\|  $$$$$$\\ ______    ______        | $$$$$$$\\  ______   _______  | $$   __ "
            + ConsoleUtil.NEW_LINE
            + "| $$   \\$$ /      \\ | $$_  \\$$| $$_  \\$$/      \\  /      \\       | $$__/ $$ |      \\ |       \\ | $$  /  \\"
            + ConsoleUtil.NEW_LINE
            + "| $$      |  $$$$$$\\| $$ \\    | $$ \\   |  $$$$$$\\|  $$$$$$\\      | $$    $$  \\$$$$$$\\| $$$$$$$\\| $$_/  $$"
            + ConsoleUtil.NEW_LINE
            + "| $$   __ | $$  | $$| $$$$    | $$$$   | $$    $$| $$    $$      | $$$$$$$\\ /      $$| $$  | $$| $$   $$ "
            + ConsoleUtil.NEW_LINE
            + "| $$__/  \\| $$__/ $$| $$      | $$     | $$$$$$$$| $$$$$$$$      | $$__/ $$|  $$$$$$$| $$  | $$| $$$$$$\\ "
            + ConsoleUtil.NEW_LINE
            + " \\$$    $$ \\$$    $$| $$      | $$      \\$$     \\ \\$$     \\      | $$    $$ \\$$    $$| $$  | $$| $$  \\$$\\"
            + ConsoleUtil.NEW_LINE
            + "  \\$$$$$$   \\$$$$$$  \\$$       \\$$       \\$$$$$$$  \\$$$$$$$       \\$$$$$$$   \\$$$$$$$ \\$$   \\$$ \\$$   \\$$"
            + ConsoleUtil.NEW_LINE;

    private static final String WELCOME_STRING = " ____                            _           _                     "
            + ConsoleUtil.NEW_LINE
            + "| __ )  ___ _ __ ___      __   _(_)_ __   __| | ___     __ _  ___  " + ConsoleUtil.NEW_LINE
            + "|  _ \\ / _ \\ '_ ` _ \\ ____\\ \\ / / | '_ \\ / _` |/ _ \\   / _` |/ _ \\ " + ConsoleUtil.NEW_LINE
            + "| |_) |  __/ | | | | |_____\\ V /| | | | | (_| | (_) | | (_| | (_) |" + ConsoleUtil.NEW_LINE
            + "|____/ \\___|_| |_| |_|      \\_/ |_|_| |_|\\__,_|\\___/   \\__,_|\\___/ " + ConsoleUtil.NEW_LINE;

    public Navigation run(String[] args) {
        ConsoleUtil.scrollScreen();

        ConsoleUtil.slowPrint(StringUtil.AddBlankSpacesToAllLines(WELCOME_STRING, 18) + ConsoleUtil.NEW_LINE, 5000);

        ConsoleUtil.slowPrint(ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.BLINK.getEscapeCode() + COFFEE_BANK_LOGO + ConsoleUtil.NEW_LINE
                + ConsoleUtil.Attribute.RESET.getEscapeCode() + ConsoleUtil.NEW_LINE, 5000);

        ConsoleUtil.slowPrint(StringUtil.BlankSpaces(40) + "Tecle ENTER para continuar");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConsoleUtil.clearScreen();
        return new Navigation(ScreensList.MAIN, null);
    }
}
