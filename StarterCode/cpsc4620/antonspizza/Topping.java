package cpsc4620.antonspizza;

/*
Your topping class. It should not need modification unless you don't have an ID for your toppings in your database
*/

/**
 * Topping contains the information such as the name, price, ID, inventory level, and whether or not the
 * customer selected "Extra" of that topping
 *
 * @invariants: ID is a valid ID
 *              price >= 0
 *              inventory >= 0
 *
 */

public class Topping {
    private String name;
    private double price;
    private double inventory;
    private int ID;
    private boolean extra;


    /**
     *
     * @param n the name of the topping
     * @param p the price of the topping
     * @param inv the inventory level
     * @param i the topping ID
     * @requires i is a valid ID
     *           price >= 0
     *           inventory >= 0
     *
     * @ensures name = n
     *          price = p
     *          inventory = inv
     *          ID = i
     *          extra = false
     */
    public Topping(String n, double p, double inv, int i)
    {
        name = n;
        price = p;
        inventory = inv;
        ID = i;
        extra = false;
    }

    /**
     *
     * @return the name of the topping
     * @ensures getName = name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the price of the topping
     * @ensures getPrice = price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     *
     * @return the current inventory level
     * @ensures getInv = inventory
     */
    public double getInv()
    {
        return inventory;
    }

    /**
     *
     * @return the topping ID
     * @ensures getID = ID
     */
    public int getID()
    {
        return ID;
    }

    //extra is just a boolean value

    /**
     *
     * @return if the customer requested extra of the topping
     * @ensures getExtra = extra
     */
    public boolean getExtra()
    {
        return extra;
    }

    /**
     * @ensures extra is set to true
     */
    public void makeExtra()
    {
        extra = true;
    }


}
