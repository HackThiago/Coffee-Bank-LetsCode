package br.com.letscode.screens;

import java.util.Scanner;

import br.com.letscode.model.Message;
import br.com.letscode.model.Navigation;
import br.com.letscode.util.ConsolePosition;
import br.com.letscode.util.ConsoleUtil;
import br.com.letscode.util.MessageType;
import br.com.letscode.util.StringUtil;
import br.com.letscode.util.SystemInterfaceUtil;

public class CreateClientScreen implements ScreenInterface {
    private static void draw(ConsolePosition consoleSize, Message message) {
        final String SCREEN_NAME = "Cadastrar cliente";
        final String SCREEN_CONTENT = StringUtil.centralize(
                "########## Vamos precisar das seguintes informações do cliente: ##########",
                consoleSize.getColumn())
                + StringUtil.multiply(ConsoleUtil.NEW_LINE, 4)
                + StringUtil.centralizeBlock("1. Tipo de cliente (Pessoa Física ou Pessoa Jurídica)"
                        + ConsoleUtil.NEW_LINE
                        + "2. CPF"
                        + ConsoleUtil.NEW_LINE
                        + "3. Nome completo"
                        + ConsoleUtil.NEW_LINE,
                        consoleSize.getColumn())
                + ConsoleUtil.NEW_LINE;

        SystemInterfaceUtil.drawInfoScreen(SCREEN_NAME, message, SCREEN_CONTENT, consoleSize);
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

        // TODO: substituir pela classe cliente quando estiver pronta
        String tipoCliente = "";
        String idCliente = "";
        String nomeCliente = "";

        while (true) {
            ConsoleUtil.clearScreen();
            draw(consoleSize, message);

            String promptMessage = "";
            switch (formStep) {
                case 1:
                    promptMessage = "Digite o tipo de cliente (PF ou PJ): ";
                    break;
                case 2:
                    promptMessage = "Digite o CPF do cliente: ";
                    break;
                case 3:
                    promptMessage = "Digite o CNPJ do cliente: ";
                    break;
                case 4:
                    promptMessage = "Digite o nome completo do cliente: ";
                    break;
                default:
                    promptMessage = "";
                    break;
            }
            SystemInterfaceUtil.drawInputPrompt(consoleSize, promptMessage);
            String userInput = "";
            userInput = scanner.nextLine().strip();
            System.out.print(ConsoleUtil.Attribute.RESET.getEscapeCode());

            if (!validateAnswer(formStep, userInput)) {
                message.setText("Resposta inválida! ");
                System.out.println(userInput);
                continue;
            }
            message.setText("");

            switch (formStep) {
                case 1:
                    tipoCliente = userInput.toUpperCase();
                    switch (tipoCliente) {
                        case "PF":
                            formStep++;
                            break;
                        case "PJ":
                            formStep += 2;
                            break;
                        default:
                            continue;
                    }
                    continue;
                case 2:
                    formStep++;
                case 3:
                    idCliente = userInput.replaceAll("\\.|-|/", "");
                    formStep++;
                    continue;
                case 4:
                    nomeCliente = userInput;
                    formStep++;
                default:
                    break;
            }
            break;
        }

        System.out.println(tipoCliente + " - " + idCliente + " - " + nomeCliente);

        ConsoleUtil.clearScreen();
        navigate.setScreen(ScreensList.CLIENT);
        navigate.setArgs(args);
        return navigate;
    }

}
