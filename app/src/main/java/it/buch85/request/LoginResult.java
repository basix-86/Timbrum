package it.buch85.request;

public class LoginResult {
    boolean success = false;
    String message = "";

    public LoginResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
