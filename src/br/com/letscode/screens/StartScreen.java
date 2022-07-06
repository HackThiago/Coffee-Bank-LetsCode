package br.com.letscode.screens;

import java.io.IOException;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;

public class StartScreen implements ScreenInterface {
    private static final String COFFEE_BANK_LOGO = "  ______              ______    ______                            _______                       __       \n"
            + " /      \\            /      \\  /      \\                          |       \\                     |  \\      \n"
            + "|  $$$$$$\\  ______  |  $$$$$$\\|  $$$$$$\\ ______    ______        | $$$$$$$\\  ______   _______  | $$   __ \n"
            + "| $$   \\$$ /      \\ | $$_  \\$$| $$_  \\$$/      \\  /      \\       | $$__/ $$ |      \\ |       \\ | $$  /  \\\n"
            + "| $$      |  $$$$$$\\| $$ \\    | $$ \\   |  $$$$$$\\|  $$$$$$\\      | $$    $$  \\$$$$$$\\| $$$$$$$\\| $$_/  $$\n"
            + "| $$   __ | $$  | $$| $$$$    | $$$$   | $$    $$| $$    $$      | $$$$$$$\\ /      $$| $$  | $$| $$   $$ \n"
            + "| $$__/  \\| $$__/ $$| $$      | $$     | $$$$$$$$| $$$$$$$$      | $$__/ $$|  $$$$$$$| $$  | $$| $$$$$$\\ \n"
            + " \\$$    $$ \\$$    $$| $$      | $$      \\$$     \\ \\$$     \\      | $$    $$ \\$$    $$| $$  | $$| $$  \\$$\\\n"
            + "  \\$$$$$$   \\$$$$$$  \\$$       \\$$       \\$$$$$$$  \\$$$$$$$       \\$$$$$$$   \\$$$$$$$ \\$$   \\$$ \\$$   \\$$\n";

    private static final String WELCOME_STRING = " ____                            _           _                     \n"
            + "| __ )  ___ _ __ ___      __   _(_)_ __   __| | ___     __ _  ___  \n"
            + "|  _ \\ / _ \\ '_ ` _ \\ ____\\ \\ / / | '_ \\ / _` |/ _ \\   / _` |/ _ \\ \n"
            + "| |_) |  __/ | | | | |_____\\ V /| | | | | (_| | (_) | | (_| | (_) |\n"
            + "|____/ \\___|_| |_| |_|      \\_/ |_|_| |_|\\__,_|\\___/   \\__,_|\\___/ \n";

    public Navigation run(String[] args) {
        ConsoleUtil.scrollScreen();

        ConsoleUtil.slowPrint(StringUtil.AddBlankSpacesToAllLines(WELCOME_STRING, 18) + "\n", 5000);

        ConsoleUtil.slowPrint(ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.BLINK.getEscapeCode() + COFFEE_BANK_LOGO + "\n"
                + ConsoleUtil.Attribute.RESET.getEscapeCode() + "\n", 5000);

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
