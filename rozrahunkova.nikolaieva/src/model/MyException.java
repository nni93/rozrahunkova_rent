package model;

/**
 * Class MyException to generate its own exception type
 * 
 * @author Natali Nikolaieva
 * 
 */
public class MyException extends Exception {
    /**
     * serialVersionUID auto-generate UID
     */
    private static final long serialVersionUID = -8520720171137328016L;

    /**
     * Default constructor of class MyException
     */
    public MyException() {
        super();
    }

    /**
     * Constructor with params of class MyException
     * 
     * @param message
     *            message of exception
     */
    public MyException(String message) {
        super(message);
    }
}