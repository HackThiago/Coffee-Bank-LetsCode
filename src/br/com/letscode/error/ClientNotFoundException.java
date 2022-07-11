package br.com.letscode.error;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
