package cpsc4620.antonspizza;

/**
 * Correspondences Name = name
 *                  Phone = phone
 */
public class DineOutCustomer extends AbsCustomer implements IDineOutCustomer {
    protected String name;
    protected String phone;

    /**
     * Only included as DineOutCustomer needs a default constructor so it can be extended
     */
    public DineOutCustomer()
    {
        ID = -1;
        name = "no name";
        phone = "no phone";
        type = DBNinja.dine_in;
    }

    /**
     *
     * @param i the customer id
     * @param n the customer name
     * @param p the customer phone number
     * @requires i is a valid id or -1
     * @ensures ID = i and name = n and phone = p and type = DBNinja.pickup
     */
    public DineOutCustomer(int i, String n, String p)
    {
        ID = i;
        name = n;
        phone = p;
        type = DBNinja.pickup;

    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString()
    {
        String s = "Customer: " + name + " Phone: " + phone;
        return s;
    }

}
