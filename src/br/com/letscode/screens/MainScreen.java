package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class MainScreen implements ScreenInterface {
    public Navigation run(String[] args) {
        ConsoleUtil.scrollScreen();

        ConsolePosition consolePos = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.print(SystemInterfaceUtil.getHeader("Main Screen", consolePos));

        System.out.print(ConsoleUtil.NEW_LINE
                + StringUtil.centralize("########## Digite a opção desejada: ##########", consolePos.getColumn())
                + ConsoleUtil.NEW_LINE);
        ConsoleUtil.skipLines(3);
        System.out.print(StringUtil.centralize("1. Criar um cliente", consolePos.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("2. Ir para a lista de clientes", consolePos.getColumn()) + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("3. Ir para a lista de contas", consolePos.getColumn()) + ConsoleUtil.NEW_LINE);
        ConsoleUtil.skipLines(3);
        System.out.print("Digite o número da opção desejada: ");

        Scanner s = new Scanner(System.in);
        Navigation navigate = new Navigation();
        while (true) {
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
                    continue;
            }
            break;
        }
        s.close();

        navigate.setArgs(args);

        return navigate;
    }
}
