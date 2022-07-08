package br.com.letscode.util;

import java.util.Scanner;

import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.model.ConsolePosition;
import br.com.letscode.model.Message;

public class SystemInterfaceUtil {
    public static final int DEFAULT_CONSOLE_WIDTH = 108;

    public static final String COFFEE_BANK_LOGO = "  ______              ______    ______                            _______                       __       "
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

    public static final String WELCOME_STRING = " ____                            _           _                     "
            + ConsoleUtil.NEW_LINE
            + "| __ )  ___ _ __ ___      __   _(_)_ __   __| | ___     __ _  ___  " + ConsoleUtil.NEW_LINE
            + "|  _ \\ / _ \\ '_ ` _ \\ ____\\ \\ / / | '_ \\ / _` |/ _ \\   / _` |/ _ \\ " + ConsoleUtil.NEW_LINE
            + "| |_) |  __/ | | | | |_____\\ V /| | | | | (_| | (_) | | (_| | (_) |" + ConsoleUtil.NEW_LINE
            + "|____/ \\___|_| |_| |_|      \\_/ |_|_| |_|\\__,_|\\___/   \\__,_|\\___/ " + ConsoleUtil.NEW_LINE;

    public static String getHeader(String screenName, ConsolePosition pos) {
        final String HEADER_START_TEXT = "Coffee Bank";
        final String time = TimeUtil.now();
        final int consoleMiddleRow = pos.getColumn() / 2;
        final int screenNameStartPadding = consoleMiddleRow - HEADER_START_TEXT.length() - (screenName.length() / 2);
        final int dateStartPadding = pos.getColumn() - screenNameStartPadding - time.length() - screenName.length()
                - HEADER_START_TEXT.length();

        return ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                + HEADER_START_TEXT
                + StringUtil.blankSpaces(screenNameStartPadding)
                + screenName
                + StringUtil.blankSpaces(dateStartPadding)
                + time
                + ConsoleUtil.Attribute.RESET.getEscapeCode()
                + ConsoleUtil.NEW_LINE;
    }

    public static String getMessage(Message message, int consoleWidth) {
        String format = ConsoleUtil.Attribute.BRIGHT.getEscapeCode();

        String formatBGColor;
        switch (message.getType()) {
            case SUCCESS:
                formatBGColor = ConsoleUtil.Attribute.FCOL_GREEN.getEscapeCode();
                break;
            case ERROR:
                formatBGColor = ConsoleUtil.Attribute.FCOL_RED.getEscapeCode();
                break;
            case WARNING:
                formatBGColor = ConsoleUtil.Attribute.FCOL_YELLOW.getEscapeCode();
                break;
            case INFO:
                formatBGColor = ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode();
                break;
            default:
                formatBGColor = ConsoleUtil.Attribute.REVERSE.getEscapeCode();
                break;
        }
        format = format.concat(formatBGColor);

        return format
                + ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                + StringUtil.centralize(message.getText(), consoleWidth)
                + StringUtil.blankSpaces((consoleWidth / 2) - (message.getText().length() / 2))
                + ConsoleUtil.Attribute.RESET.getEscapeCode()
                + ConsoleUtil.NEW_LINE;
    }

    public static void drawInfoScreen(String screenName, Message message, String content, ConsolePosition consoleSize) {
        ConsoleUtil.scrollScreen();

        System.out.print(SystemInterfaceUtil.getHeader(screenName, consoleSize));

        ConsoleUtil.skipLines(1);
        if (message.getText().length() > 0) {
            System.out.print(getMessage(message, consoleSize.getColumn()));
        } else {
            ConsoleUtil.skipLines(1);
        }

        ConsoleUtil.skipLines(1);

        System.out.print(content + ConsoleUtil.NEW_LINE);
        ConsoleUtil.skipLines(3);
    }

    public static String getUserInput(Scanner scanner, ConsolePosition consoleSize, String message)
            throws ExitSignalException, GoBackSignalException {
        ConsoleUtil.cursorTo(consoleSize.getRow(), 1);
        System.out.print(ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                + message
                + StringUtil.blankSpaces(consoleSize.getColumn() - message.length()));
        ConsoleUtil.cursorTo(consoleSize.getRow(), message.length() + 1);

        String userInput = scanner.nextLine();

        if (userInput.strip().toUpperCase().equals("\\EXIT")) {
            throw new ExitSignalException("The user has sent the exit signal");
        }
        if (userInput.strip().toUpperCase().equals("\\BACK")) {
            throw new GoBackSignalException("The user has sent the go back signal");
        }

        return userInput;
    }
}
