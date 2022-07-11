package br.com.letscode.screens;

import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.model.ConsolePosition;
import br.com.letscode.model.Message;
import br.com.letscode.model.MessageType;
import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class MainScreen implements ScreenInterface {
    private static void draw(ConsolePosition consoleSize, Message message) {
        final String SCREEN_NAME = "Home";
        final String SCREEN_CONTENT = StringUtil.centralize("########## Digite a opção desejada: ##########",
                consoleSize.getColumn())
                + StringUtil.multiply(ConsoleUtil.NEW_LINE, 4)
                + StringUtil.centralizeBlock("1. Criar um cliente"
                        + ConsoleUtil.NEW_LINE
                        + "2. Ir para a lista de clientes"
                        + ConsoleUtil.NEW_LINE,
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE;

        SystemInterfaceUtil.drawInfoScreen(SCREEN_NAME, message, SCREEN_CONTENT, consoleSize);
    }

    public Navigation run(Scanner scanner, String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Message message = new Message("", MessageType.ERROR);
        Navigation navigate = new Navigation();

        while (true) {
            ConsoleUtil.clearScreen();
            draw(consoleSize, message);
            int userInput = 0;
            try {
                userInput = Integer.parseInt(
                        SystemInterfaceUtil.getUserInput(scanner, consoleSize, "Digite o número da opção desejada: "));
            } catch (NumberFormatException e) {
                message.setText("Opção inválida!");
                continue;
            } catch (ExitSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.EXIT, args);
            } catch (GoBackSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.START, args);
            } catch (NoSuchElementException e) {
                // do nothing
            }

            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            switch (userInput) {
                case 1:
                    navigate.setScreen(ScreensList.CREATE_CLIENT);
                    break;
                case 2:
                    navigate.setScreen(ScreensList.CLIENTS_LIST);
                    break;
                default:
                    message.setText("Opção inválida!");
                    continue;
            }
            break;
        }

        ConsoleUtil.clearScreen();
        navigate.setArgs(args);
        return navigate;
    }
}
