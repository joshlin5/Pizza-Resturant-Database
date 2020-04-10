package cpsc4620.antonspizza;

import java.util.*;

/**
 * Pizza will hold all the relevant information such as ID, size, crust, toppings, discounts, and the price of the pizza
 * @invariants ID = (a valid id || -1 to show a new order)
 *              size = (DBNinja.size_s || DBNinja.size_m || DBNinja.size_l || DBNinja.size_xl)
 *              crust = (DBNinja.crust_thin || DBNinja.crust_orig || DBNinja.crust_pan || DBNinja.crust_gf)
 *              baseprice is the base price for that size and crust
 */
public class Pizza {
    private int ID;
    private String size;
    private String crust;
    private ArrayList<Topping> toppings;
    private ArrayList<Discount> discounts;
    private double base_price;

    /**
     *
     * @param i the pizza id or -1 for a new pizza
     * @param s the size of the pizza
     * @param c the crust type
     * @param bp the base price of the pizza
     * @requirws i = (a valid id || -1 to show a new order)
     *           s = (DBNinja.size_s || DBNinja.size_m || DBNinja.size_l || DBNinja.size_xl)
     *           c = (DBNinja.crust_thin || DBNinja.crust_orig || DBNinja.crust_pan || DBNinja.crust_gf)
     *           bp is the base price for that size and crust
     *
     * @ensures ID = i AND size = s AND crust = c AND base_price = bp AND pizza starts with no toppings or discounts
     */
    public Pizza(int i, String s, String c, double bp)
    {
        ID = i;
        size = s;
        crust = c;
        toppings = new ArrayList<Topping>();
        discounts = new ArrayList<Discount>();
        base_price = bp;
    }

    /**
     *
     * @return the pizza id
     * @ensures getID = ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     *
     * @return the pizza size
     * @ensures getSize = size
     */
    public String getSize()
    {
        return size;
    }

    /**
     *
     * @return the pizza crust
     * @ensures getCrust = crust
     */
    public String getCrust()
    {
        return crust;
    }

    /**
     *
     * @param t the topping to add to the pizza
     * @ensures t is added to the pizza
     */
    public void addTopping(Topping t)
    {
        toppings.add(t);
    }

    /**
     *
     * @param d the discount to add to the pizza
     * @requires d is a discount that applies to individual pizzas and not to the order
     * @ensures d is added to the pizza
     */
    public void addDiscount(Discount d)
    {
        discounts.add(d);
    }

    /**
     *
     * @return the price of the pizza
     * @ensures calcPrice is calculated using the base price, the price of each toppings, and any discounts
     */
    public double calcPrice()
    {
        double price = base_price;
        for (Topping t : toppings)
        {
            price += t.getPrice();
            if(t.getExtra())
            {
                price += t.getPrice();
            }
        }

        //discounts
        for (Discount d : discounts)
        {
            if(d.percentDiscount())
            {
                price = price * (1 - d.getPercentDisc());
            }
            else
            {
                price = price - d.getCashDisc();
            }
        }

        return price;
    }

    @Override
    public String toString()
    {
        String s = "Size: " + size + " Crust: " + crust + "\nToppings: \n";
        for (Topping t : toppings)
        {
            if(t.getExtra())
            {
                s = s + " Extra " + t.getName() + ", $" + Double.toString(t.getPrice() * 2 )+ "\n";
            }
            else
            {
                s = s + t.getName() + ", $" + Double.toString(t.getPrice() )+ "\n";
            }
        }
        s += "\n Discounts:";

        for (Discount d : discounts)
        {

            s += d.toString() + "\n";
        }
        s += "\nPrice: $" + Double.toString(this.calcPrice()) + "\n" ;

        return s;
    }

    /*
    Its bad practice to expose the arrays like this, but it will make it easier to convert the order to the format needed for your database and I didn't have the time to go do this properly

    Only use these functions to read the lists, never to modify them

    */

    /**
     *
     * @return all toppings on the pizza
     * @requires this will only be use to read the data, not to modify it
     */
    public ArrayList<Topping> getToppings()
    {
        return toppings;
    }

    /**
     *
     * @return all discounts on the pizza
     * @requires this will only be used to view the data, not to modify it
     */
    public ArrayList<Discount> getDiscounts()
    {
        return discounts;
    }
}
