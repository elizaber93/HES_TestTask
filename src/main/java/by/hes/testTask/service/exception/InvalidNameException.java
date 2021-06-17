package by.hes.testTask.service.exception;

public class InvalidNameException extends Exception{
    public InvalidNameException() {
        super();
    }

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(Exception e) {
        super(e);
    }

    public InvalidNameException(String message, Exception e) {
        super(message, e);
    }
}
