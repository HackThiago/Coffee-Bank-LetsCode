package br.com.letscode.screens;

import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.database.ClienteDAO;
import br.com.letscode.error.ClientNotFoundException;
import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.ConsolePosition;
import br.com.letscode.model.Message;
import br.com.letscode.model.MessageType;
import br.com.letscode.model.Navigation;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class ClientsListScreen implements ScreenInterface {
    private static final int HEADER_LINES = 2;

    private static void draw(ConsolePosition consoleSize, Message message, String content, int page,
            int totalPages) {
        final String SCREEN_NAME = "Lista de clientes";
        final String SCREEN_HEADER = StringUtil.centralize(
                "########## Digite o CPF/CNPJ do cliente que deseja visualizar ##########",
                consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize(
                        "Digite \\n ou \\p para navegar entre as páginas",
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE;

        SystemInterfaceUtil.drawPaginationScreen(SCREEN_NAME, message, SCREEN_HEADER, content, consoleSize, page,
                totalPages);
    }

    public Navigation executeUserCommand(String userCommand, int totalPages, String[] args)
            throws InvalidCommandException {
        int page = Integer.parseInt(args[2]);
        args = StringUtil.removeArgFromList(args, 2);

        if (userCommand.equals("\\n")) {
            if (page >= totalPages) {
                throw new InvalidCommandException("Não há mais páginas");
            }
            return new Navigation(ScreensList.CLIENTS_LIST, StringUtil.addArgToList(args, String.valueOf(page + 1)));
        }
        if (userCommand.equals("\\p")) {
            if (page <= 1) {
                throw new InvalidCommandException("Não há mais páginas");
            }
            return new Navigation(ScreensList.CLIENTS_LIST, StringUtil.addArgToList(args, String.valueOf(page - 1)));
        }

        Cliente cliente = null;

        // CPF
        if (userCommand.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}")) {
            try {
                cliente = ClienteDAO.getClienteByCPF(userCommand);
            } catch (ClientNotFoundException e) {
                throw new InvalidCommandException(e.getMessage());
            }

        }

        // CNPJ
        if (userCommand.matches("\\d{2}[.]?\\d{3}[.]?\\d{3}[/]?\\d{4}[-]?\\d{2}")) {
            try {
                cliente = ClienteDAO.getClienteByCNPJ(userCommand);
            } catch (ClientNotFoundException e) {
                throw new InvalidCommandException(e.getMessage());
            }
        }

        if (cliente != null) {
            return new Navigation(ScreensList.CLIENT, StringUtil.addArgToList(args, cliente.getId()));
        }

        throw new InvalidCommandException("Comando inválido!");
    }

    public Navigation run(Scanner scanner, String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Message message = new Message("", null);
        String content = SystemInterfaceUtil.getClientsList(ClienteDAO.getListaCliente(), consoleSize.getColumn());
        int page = 1;
        int totalPages = (int) Math.ceil((double) content.split("\n").length
                / ((double) consoleSize.getRow() - (double) SystemInterfaceUtil.DEFAULT_LINES_PER_PAGE
                        - (double) HEADER_LINES));
        args = StringUtil.addArgToList(args, String.valueOf(page));

        while (true) {
            ConsoleUtil.clearScreen();
            draw(consoleSize, message, content, page, totalPages);

            String promptMessage = "Digite o comando que deseja executar: ";
            String userInput = "";
            try {
                userInput = SystemInterfaceUtil.getUserInput(scanner, consoleSize, promptMessage).strip();
            } catch (ExitSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.EXIT, args);
            } catch (GoBackSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.MAIN, StringUtil.removeArgFromList(args, 2));
            } catch (NoSuchElementException e) {
                // do nothing
            }
            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            try {
                Navigation commandReturn = executeUserCommand(userInput, totalPages, args);
                if (commandReturn.getScreen() != ScreensList.CLIENTS_LIST) {
                    ConsoleUtil.clearScreen();
                    return commandReturn;
                }
                page = Integer.parseInt(commandReturn.getArg(2));
                args = StringUtil.removeArgFromList(args, 2);
                args = StringUtil.addArgToList(args, String.valueOf(page));

                message.setText("Comando executado com sucesso!");
                message.setType(MessageType.SUCCESS);
                continue;
            } catch (InvalidCommandException e) {
                message.setText(e.getMessage());
                message.setType(MessageType.ERROR);
                continue;
            }
        }
    }

}
