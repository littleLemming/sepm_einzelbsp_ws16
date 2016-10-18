package sepm.ws16.e1327450.dao;

public class PersistenceException extends Exception {

    private boolean error = false;

    public PersistenceException(String message) {
        this(message,true);
    }

    public PersistenceException(String message, boolean error) {
        super(message);
        this.error = error;
    }

    public boolean didErrorOccur() {
        return error;
    }

}
