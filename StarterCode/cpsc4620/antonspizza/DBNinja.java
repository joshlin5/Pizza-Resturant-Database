package cpsc4620.antonspizza;

import java.io.*;
import java.sql.*;
import java.util.*;

/*
This file is where most of your code changes will occur
You will write the code to retrieve information from the database, or save information to the database

The class has several hard coded static variables used for the connection, you will need to change those to your connection information

This class also has static string variables for pickup, delivery and dine-in. If your database stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will ensure that the comparison is checking for the right string in other places in the program. You will also need to use these strings if you store this as boolean fields or an integer.


*/

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
    //enter your user name here
    private static String user = "uauajzno";
    //enter your password here
    private static String password = "joshdan20";
    //enter your database name here
    private static String database_name = "SmithAndLinPizzeria_2xh5";
    //Do not change the port. 3306 is the default MySQL port
    private static String port = "3306";
    private static Connection conn;

    //Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
    public final static String pickup = "PICK_UP";
    public final static String delivery = "DELIVERY";
    public final static String dine_in = "DINE_IN";

    public final static String size_s = "small";
    public final static String size_m = "medium";
    public final static String size_l = "Large";
    public final static String size_xl = "X-Large";

    public final static String crust_thin = "Thin";
    public final static String crust_orig = "Original";
    public final static String crust_pan = "Pan";
    public final static String crust_gf = "Gluten-Free";



    /**
     * This function will handle the connection to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());


            return false;
        }

        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     *
     * @param o order that needs to be saved to the database
     * @throws SQLException
     * @throws IOException
     * @requires o is not NULL. o's ID is -1, as it has not been assigned yet. The pizzas do not exist in the database
     *          yet, and the topping inventory will allow for these pizzas to be made
     * @ensures o will be assigned an id and added to the database, along with all of it's pizzas. Inventory levels
     *          will be updated appropriately
     */
    public static void addOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
        System.out.println("add order");
		/* add code to add the order to the DB. Remember to add the pizzas and discounts as well, which will involve multiple tables. Customer should already exist. Toppings will need to be added to the pizzas.

		It may be beneficial to define more functions to add an individual pizza to a database, add a topping to a pizza, etc.

		Note: the order ID will be -1 and will need to be replaced to be a fitting primary key.

		You will also need to add timestamps to your pizzas/orders in your database. Those timestamps are not stored in this program, but you can get the current time before inserting into the database

		Remember, when a new order comes in the ingredient levels for the topping need to be adjusted accordingly. Remember to check for "extra" of a topping here as well.

		You do not need to check to see if you have the topping in stock before adding to a pizza. You can just let it go negative.
        */

        // FIXME: Check that customer already exists?

        /*
        *   1. Add the order to the database
        *-/

        Stirng insert = "Insert into ORDERS Values (?, ?, ?);";
        stmt = conn.prepareStatement(insert);
        stmt.clearParameters();
        int OID = getNextOrderID();
        stmt.setInt(1, OID);
        stmt.setDouble(2, o.calcPrice());
        stmt.setDouble(3, o.calcCost());
        try {
            ResultSet rset = stmt.executeUpdate(insert);
        }
        catch (SQLException e) {
            System.out.println("Error inserting order");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return;
        }

        /*
        *   2. Add order to appropriate order type SQL table
        *   FIXME: maybe separate into functions
        *-/

        insert = "Insert into ? Values (?, ?);";

        // DINE IN
        if(o.getType().equals(DBNinja.dine_in))
        {
            if(o.getCustomer() instanceof DineInCustomer)
            {
                DineInCustomer cust = (DineInCustomer) o.getCustomer();
                List<Integer> seats = cust.getSeats();

                // insert into dine in table
                stmt = conn.prepareStatement(insert);
                stmt.clearParameters();
                stmt.setString(1, DBNinja.dine_in);
                stmt.setInt(2, OID);
                stmt.setInt(3, cust.getTableNum());
                try {
                    ResultSet rset = stmt.executeUpdate(insert);
                }
                catch (SQLException e) {
                    System.out.println("Error inserting dine in");
                    while (e != null) {
                        System.out.println("Message     : " + e.getMessage());
                        e = e.getNextException();
                    }
                    conn.close();
                    return;
                }

                // insert into seat num table
                for(Integer seat : seats)
                {
                    insert = "Insert into SEAT_NUM Values (?, ?);";
                    stmt = conn.prepareStatement(insert);
                    stmt.clearParameters();
                    stmt.setInt(1, OID);
                    stmt.setInt(2, seat);
                    try {
                        ResultSet rset = stmt.executeUpdate(insert);
                    }
                    catch (SQLException e) {
                        System.out.println("Error inserting seat num");
                        while (e != null) {
                            System.out.println("Message     : " + e.getMessage());
                            e = e.getNextException();
                        }
                        conn.close();
                        return;
                    }
                }
            }
            else
            {
                System.out.println("Error customer not instance of DineInCustomer (but should be)");
                conn.close();
                return;
            }
        }

        // PICK UP
        else if(o.getType().equals(DBNinja.pickup))
        {
            if(o.getCustomer() instanceof DineOutCustomer)
            {
                DineOutCustomer cust = (DineOutCustomer) o.getCustomer();

                // insert into dine in table
                stmt = conn.prepareStatement(insert);
                stmt.clearParameters();
                stmt.setString(1, DBNinja.pickup);
                stmt.setInt(2, OID);
                stmt.setInt(3, cust.getID());
                try {
                    ResultSet rset = stmt.executeUpdate(insert);
                }
                catch (SQLException e) {
                    System.out.println("Error inserting pick up");
                    while (e != null) {
                        System.out.println("Message     : " + e.getMessage());
                        e = e.getNextException();
                    }
                    conn.close();
                    return;
                }
            }
            else
            {
                System.out.println("Error customer not instance of DineOutCustomer (but should be)");
                conn.close();
                return;
            }
        }

        // DELIVERY
        else if(o.getType().equals(DBNinja.delivery))
        {
            if(o.getCustomer() instanceof DeliveryCustomer)
            {
                DeliveryCustomer cust = (DeliveryCustomer) o.getCustomer();

                // insert into dine in table
                stmt = conn.prepareStatement(insert);
                stmt.clearParameters();
                stmt.setString(1, DBNinja.delivery);
                stmt.setInt(2, OID);
                stmt.setInt(3, cust.getID());
                try {
                    ResultSet rset = stmt.executeUpdate(insert);
                }
                catch (SQLException e) {
                    System.out.println("Error inserting delivery");
                    while (e != null) {
                        System.out.println("Message     : " + e.getMessage());
                        e = e.getNextException();
                    }
                    conn.close();
                    return;
                }
            }
            else
            {
                System.out.println("Error customer not instance of DeliveryCustomer (but should be)");
                conn.close();
                return;
            }
        }

        // error handling
        else
        {
            System.out.println("Error order type not recognized");
            conn.close();
            return;
        }

        /*
        *   3. Add order discounts
        *   TODO
        */

        /*
        *   4. Add pizzas (and therefore toppings and discounts)
        *   TODO
        */

        conn.close();
    }

    /**
     *
     * @param c the new customer to add to the database
     * @throws SQLException
     * @throws IOException
     * @requires c is not null. C's ID is -1 and will need to be assigned
     * @ensures c is given an ID and added to the database
     */
    public static void addCustomer(ICustomer c) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add the customer to the DB.
		Note: the ID will be -1 and will need to be replaced to be a fitting primary key
		Note that the customer is an ICustomer data type, which means c could be a dine in, carryout or delivery customer
        */
        
        /*
        *   FIXME check for existing customer?
        *   1. Get fitting ID (max+1)
        *   2a. Handle details of customer according to what type customer (dine in, pickup, or delivery)
        *   2b. Add customer to DB
        */

        String insert = "Insert into CUSTOMER Values (?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(insert);
        stmt.clearParameters();
        int CID = getNextCustomerID();
        stmt.setInt(1, CID);

        // last 3 params depend on type of customer
        if(c instanceof DineInCustomer)
        {
            stmt.setNull(2, java.sql.Types.VARCHAR);
            stmt.setNull(3, java.sql.Types.CHAR);
            stmt.setNull(4, java.sql.Types.VARCHAR);
        }
        else if(c instanceof DineOutCustomer)
        {
            DineOutCustomer cust = (DineOutCustomer)c;
            stmt.setString(2, cust.getName());
            stmt.setString(3, cust.getPhone());
            stmt.setNull(4, java.sql.Types.VARCHAR);
        }
        else if(c instanceof DeliveryCustomer)
        {
            DeliveryCustomer cust = (DeliveryCustomer)c;
            stmt.setString(2, cust.getName());
            stmt.setString(3, cust.getPhone());
            stmt.setString(4, cust.getAddress());
        }
        else
        {
            System.out.println("Error add customer type not recognized");
            conn.close();
            return;
        }

        try {
            ResultSet rset = stmt.executeUpdate(insert);
        }
        catch (SQLException e) {
            System.out.println("Error inserting customer");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }

        conn.close();
    }

    /**
     *
     * @param o the order to mark as complete in the database
     * @throws SQLException
     * @throws IOException
     * @requires the order exists in the database
     * @ensures the order will be marked as complete
     */
    public static void CompleteOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to mark an order as complete in the DB. You may have a boolean field for this, or maybe a completed time timestamp. However you have it, */
        System.out.println("complete order");
        conn.close();
    }

    /**
     *
     * @param t the topping whose inventory is being replenished
     * @param toAdd the amount of inventory of t to add
     * @throws SQLException
     * @throws IOException
     * @requires t exists in the database and toAdd > 0
     * @ensures t's inventory level is increased by toAdd
     */
    public static void AddToInventory(Topping t, double toAdd) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add toAdd to the inventory level of T. This is not adding a new topping, it is adding a certain amount of stock for a topping. This would be used to show that an order was made to replenish the restaurants supply of pepperoni, etc*/
        System.out.println("add to inventory");
        conn.close();
    }


    /*
        A function to get the list of toppings and their inventory levels. I have left this code "complete" as an example of how to use JDBC to get data from the database. This query will not work on your database if you have different field or table names, so it will need to be changed

        Also note, this is just getting the topping ids and then calling getTopping() to get the actual topping. You will need to complete this on your own

        You don't actually have to use and write the getTopping() function, but it can save some repeated code if the program were to expand, and it keeps the functions simpler, more elegant and easy to read. Breaking up the queries this way also keeps them simpler. I think it's a better way to do it, and many people in the industry would agree, but its a suggestion, not a requirement.
    */

    /**
     *
     * @return the List of all toppings in the database
     * @throws SQLException
     * @throws IOException
     * @ensures the returned list will include all toppings and accurate inventory levels
     */
    public static ArrayList<Topping> getInventory() throws SQLException, IOException
    {
        //start by connecting
        connect_to_db();
        ArrayList<Topping> ts = new ArrayList<Topping>();
        //create a string with out query, this one is an easy one
        String query = "Select Name From TOPPING;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					/*Use getInt, getDouble, getString to get the actual value. You can use the column number starting with 1, or use the column name as a string

					NOTE: You want to use rset.getInt() instead of Integer.parseInt(rset.getString()), not just because it's shorter, but because of the possible NULL values. A NUll would cause parseInt to fail

					If there is a possibility that it could return a NULL value you need to check to see if it was NULL. In this query we won't get nulls, so I didn't. If I was going to I would do:

					int ID = rset.getInt(1);
					if(rset.wasNull())
					{
						//set ID to what it should be for NULL, and whatever you need to do.
					}

					NOTE: you can't check for NULL until after you have read the value using one of the getters.

					*/
                int name = rset.getString(1);
                //Now I'm just passing my primary key to this function to get the topping itself individually
                ts.add(getTopping(name));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return ts;
        }


        //end by closing the connection
        conn.close();
        return ts;
    }

    /**
     *
     * @return a list of all orders that are currently open in the kitchen
     * @throws SQLException
     * @throws IOException
     * @ensures all currently open orders will be included in the returned list.
     */
    public static ArrayList<Order> getCurrentOrders() throws SQLException, IOException
    {
        connect_to_db();

        ArrayList<Order> os = new ArrayList<Order>();
		/*add code to get a list of all open orders. Only return Orders that have not been completed. If any pizzas are not completed, then the order is open.*/
        System.out.println("get current orders");
        conn.close();
        return os;
    }

    /**
     *
     * @param size the pizza size
     * @param crust the type of crust
     * @return the base price for a pizza with that size and crust
     * @throws SQLException
     * @throws IOException
     * @requires size = size_s || size_m || size_l || size_xl AND crust = crust_thin || crust_orig || crust_pan || crust_gf
     * @ensures the base price for a pizza with that size and crust is returned
     */
    public static double getBasePrice(String size, String crust) throws SQLException, IOException
    {
        connect_to_db();
        double bp = 0.0;
        //add code to get the base price for that size and crust pizza Depending on how you store size and crust in your database, you may have to do a conversion
        System.out.println("get base price");
        conn.close();
        return bp;
    }

    /**
     *
     * @return the list of all discounts in the database
     * @throws SQLException
     * @throws IOException
     * @ensures all discounts are included in the returned list
     */
    public static ArrayList<Discount> getDiscountList() throws SQLException, IOException
    {
        ArrayList<Discount> discs = new ArrayList<Discount>();
        connect_to_db();
        //add code to get a list of all discounts
        System.out.println("get discount list");
        conn.close();
        return discs;
    }

    /**
     *
     * @return the list of all delivery and carry out customers
     * @throws SQLException
     * @throws IOException
     * @ensures the list contains all carryout and delivery customers in the database
     */
    public static ArrayList<ICustomer> getCustomerList() throws SQLException, IOException
    {
        ArrayList<ICustomer> custs = new ArrayList<ICustomer>();
        connect_to_db();
        //add code to get a list of all customers
        System.out.println("get customer list");
        conn.close();
        return custs;
    }



	/*
	Note: The following incomplete functions are not strictly required, but could make your DBNinja class much simpler. For instance, instead of writing one query to get all of the information about an order, you can find the primary key of the order, and use that to find the primary keys of the pizzas on that order, then use the pizza primary keys individually to build your pizzas. We are no longer trying to get everything in one query, so feel free to break them up as much as possible

	You could also add functions that take in a Pizza object and add that to the database, or take in a pizza id and a topping id and add that topping to the pizza in the database, etc. I would recommend this to keep your addOrder function much simpler

	These simpler functions should still not be called from our menu class. That is why they are private

	We don't need to open and close the connection in these, since they are only called by a function that has opened the connection and will close it after
	*/

	
    private static Topping getTopping(String name) throws SQLException, IOException
    {

        //add code to get a topping
		//the java compiler on unix does not like that t could be null, so I created a fake topping that will be replaced
        Topping t = new Topping("fake", 0.25, 100.0, -1);
		String query = "Select Price, Inventory From TOPPING where Name = " + name + ";";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					double price = rset.getDouble(1);
					double inv = rset.getDouble(2);
					
					t = new Topping(name, price, inv, -1);
			}
			
		}
		catch (SQLException e) {
            System.out.println("Error loading Topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return t;
        }
		
        return t;

    }
