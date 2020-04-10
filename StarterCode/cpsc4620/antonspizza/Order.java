package cpsc4620.antonspizza;

import java.util.*;

/**
 * Order can hold any type of order, dine in, pick up or delivery. The order contains the pizzas, customer and discounts
 *
 * @invariants ID = (a valid id || -1 to show a new order)
 *              cust can be any customer type
 *              order_type = (DBNinja.dine_in || DBNinja.pickup || DBNinja.delivery)
 *
 */


public class Order {
    private int ID;
    private ICustomer cust;
    private String order_type; //pick-up, delivery, dine-in
    private ArrayList<Pizza> pizzas;
    private ArrayList<Discount> discounts;

    //You pass in the ID, customer, table number and order type.
    //Once the Order is created you can add pizzas and discounts.

    /**
     *
     * @param i the order id or -1 for a new order
     * @param c the customer who has the order
     * @param type the type of order
     * @requires i = (a valid id || -1 to show a new order) AND
     *           type = (DBNinja.dine_in || DBNinja.pickup || DBNinja.delivery)
     * @ensures ID = i and cust = c and order_type = type and the order has not pizzas or discounts yet
     */
    public Order(int i, ICustomer c, String type)
    {
        ID = i;
        cust = c;
        order_type = type;
        pizzas = new ArrayList<Pizza>();
        discounts = new ArrayList<Discount>();
    }

    /**
     *
     * @return the order ID
     * @ensures getID = ID
     */
    public int getID()
    {
        return ID;
    }

    public ICustomer getCustomer()
    {
        return cust;

    }

    /**
     *
     * @return the type of the order
     * @ensures getType = order_type
     */
    public String getType()
    {
        return order_type;
    }


    /**
     *
     * @return the total price of the order
     * @esnures calcPrice includes all pizzas and discounts
     */
    public double calcPrice()
    {
        double price = 0.0;
        for(Pizza p : pizzas)
        {
            price += p.calcPrice();
        }

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


    /**
     *
     * @param p the pizza to add to the order
     * @ensures p is added to the order
     */
    public void addPizza(Pizza p)
    {
        pizzas.add(p);
    }

    /**
     *
     * @param d the discount to add to the order
     * @ensures d is added to the order
     */
    public void addDiscount( Discount d)
    {
        discounts.add(d);
    }

    /*
    Its bad practice to expose the arrays like this, but it will make it easier to convert the order to the format needed for your database

    Only use these functions to read the lists, never to modify them

    */

    /**
     *
     * @return the pizzas on the order
     * @requires this is only used to view the data, not to modify it
     * @ensures getPizzas includes all pizzas on the order
     */
    public ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    /**
     *
     * @return the discounts on the order
     * @requires this is only used to view the data, not to modify it
     * @ensures getDiscounts includes all discounts on the order
     */
    public ArrayList<Discount> getDiscounts()
    {
        return discounts;
    }



    /**
     * Print the high level info
     * @return a string with the order number, type, customer information, number of pizzas and the price of the order
     */
    public String toSimplePrint()
    {
        String s = "Order Number: " + Integer.toString(ID) + " Type: " + order_type;

	
        s += " Customer: " + cust.toString();

        s+= " Number of Pizzas: " + Integer.toString(pizzas.size()) + " Price: $" + Double.toString(this.calcPrice()) + "\n";
        return s;
    }

    /**
     *
     * @return A string with all order information
     */
    @Override
    public String toString()
    {
        String s = "Order Number: " + Integer.toString(ID) + " Type: " + order_type;

        s += "\n" + cust.toString();

        s += "\n\nPizzas:\n";
        for (Pizza p: pizzas)
        {
            s += p.toString() + "\n";
        }

        s += "\nOrder Discounts: \n";
        for (Discount d : discounts)
        {
            s += d.toString() + "\n";
        }

        s += "\nTotal Order Price: $" + Double.toString(this.calcPrice()) + "\n" ;
        return s;
    }
}
