package br.com.letscode.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.letscode.database.ClienteDAO;
import br.com.letscode.error.ExitSignalException;
import br.com.letscode.error.GoBackSignalException;
import br.com.letscode.model.Message;
import br.com.letscode.model.Navigation;
import br.com.letscode.model.cliente.Cliente;
import br.com.letscode.model.cliente.ClientePF;
import br.com.letscode.model.cliente.ClientePJ;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.MessageType;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class CreateClientScreen implements ScreenInterface {
    private static void draw(ConsolePosition consoleSize, Message message, List<Integer> highlitedLines) {
        final String SCREEN_NAME = "Cadastrar cliente";
        final String SCREEN_CONTENT = StringUtil.centralize(
                "########## Vamos precisar das seguintes informações do cliente: ##########",
                consoleSize.getColumn())
                + StringUtil.multiply(ConsoleUtil.NEW_LINE, 4)
                + StringUtil.centralizeBlock("1. Tipo de cliente (Pessoa Física ou Pessoa Jurídica)"
                        + ConsoleUtil.NEW_LINE
                        + "2. CPF ou CNPJ"
                        + ConsoleUtil.NEW_LINE
                        + "3. Nome completo"
                        + ConsoleUtil.NEW_LINE,
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE;

        String screenContent = "";
        String[] screenContentLines = SCREEN_CONTENT.split("\n");
        for (int i = 0; i < screenContentLines.length; i++) {
            String line = screenContentLines[i];
            if (highlitedLines.contains(i)) {
                line = ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                        + line
                        + ConsoleUtil.Attribute.RESET.getEscapeCode();
            }
            screenContent += line + ConsoleUtil.NEW_LINE;
        }

        SystemInterfaceUtil.drawInfoScreen(SCREEN_NAME, message, screenContent, consoleSize);
    }

    private static boolean validateAnswer(int formStep, String answer) {
        switch (formStep) {
            case 1:
                if (answer.matches("[p|P][f|F|j|J]")) {
                    return true;
                }
                break;
            case 2:
                if (answer.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}")) {
                    return true;
                }
                break;
            case 3:
                if (answer.matches("\\d{2}[.]?\\d{3}[.]?\\d{3}[/]?\\d{4}[-]?\\d{2}")) {
                    return true;
                }
                break;
            case 4:
                return true;
            default:
                break;
        }
        return false;
    }

    public Navigation run(Scanner scanner, String[] args) {
        ConsolePosition consoleSize = new ConsolePosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Message message = new Message("", MessageType.ERROR);
        Navigation navigate = new Navigation();
        int formStep = 1;
        List<Integer> highlitedLines = new ArrayList<Integer>();
        highlitedLines.add(0);

        Cliente client = new ClientePF();
        String tipoCliente = "";

        while (true) {
            String promptMessage = "";
            switch (formStep) {
                case 1:
                    promptMessage = "Digite o tipo de cliente (PF ou PJ): ";
                    highlitedLines.set(0, 4);
                    break;
                case 2:
                    promptMessage = "Digite o CPF do cliente: ";
                    highlitedLines.set(0, 5);
                    break;
                case 3:
                    promptMessage = "Digite o CNPJ do cliente: ";
                    highlitedLines.set(0, 5);
                    break;
                case 4:
                    promptMessage = "Digite o nome completo do cliente: ";
                    highlitedLines.set(0, 6);
                    break;
                default:
                    promptMessage = "";
                    break;
            }

            ConsoleUtil.clearScreen();
            draw(consoleSize, message, highlitedLines);

            String userInput = "";
            try {
                userInput = SystemInterfaceUtil.getUserInput(scanner, consoleSize, promptMessage).strip();
            } catch (ExitSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.EXIT, args);
            } catch (GoBackSignalException e) {
                ConsoleUtil.clearScreen();
                return new Navigation(ScreensList.MAIN, args);
            } catch (NoSuchElementException e) {
                // do nothing
            }
            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            if (!validateAnswer(formStep, userInput)) {
                message.setText("Resposta inválida!");
                System.out.println(userInput);
                continue;
            }

            message.setText("");

            switch (formStep) {
                case 1:
                    tipoCliente = userInput.toUpperCase();
                    switch (tipoCliente) {
                        case "PF":
                            client = new ClientePF();
                            formStep++;
                            break;
                        case "PJ":
                            client = new ClientePJ();
                            formStep += 2;
                            break;
                        default:
                            continue;
                    }
                    continue;
                case 2:
                    formStep++;
                case 3:
                    client.setDocument(userInput.replaceAll("\\.|-|/", ""));
                    if (ClienteDAO.existCliente(client)) {
                        client.setDocument(null);
                        message.setText("Documento já cadastrado no banco!");
                        formStep--;
                    } else {
                        message.setText("");
                        formStep++;
                    }
                    continue;
                case 4:
                    client.setNome(userInput);
                    formStep++;
                default:
                    break;
            }
            break;
        }

        client.setId(Cliente.nextId());
        ClienteDAO.createCliente(client);

        ConsoleUtil.clearScreen();
        navigate.setScreen(ScreensList.CLIENT);
        navigate.setArgs(StringUtil.addArgToList(args, client.getId()));
        return navigate;
    }

}
