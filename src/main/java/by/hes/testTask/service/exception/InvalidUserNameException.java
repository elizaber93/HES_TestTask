package by.hes.testTask.service.exception;

public class InvalidUserNameException extends Exception{
    public InvalidUserNameException() {
        super();
    }

    public InvalidUserNameException(String message) {
        super(message);
    }

    public InvalidUserNameException(Exception e) {
        super(e);
    }

    public InvalidUserNameException(String message, Exception e) {
        super(message, e);
    }
}
