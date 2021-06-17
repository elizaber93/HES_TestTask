package by.hes.testTask.service.exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(Exception e) {
        super(e);
    }

    public InvalidPasswordException(String message, Exception e) {
        super(message, e);
    }
}
