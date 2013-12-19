package model;

/**
 * This class is used to describe the clients
 * 
 * @author Nikolaieva Natalia
 */
public class Client implements Comparable<Object> {
    private String fullName = null;
    private String telNumber = null;
    private String e_mail = null;

    /**
     * default constructor
     */
    public Client() {
    }

    /**
     * Constructor with parameters
     * 
     * @param fullName
     *            - full name of client
     * @param telNumber
     *            - telephone number of client
     * @param e_mail
     *            - e-mail of client
     */
    public Client(String fullName, String telNumber, String e_mail) {
        this.fullName = fullName;
        this.telNumber = telNumber;
        this.e_mail = e_mail;
    }

    /**
     * @return fullName of Client
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     *            for setting the name of client
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return telephone number of Client
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * @param telNumber
     *            for setting telephone number of Client
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * @return e-mail of Client
     */

    public String getE_mail() {
        return e_mail;
    }

    /**
     * @param e_mail
     *            of Client
     */
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    /**
     * hashCode for objects of class Client, used for adequate comparison of
     * objects of class Client
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((e_mail == null) ? 0 : e_mail.hashCode());
        result = prime * result
                + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result
                + ((telNumber == null) ? 0 : telNumber.hashCode());
        return result;
    }

    /**
     * for adequate comparison of objects of class Client
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (e_mail == null) {
            if (other.e_mail != null)
                return false;
        } else if (!e_mail.equals(other.e_mail))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (telNumber == null) {
            if (other.telNumber != null)
                return false;
        } else if (!telNumber.equals(other.telNumber))
            return false;
        return true;
    }

    /**
     * for show information about Client objects in adequate view
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[ fullname = "
                + this.getFullName() + ", telNumber ="
                + this.getTelNumber() + ", e_mail= " + this.getE_mail() + " ] ";
    }

    /**
     * to set the criterion of comparison
     */
    @Override
    public int compareTo(Object o) {
        Client someclient = (Client) o;
        int rez = 0;
        if (this.getTelNumber().length() > someclient.getTelNumber().length()) {
            rez = 1;
        } else if (this.getTelNumber().equals(someclient.getTelNumber())) {
            rez = 0;
        } else rez = -1;
        return rez;
    }
}
