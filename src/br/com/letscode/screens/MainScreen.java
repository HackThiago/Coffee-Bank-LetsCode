package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.MessageType;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class MainScreen implements ScreenInterface {
    private static void draw(ConsolePosition consoleSize, String message) {
        ConsoleUtil.scrollScreen();

        System.out.print(SystemInterfaceUtil.getHeader("Main Screen", consoleSize));

        ConsoleUtil.skipLines(1);
        if (message.length() > 0) {
            System.out.print(SystemInterfaceUtil.getMessage(message, MessageType.INFO, consoleSize.getColumn()));
        } else {
            ConsoleUtil.skipLines(1);
        }

        ConsoleUtil.skipLines(1);

        System.out
                .print(StringUtil.centralize("########## Digite a opção desejada: ##########", consoleSize.getColumn())
                        + ConsoleUtil.NEW_LINE);
        ConsoleUtil.skipLines(3);
        System.out.print(StringUtil.centralize("1. Criar um cliente", consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("2. Ir para a lista de clientes", consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("3. Ir para a lista de contas", consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE);
        ConsoleUtil.skipLines(3);
    }

    public Navigation run(String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        String message = "";
        try {
            message = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing
        }

        Scanner s = new Scanner(System.in);
        Navigation navigate = new Navigation();
        while (true) {
            ConsoleUtil.clearScreen();
            draw(consoleSize, message);
            System.out.print("Digite o número da opção desejada: ");

            int userInput = s.nextInt();
            switch (userInput) {
                case 1:
                    navigate.setScreen(ScreensList.CREATE_ACCOUNT);
                    break;
                case 2:
                    navigate.setScreen(ScreensList.CLIENTS_LIST);
                    break;
                case 3:
                    navigate.setScreen(ScreensList.ACCOUNTS_LIST);
                    break;
                default:
                    message = "Opção inválida!";
                    continue;
            }
            break;
        }
        s.close();

        navigate.setArgs(args);

        return navigate;
    }
}
