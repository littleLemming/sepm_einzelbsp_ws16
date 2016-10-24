package sepm.ws16.e1327450.service;

public class ServiceException extends Exception {

    private boolean error = false;

    public ServiceException(String message, boolean error){
        super(message);
        this.error = error;
    }

    public ServiceException(String message){
        this(message,true);
    }

    public boolean didErrorOccur(){
        return error;
    }

}
