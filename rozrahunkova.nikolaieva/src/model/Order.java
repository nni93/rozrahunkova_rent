package model;

import java.util.Date;

/**
 * Class Order serves to recording the study - customer orders (for rent real
 * estate)
 * 
 * @author Natali Nikolaieva
 * 
 */
public class Order implements Comparable<Object> {

    private Date dateOfOrder = null;
    private Client client = null;
    private double price = 0;
    private String address = null;

    /**
     * Default constructor of class Order
     */
    public Order() {
    }

    /**
     * Constructor with params of class Order
     * 
     * @param dateOfOrder
     *            date of order
     * @param client
     *            firms client
     * @param price
     *            rent price
     */
    public Order(Date dateOfOrder, Client client, double price, String address) {
        this.dateOfOrder = dateOfOrder;
        this.client = client;
        setPrice(price);
        this.address = address;
    }

    /**
     * Method that return date of order
     * 
     * @return date of order
     */
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    /**
     * Method that set date of order
     * 
     * @param dateOfOrder
     *            current date of order
     * @throws IllegalArgumentException
     *             Thrown to indicate that a method has been passed an illegal
     *             or inappropriate argument
     */
    public void setDateOfOrder(Date dateOfOrder)
            throws IllegalArgumentException {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * Method that set firms client
     * 
     * @param client
     *            firms client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Method that return firms client
     * 
     * @return firms client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method that return rent price
     * 
     * @return rent price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method that set rent price
     * 
     * @param thePrice
     *            current rent price
     */
    public void setPrice(double thePrice) {
        if (thePrice <= 0)
            throw new IllegalArgumentException("Incorrect " + thePrice);
        this.price = thePrice;
    }
    
    /**
     * Method that return address
     * 
     * @return address
     */

    public String getAddress() {
        return address;
    }

    /**
     * Method that set address
     * 
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Overriding method toString
     */
    @Override
    public String toString() {
        return "Order [dateOfOrder= " + dateOfOrder + ", client= " + client
                + ", price= " + price + "thous. dollars " + ", address " + address + " ]";
    }

    /**
     * Overriding method hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result
                + ((dateOfOrder == null) ? 0 : dateOfOrder.hashCode());
        return result;
    }

    /**
     * Overriding method equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (Double.doubleToLongBits(price) != Double
                .doubleToLongBits(other.price))
            return false;
        if (dateOfOrder == null) {
            if (other.dateOfOrder != null)
                return false;
        } else if (!dateOfOrder.equals(other.dateOfOrder))
            return false;
        return true;
    }

    /**
     * Overriding method compareTo
     */
    @Override
    public int compareTo(Object o) {
        Order someOrder = (Order) o;
        int rez = 0;
        if (this.dateOfOrder.before(someOrder.dateOfOrder))
            rez = 1;
        else if (this.dateOfOrder.after(someOrder.dateOfOrder))
            rez = -1;
        return rez;
    }

}