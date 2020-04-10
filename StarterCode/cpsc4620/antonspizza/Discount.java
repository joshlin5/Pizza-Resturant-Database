package cpsc4620.antonspizza;

/**
 * @invariant 0 <= percent_off <= 1
 * @invariant 0 <= cash_off
 */


/*
This contains code for your discount object. You should not need to modify this, unless you use the discount name as the primary key instead of adding an ID. Then you can remove the ID field and methods

NOTE: Percent is stored as a decimal. If you store 20% as .20 in your database, then this is fine. If you store 20% as 20 then you will want to make sure to divide the value by 100 when you load it into the Discount object.

NOTE: If the discount is percent off, then pass 0.0 as cash_off to the constructor, and vice versa.

*/

public class Discount {
    private String name;
    private double percent_off;
    private double cash_off;
    private int ID;

    /**
     *
     * @param n discount name
     * @param p percent discount
     * @param c cash discount
     * @param i the ID of the discount
     * @requires 0 <= p <= 1
     *          0 <= c
     *          i = -1 || a valid ID in the database
     */
    public Discount(String n, double p, double c, int i)
    {
        name = n;
        percent_off = p;
        cash_off = c;
        ID = i;
    }

    /**
     *
     * @return the discount name
     * @ensures getName = name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the percent discount
     * @ensures getPercentDisc = percent_off
     */
    public double getPercentDisc()
    {
        return percent_off;
    }

    /**
     *
     * @return the cash discount
     * @ensures getCashDisc = cash_off
     */
    public double getCashDisc()
    {
        return cash_off;
    }

    /**
     *
     * @return the discount id
     * @ensures getID = ID
     */
    public int getID()
    {
        return ID;
    }

    //Returns true if the discount is a percentage

    /**
     *
     * @return the percentage off
     * @ensures percentDiscount = percent_off
     */
    public boolean percentDiscount()
    {
        if (percent_off == 0.0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //Creates a string for the discount to be printed to the screen.
    @Override
    public String toString()
    {
        String s = "Name: " + name + " Discount: ";
        if(this.percentDiscount())
        {
            s += Double.toString(percent_off * 100) + "% off";
        }
        else
        {
            s += "$" + Double.toString(cash_off) + " off";
        }
        return s;
    }
}
