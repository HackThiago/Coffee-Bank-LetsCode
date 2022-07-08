package br.com.letscode.screens;

import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.database.ClienteDAO;
import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.Message;
import br.com.letscode.model.Navigation;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
import br.com.letscode.model.conta.Conta;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.MessageType;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class ClientScreen implements ScreenInterface {
    private static String drawAccountsList(Cliente client) {
        String accountsList = "";

        for (Conta account : client.getContas()) {
            accountsList = accountsList.concat("Conta " + account.getTipoConta()
                    + " #" + account.getCodigoConta()
                    + " / Saldo: " + StringUtil.formatCurrencyBRL(account.consultarSaldo())
                    + ConsoleUtil.NEW_LINE);
        }

        if (accountsList.length() == 0) {
            accountsList = "Esse cliente não possui contas";
        }

        return accountsList;
    }

    private static void draw(ConsolePosition consoleSize, Message message, Cliente client) {
        final String SCREEN_NAME = "Detalhamento cliente";
        final String SCREEN_CONTENT = StringUtil.centralize("########## "
                + client.getNome()
                + " ##########",
                consoleSize.getColumn())
                + StringUtil.multiply(ConsoleUtil.NEW_LINE, 4)
                + StringUtil.centralizeBlock(
                        (client.getClass() == ClientePF.class
                                ? ("CPF: " + StringUtil.formatCPF(((ClientePF) client).getCpf()))
                                : ("CNPJ: " + StringUtil.formatCNPJ(((ClientePJ) client).getCnpj())))
                                + StringUtil.multiply(ConsoleUtil.NEW_LINE, 2)
                                + drawAccountsList(client)
                                + ConsoleUtil.NEW_LINE,
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE;

        SystemInterfaceUtil.drawInfoScreen(SCREEN_NAME, message, SCREEN_CONTENT, consoleSize);
    }

    public Navigation executeUserCommand(String userCommand) throws InvalidCommandException {
        // TODO
        return new Navigation(ScreensList.ACCOUNT, null);
    }

    public Navigation run(Scanner scanner, String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Message message = new Message("", null);
        Cliente client = ClienteDAO.getClienteById(args[2]);
        args = StringUtil.removeArgFromList(args, 2);

        while (true) {
            ConsoleUtil.clearScreen();
            draw(consoleSize, message, client);

            String promptMessage = "Digite o comando que deseja executar: ";
            String userInput = "";
            try {
                userInput = SystemInterfaceUtil.getUserInput(scanner, consoleSize, promptMessage).strip();
            } catch (ExitSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.EXIT, args);
            } catch (GoBackSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.CLIENTS_LIST, args);
            } catch (NoSuchElementException e) {
                // do nothing
            }
            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            try {
                Navigation commandReturn = executeUserCommand(userInput);
                if (commandReturn.getScreen() != ScreensList.ACCOUNT) {
                    ConsoleUtil.clearScreen();
                    return commandReturn;
                }

                message.setText("Comando executado com sucesso!");
                message.setType(MessageType.SUCCESS);
                continue;
            } catch (InvalidCommandException e) {
                message.setText("Comando inválido!");
                message.setType(MessageType.ERROR);
                continue;
            }
        }
    }

}
