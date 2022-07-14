package br.com.letscode.screens;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.database.ClienteDAO;
import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.error.InvalidCommandException;
import br.com.letscode.model.ConsolePosition;
import br.com.letscode.model.Message;
import br.com.letscode.model.MessageType;
import br.com.letscode.model.Navigation;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
import br.com.letscode.model.conta.Conta;
import br.com.letscode.model.conta.TipoContaEnum;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class ClientScreen implements ScreenInterface {
    private static final int HEADER_LINES = 10;

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

    private static void draw(ConsolePosition consoleSize, Message message, String screenContent, Cliente client,
            int currentPage,
            int totalPages) {
        final String SCREEN_NAME = "Detalhamento cliente";
        final String SCREEN_HEADER = StringUtil.centralize("########## "
                + client.getNome()
                + " ##########",
                consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralizeBlock(
                        (client.getClass() == ClientePF.class
                                ? ("CPF: " + StringUtil.formatCPF(((ClientePF) client).getCpf()))
                                : ("CNPJ: " + StringUtil.formatCNPJ(((ClientePJ) client).getCnpj())))
                                + ConsoleUtil.NEW_LINE,
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("- Comandos disponiveis -", consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralizeBlock("ABRIR CONTA {CORRENTE | POUPANCA | INVESTIMENTO}"
                        + ConsoleUtil.NEW_LINE
                        + "SACAR {CÓDIGO CONTA} {QUANTIA}"
                        + ConsoleUtil.NEW_LINE
                        + "DEPOSITAR {CÓDIGO CONTA} {QUANTIA}"
                        + ConsoleUtil.NEW_LINE
                        + "INVESTIR {CÓDIGO CONTA} {QUANTIA}"
                        + ConsoleUtil.NEW_LINE
                        + "TRANSFERIR {CÓDIGO CONTA ORIGEM} {CÓDIGO CONTA DESTINO} {QUANTIA}", consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE
                + StringUtil.centralize("Digite \\n ou \\p para navegar entre as páginas", consoleSize.getColumn());

        SystemInterfaceUtil.drawPaginationScreen(SCREEN_NAME, message, SCREEN_HEADER, screenContent, consoleSize,
                currentPage, totalPages);
    }

    public Navigation executeUserCommand(Cliente client, String userCommand, int totalPages, String[] args)
            throws InvalidCommandException {
        int page = Integer.parseInt(args[3]);

        if (userCommand.toLowerCase().equals("\\n")) {
            if (page >= totalPages) {
                throw new InvalidCommandException("Não há mais páginas");
            }
            args = StringUtil.removeArgFromList(args, 3);
            return new Navigation(ScreensList.CLIENT, StringUtil.addArgToList(args, String.valueOf(page + 1)));
        }
        if (userCommand.toLowerCase().equals("\\p")) {
            if (page <= 1) {
                throw new InvalidCommandException("Não há mais páginas");
            }
            args = StringUtil.removeArgFromList(args, 3);
            return new Navigation(ScreensList.CLIENT, StringUtil.addArgToList(args, String.valueOf(page - 1)));
        }

        String[] commandOperands = userCommand.strip().split(" ");

        Conta account;
        BigDecimal value;
        switch (commandOperands[0].toUpperCase()) {
            case "ABRIR":
                if (commandOperands.length != 3 || !commandOperands[1].toUpperCase().matches("CONTA")) {
                    throw new InvalidCommandException("Comando inválido!");
                }

                TipoContaEnum accountType = null;
                switch (commandOperands[2].toUpperCase()) {
                    case "CORRENTE":
                        accountType = TipoContaEnum.CORRENTE;
                        break;
                    case "POUPANCA":
                        accountType = TipoContaEnum.POUPANCA;
                        break;
                    case "INVESTIMENTO":
                        accountType = TipoContaEnum.INVESTIMENTO;
                        break;
                    default:
                        break;
                }

                client.abrirConta(accountType);
                return new Navigation(ScreensList.CLIENT, args);
            case "SACAR":
                if (commandOperands.length != 3) {
                    throw new InvalidCommandException("Comando inválido!");
                }

                account = client.getConta(Integer.parseInt(commandOperands[1]));
                if (account == null) {
                    throw new InvalidCommandException("Conta não pertence a esse cliente!");
                }

                try {
                    value = BigDecimal.valueOf(Double.parseDouble(commandOperands[2]));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Quantia inválida para saque!");
                }

                account.sacar(value);

                return new Navigation(ScreensList.CLIENT, args);
            case "DEPOSITAR":
                if (commandOperands.length != 3) {
                    throw new InvalidCommandException("Comando inválido!");
                }

                account = client.getConta(Integer.parseInt(commandOperands[1]));
                if (account == null) {
                    throw new InvalidCommandException("Conta não pertence a esse cliente!");
                }

                try {
                    value = BigDecimal.valueOf(Double.parseDouble(commandOperands[2]));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Quantia inválida para depósito!");
                }

                account.depositar(value);

                return new Navigation(ScreensList.CLIENT, args);
            case "INVESTIR":
                if (commandOperands.length != 3) {
                    throw new InvalidCommandException("Comando inválido!");
                }

                account = client.getConta(Integer.parseInt(commandOperands[1]));
                if (account == null) {
                    throw new InvalidCommandException("Conta não pertence a esse cliente!");
                }
                if (!account.getTipoConta().equals("INVESTIMENTO") && !account.getTipoConta().equals("POUPANÇA")) {
                    throw new InvalidCommandException("Operação inválida para esse tipo de conta!");
                }

                try {
                    value = BigDecimal.valueOf(Double.parseDouble(commandOperands[2]));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Quantia inválida para depósito!");
                }

                account.depositar(value);

                return new Navigation(ScreensList.CLIENT, args);
            case "TRANSFERIR":
                if (commandOperands.length != 4) {
                    throw new InvalidCommandException("Comando inválido!");
                }

                account = client.getConta(Integer.parseInt(commandOperands[1]));
                Conta receiver = ClienteDAO.getContaById(Integer.parseInt(commandOperands[2]));
                if (account == null) {
                    throw new InvalidCommandException("Conta não pertence a esse cliente!");
                }
                if (receiver == null) {
                    throw new InvalidCommandException("Conta inexistente!");
                }

                try {
                    value = BigDecimal.valueOf(Double.parseDouble(commandOperands[3]));
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Quantia inválida para transferência!");
                }

                account.transferir(receiver, value);

                return new Navigation(ScreensList.CLIENT, args);
            default:
                throw new InvalidCommandException("Comando inválido!");
        }
    }

    public Navigation run(Scanner scanner, String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Message message = new Message("", null);
        Cliente client = ClienteDAO.getClienteById(Integer.parseInt(args[2]));

        int totalPages;
        int page;
        try {
            page = Integer.parseInt(args[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            page = 1;
            args = StringUtil.addArgToList(args, String.valueOf(page));
        }

        while (true) {
            String screenContent = StringUtil.centralizeBlock(drawAccountsList(client), consoleSize.getColumn());
            totalPages = (int) Math.ceil((double) screenContent.split("\n").length
                    / ((double) consoleSize.getRow() - (double) SystemInterfaceUtil.DEFAULT_LINES_PER_PAGE
                            - (double) HEADER_LINES));

            ConsoleUtil.clearScreen();
            draw(consoleSize, message, screenContent, client, page, totalPages);

            String promptMessage = "Digite o comando que deseja executar: ";
            String userInput = "";
            try {
                userInput = SystemInterfaceUtil.getUserInput(scanner, consoleSize, promptMessage).strip();
            } catch (ExitSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.EXIT, args);
            } catch (GoBackSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.CLIENTS_LIST,
                        StringUtil.removeArgFromList(StringUtil.removeArgFromList(args, 3), 2));
            } catch (NoSuchElementException e) {
                // do nothing
            }
            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            try {
                Navigation commandReturn = executeUserCommand(client, userInput, totalPages, args);
                if (commandReturn.getScreen() != ScreensList.CLIENT) {
                    ConsoleUtil.clearScreen();
                    return commandReturn;
                }
                page = Integer.parseInt(commandReturn.getArg(3));
                args = StringUtil.removeArgFromList(args, 3);
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
