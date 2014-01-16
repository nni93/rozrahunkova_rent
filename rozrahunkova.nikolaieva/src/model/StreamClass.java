package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class StreamClass intended to describe the static methods of the text write /
 * read data from file
 * 
 * @author Natali Nikolaieva
 * 
 */
public class StreamClass {

    public static SimpleDateFormat myDateFormat = new SimpleDateFormat(
            "dd.MM.yyyy");

    /**
     * Method that write a collection of type List <Order> in a file named
     * filename
     * 
     * @param collection
     *            collection of orders
     * @param filename
     *            name of file
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred This
     *             class is the general class of exceptions produced by failed
     *             or interrupted I/O operations
     */
    public static void writeText(List<Order> collection, String filename)
            throws IOException {
        if (collection == null || filename == null)
            throw new IllegalArgumentException("Illegal parameters!");

        File f = new File(filename);
        if (!f.exists()) {
            f.createNewFile();
        }
        PrintWriter wr = new PrintWriter(new FileWriter(f));
        try {
            wr.println(collection.size());
            for (Order c : collection) {
                wr.write("Date of order: ");
                wr.append('\n');
                wr.write(myDateFormat.format(c.getDateOfOrder().getTime()));
                wr.append('\n');
                wr.write("Price of order: ");
                wr.append('\n');
                wr.println(c.getPrice());
                wr.write("Address of order: ");
                wr.append('\n');
                wr.println(c.getAddress());
                wr.write("client fullname: ");
                wr.append('\n');
                wr.write(c.getClient().getFullName());
                wr.append('\n');
                wr.write("client telNumber: ");
                wr.append('\n');
                wr.write(c.getClient().getTelNumber());
                wr.append('\n');
                wr.write("client e_mail: ");
                wr.append('\n');
                wr.write(c.getClient().getE_mail());
                wr.append('\n');
            }
        } finally {
            wr.close();
        }
    }

    /**
     * Method for read the contents of the file filename
     * 
     * @param filename
     *            name of file
     * @return ArrayList with orders
     * @throws IOException
     *             Signals that an I/O exception of some sort has occurred This
     *             class is the general class of exceptions produced by failed
     *             or interrupted I/O operations
     */
    public static CopyOnWriteArrayList<Order> readerText(String filename)
            throws IOException {
        File f = new File(filename);
        if (!f.exists())
            throw new IOException();
        CopyOnWriteArrayList<Order> array = new CopyOnWriteArrayList<Order>();
        //ArrayList<Order> array = new ArrayList<Order>();
        if (f.length() != 0) {
            Scanner sc = new Scanner(new FileReader(f));
            try {
                // зчитали кількість обєктів в файлі
                String countString = sc.nextLine();
                int count = Integer.parseInt(countString);
                for (int i = 0; i < count; i++) {
                    sc.nextLine();
                    // зчитування датy:
                    String dateString = sc.nextLine();
                    Date dateOfOrder = null;
                    try {
                        // перетворюємо рядок в дату типу Date
                        dateOfOrder = myDateFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.getMessage();
                    }
                    sc.nextLine();
                    String priceString = sc.nextLine();
                    double price = Double.parseDouble(priceString);
                    sc.nextLine();
                    String address = sc.nextLine();
                    sc.nextLine();
                    String fullClientName = sc.nextLine();
                    sc.nextLine();
                    String telNumberclient = sc.nextLine();
                    sc.nextLine();
                    String e_mailclient = sc.nextLine();

                    Client someClient = new Client();
                    someClient.setFullName(fullClientName);
                    someClient.setTelNumber(telNumberclient);
                    someClient.setE_mail(e_mailclient);

                    Order someOrder = new Order(dateOfOrder, someClient, price, address);
                    array.add(someOrder);
                    
                }
            } finally {
                sc.close();
            }
        }
        return array;

    }
}