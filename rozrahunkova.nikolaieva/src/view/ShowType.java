package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import model.Order;

/**
 * Class ShowType contains timeSort method to display the contents of a database
 * application in the form of a collection of type SortedMap
 * 
 * @author Natali Nikolaieva
 * 
 */
public class ShowType {

    /**
     * Method to display the contents of a database application in the form of a
     * collection of type SortedMap
     * 
     * @param array
     *            array of orders
     * @return SortedMap with key - Date and value - Set<Order>
     * @throws IllegalArgumentException
     *             Thrown to indicate that a method has been passed an illegal
     *             or inappropriate argument
     */
    public static SortedMap<Date, Set<Order>> timeSort(ArrayList<Order> array)
            throws IllegalArgumentException {
        if (array.isEmpty())
            throw new IllegalArgumentException("Collection is empty");
        SortedMap<Date, Set<Order>> sm = new TreeMap<Date, Set<Order>>();
        for (Order o : array) {
            if (!sm.containsKey(o.getDateOfOrder())) {
                Set<Order> setofOrder = new HashSet<Order>();
                setofOrder.add(o);
                sm.put(o.getDateOfOrder(), setofOrder);
            } else {
                (sm.get(o.getDateOfOrder())).add(o);
            }
        }
        return sm;
    }
}