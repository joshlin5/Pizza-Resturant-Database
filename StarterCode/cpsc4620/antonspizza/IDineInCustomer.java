package cpsc4620.antonspizza;

import java.util.*;

/**
 * A dine in customer has a table number and a set of seats, on top of what was inherited
 * from the ICustomer interface
 *
 * Defines: Table_Num: Z
 *          Seats: A set of Z
 *
 * Initialization Ensures: Table_Num is set and all Seats are included
 */
public interface IDineInCustomer extends ICustomer {

    /**
     *
     * @return the table number of the customer
     * @ensures getTableNum = Table_Num
     */
    int getTableNum();

    /**
     *
     * @return the List of seats for the customer
     * @ensures getSeats = Seats
     */
    List<Integer> getSeats();

}