/*
    private static Discount getDiscount()  throws SQLException, IOException
    {

        //add code to get a discount

        Discount D;

        return D;

    }

    private static Pizza getPizza()  throws SQLException, IOException
    {

        //add code to get Pizza Remember, a Pizza has toppings and discounts on it
        Pizza P;

        return P;

    }

    private static ICustomer getCustomer()  throws SQLException, IOException
    {

        //add code to get customer


        ICustomer C;

        return C;


    }

    private static Order getOrder()  throws SQLException, IOException
    {

        //add code to get an order. Remember, an order has pizzas, a customer, and discounts on it


        Order O;

        return O;

    }
    */

    private static int getNextOrderID()
    {
        int orderID = -1;
        String query = "Select Max(OID) From ORDERS;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        try {
            ResultSet rset = stmt.executeQuery();
            while(rset.next())
            {
                orderID = rset.getInt(1);
                if(rset.wasNull()) // empty table
                {
                    orderID = 1;
                }
                else
                {
                    orderID++;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error getting order count");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return orderID;
        }
        return orderID;
    }

    private static int getNextCustomerID()
    {
        int custID = -1;
        String query = "Select Max(CID) From CUSTOMER;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        try {
            ResultSet rset = stmt.executeQuery();
            while(rset.next())
            {
                custID = rset.getInt(1);
                if(rset.wasNull()) // empty table
                {
                    custID = 1;
                }
                else
                {
                    custID++;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error getting customer count");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return custID;
        }
        return custID;
    }

}
