package social.net.exceptions;

public class RegistrationException extends RuntimeException{
    public RegistrationException() {
        super();
    }
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
    public RegistrationException(String message) {
        super(message);
    }
    public RegistrationException(Throwable cause) {
        super(cause);
    }
}
