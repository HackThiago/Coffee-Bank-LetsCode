package br.com.letscode.screens;

public enum ScreensList {
    START, MAIN, CLIENT, CLIENTS_LIST, CREATE_CLIENT, EXIT;

    public ScreenInterface createInstance() {
        ScreenInterface screenInstance;
        switch (this) {
            case START:
                screenInstance = new StartScreen();
                break;
            case MAIN:
                screenInstance = new MainScreen();
                break;
            case CLIENT:
                screenInstance = new ClientScreen();
                break;
            case CLIENTS_LIST:
                screenInstance = new ClientsListScreen();
                break;
            case CREATE_CLIENT:
                screenInstance = new CreateClientScreen();
                break;
            case EXIT:
                screenInstance = new ExitScreen();
                break;
            default:
                screenInstance = new ExitScreen();
                break;
        }
        return screenInstance;
    }
}
