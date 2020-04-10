package cpsc4620.antonspizza;

import java.util.*;

/**
 * DineInCustomer extends AbsCustomer to inherit its ID and Type
 * DineInCustomer implements the IDineInCustomer interface
 *
 * Correspondences: Table_Num = tableNum
 *                  Seats = seats
 */

public class DineInCustomer extends AbsCustomer implements IDineInCustomer  {
    private int tableNum;
    private List<Integer> seats;

    /**
     *
     * @param t the table number
     * @param s the seats
     * @param i the ID or -1 if unavailable
     * @requires i = (a vaild ID in the database || -1)
     * @ensures tableNum = t and seats = s and ID = i and type = DBNinja.dine_in
     */
    public DineInCustomer(int t, List<Integer> s, int i)
    {
        tableNum = t;
        seats = s;
        ID = i;
        type = DBNinja.dine_in;
    }


    public int getTableNum()
    {
        return tableNum;
    }

   public List<Integer> getSeats()
   {
       return seats;
   }

    @Override
    public String toString()
    {
        String s = "Table: " + tableNum + " Seats: ";

        for(Integer seat: seats)
        {
            s += seat + " ";
        }
        return s;
    }


}
