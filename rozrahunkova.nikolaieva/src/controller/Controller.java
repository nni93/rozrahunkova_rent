package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

import model.Client;
import model.MyException;
import model.Order;
import model.RentDate;
import model.StreamClass;
import view.ShowType;

/**
 * Controller class contains the main method of application, which defines the
 * interface of the user (subject) with the program
 * 
 * @author Natali Nikolaieva
 * 
 */

public class Controller {

    final static Pattern email_pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}+|[\n]", Pattern.CASE_INSENSITIVE);
    
    final static Pattern tel_pattern = Pattern.compile("[0-9]+|[\n]");
    
    static private String filename = "Orders.txt";

    static List<RentDate> rentDate = new ArrayList<RentDate>();

    static {
        // в колекції задаємо дати замовлень, площі нерухомості та ціну
        try {
            RentDate salesDate1 = new RentDate(
                    StreamClass.myDateFormat.parse("01.01.2015"), 100,
                    500, "Yakira 10");
            RentDate salesDate2 = new RentDate(
                    StreamClass.myDateFormat.parse("01.02.2015"), 200,
                    1000, "Petropavlovska 57");
            RentDate salesDate3 = new RentDate(
                    StreamClass.myDateFormat.parse("01.03.2015"), 300,
                    1500, "Soborna 20");
            rentDate.add(salesDate1);
            rentDate.add(salesDate2);
            rentDate.add(salesDate3);
        } catch (ParseException e) {
            System.out.println("Problem with parsing date");
        }

    }

    /**
     * The runtime system starts by calling the class's main() method. The
     * main() method then calls all the other methods required to run your
     * application.
     * 
     * @param args
     *            an array of Strings
     */
    public static void main(String[] args) throws IOException {
        Scanner read = null;
        try {
            String rez = "end";
            read = new Scanner(System.in);
            do {
                System.out
                        .println("Show All orders, Make order or Remove order (Enter All/Make order/Remove) ");
                rez = read.nextLine();
                if (rez.equalsIgnoreCase("All")) {
                    try {
                        showAll(filename);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Collection is empty!");
                    }
                }
                if (rez.equalsIgnoreCase("Make order")) {
                    addOrder(filename);
                }
                if (rez.equalsIgnoreCase("Remove")) {
                    remove(read, filename);
                }
            } while ((!rez.equalsIgnoreCase("All"))
                    || (!rez.equalsIgnoreCase("Sell"))
                    || (!rez.equalsIgnoreCase("Remove")));
        } catch (IOException e) {
            System.out.println("Check file! " + filename);
        } finally {
            if (read != null)
                read.close();
        }
    }

    /**
     * Method check if current date already exist
     * 
     * @param orderDatetoCheck
     *            current date
     * @return true if current date already exist, else return false
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred This
     *             class is the general class of exceptions produced by failed
     *             or interrupted I/O operations
     * @throws ParseException
     *             Signals that an error has been reached unexpectedly while
     *             parsing
     */
    static boolean checkOrderOnCurrentDate(Date orderDatetoCheck)
            throws IOException, ParseException {
        boolean orderExsists = false;
        CopyOnWriteArrayList<Order> rezultCollection = StreamClass.readerText(filename);
        if (rezultCollection.isEmpty()) {
            System.out.println("No orders");
        } else {
            Map<Date, Set<Order>> rezultMap = ShowType
                    .timeSort(rezultCollection);
            if (rezultMap.containsKey(orderDatetoCheck)) {
                orderExsists = true;
            } else
                orderExsists = false;
        }
        return orderExsists;
    }

    /**
     * Method that add new order to file
     * @param file name of the file
     */
    @SuppressWarnings("null")
    static void addOrder(String file) {
        Scanner reader = null;

        // String answerString = "Y";

        try {
            reader = new Scanner(System.in);
            // while (answerString.equalsIgnoreCase("y")) {
            System.out.println("SalesDate of orders: ");
            for (int i = 0; i < rentDate.size(); i++) {
                System.out.println(i + " " + rentDate.get(i).toString());
            }
            int dataPointer = -1;
            int numberOfDate = -1;
            while (dataPointer == -1) {
                try {
                    System.out.println("Select the date and time of order");
                    numberOfDate = Integer.parseInt(reader.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect number!");
                }
                if (numberOfDate <= rentDate.size() - 1) {
                    dataPointer = numberOfDate;
                } else
                    System.out.println("Incorrect order date pointer");
            }
            double price = rentDate.get(numberOfDate).getRentPrice();
            String address = rentDate.get(numberOfDate).getAddress();

            if (checkOrderOnCurrentDate(rentDate.get(numberOfDate)
                    .getDateTime())) {
                System.out.println("Order already exists");
            } else {
                System.out
                        .println("Enter surnamename, firstname and secondname of client: ");
                String fullnameString = reader.nextLine();
                if (fullnameString.length() < 2)
                    throw new MyException("Name of client " + fullnameString
                            + "is incorrect!");
                System.out.println("Enter telephone number of client: ");
                String telephoneString = reader.nextLine();
                if (!tel_pattern.matcher(telephoneString).matches()) {
                    throw new MyException("Telnumber " + fullnameString
                            + " is incorrect!");
                }
                System.out.println("Enter e-mail of client: ");
                String e_mailString = reader.nextLine();
                if (!email_pattern.matcher(e_mailString).matches()) {
                    throw new MyException("E-mail " + e_mailString
                            + " is incorrect!");
                }
                System.out.println("Enter address of rent object");
                String addressString = reader.nextLine();
                if (addressString.length() < 2)
                    throw new MyException("Address " + addressString
                            + " is incorrect!");

                // створення об'єктів Client та Order
                Client cl = new Client();
                cl.setFullName(fullnameString);
                cl.setTelNumber(telephoneString);
                cl.setE_mail(e_mailString);
                Order newOrder = new Order(rentDate.get(numberOfDate)
                        .getDateTime(), cl, price, address);

                // перезаписуємо колекцію, щоб не втратити вже існуючі записи в
                // БД:
                List<Order> collect = StreamClass.readerText(file);
                collect.add(newOrder);
                StreamClass.writeText(collect, file);
                System.out.println(newOrder + " was added!");
                // System.out.println("Repeat operation? (Enter Y/N)");
                // answerString = reader.nextLine();
                // }
            }
        } catch (IOException e) {
            System.out.println("lncorrect file " + file);
        } catch (ParseException e) {
            System.out.println("Incorrect date settings!");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader == null)
                reader.close();
        }
    }

    /**
     * Method that show all orders which contains in the file
     * @param filename name of the file
     * @throws IOException 
     *             Signals that an I/O exception of some sort has occurred This
     *             class is the general class of exceptions produced by failed
     *             or interrupted I/O operations
     */
    static void showAll(String filename) throws IOException {
        CopyOnWriteArrayList<Order> allCollection = StreamClass.readerText(filename);
        SortedMap<Date, Set<Order>> rezultMap = view.ShowType
                .timeSort(allCollection);
        Collection<Date> c = rezultMap.keySet();
        if (c.isEmpty()) {
            System.out.println("There are no elements in collection!");
        }
        Iterator<Date> iter = c.iterator();
        while (iter.hasNext()) {
            Date currentDate = iter.next();
            System.out.println(currentDate + " " + rezultMap.get(currentDate));
        }
    }

    /**
     * Method that remove order from the file
     * @param read instance of class Scanner
     * @param somefileName name of file
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred This
     *             class is the general class of exceptions produced by failed
     *             or interrupted I/O operations
     * @throws IllegalArgumentException
     *             Thrown to indicate that a method has been passed an illegal 
     *             or inappropriate argument
     */
    static void remove(Scanner read, String somefileName) throws IOException,
            IllegalArgumentException {
        if (read == null)
            throw new IllegalArgumentException("empty Stream!");
        System.out
                .println("Enter client fullName, whose order need to remove: ");
        String name = read.nextLine();
        System.out
                .println("Enter date of this client order: (Use format dd.MM.yyyy)");
        String dateString = read.nextLine();
        Date dateToRemove;            
        try {
            dateToRemove = StreamClass.myDateFormat.parse(dateString);
            List<Order> collect = Collections.synchronizedList(StreamClass.readerText(somefileName));
            if (!collect.isEmpty()) {
                for (Order o : collect) {
                    if ((o.getClient().getFullName().equalsIgnoreCase(name))
                            && (o.getDateOfOrder().equals(dateToRemove))) {
                           collect.remove(o);
                        System.out.println(o.toString() + " was removed!");
                    } 
                    StreamClass.writeText(collect, somefileName);
                } 
            }
        } catch (ParseException e) {
            System.out.println("Incorrect date format!");
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage() + " ConcurrentModificationException");
        }
    }
}
