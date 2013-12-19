package model;

import java.util.Date;

/**
 * Class RentDate intended for setting the date of lease, area property and its
 * value
 * 
 * @author Natali Nikolaieva
 * 
 */
public class RentDate {

    private Date dateTime = null;
    private int area = 0;
    private double rentPrice = 0;
    private String address = null;

    /**
     * Constructor with params of class RentDate
     * 
     * @param theDateTime
     *            date of rental services
     * @param theArea
     *            area of realty
     * @param theRentPrice
     *            rent price
     */
    public RentDate(Date theDateTime, int theArea, double theRentPrice, String address) {
        this.dateTime = theDateTime;
        this.area = theArea;
        setRentPrice(theRentPrice);
        this.address = address;
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
     * Method that return rent price
     * 
     * @return rent price
     */
    public double getRentPrice() {
        return rentPrice;
    }

    /**
     * Method that set rent price
     * 
     * @param rentPrice
     */
    public void setRentPrice(double rentPrice) {
        if (rentPrice <= 0)
            throw new IllegalArgumentException("Incorrect price!");
        this.rentPrice = rentPrice;
    }

    /**
     * Method that return date of rental services
     * 
     * @return date of rental services
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Method that set current date of rental services
     * 
     * @param dateTime
     *            date of rental services
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Method that return area of realty
     * 
     * @return area of realty
     */
    public int getArea() {
        return area;
    }

    /**
     * Method that set area of realty
     * 
     * @param area
     *            area of realty
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Overriding method toString
     */
    @Override
    public String toString() {
        return "RentDate [dateTime=" + dateTime + ", area=" + area
                + ", rentPrice=" + rentPrice + "thous.dollars " + address + " ]";
    }
}