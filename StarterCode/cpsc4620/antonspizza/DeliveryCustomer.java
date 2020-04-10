package cpsc4620.antonspizza;

/**
 * Correspondences: address = Address
 */
public class DeliveryCustomer extends DineOutCustomer implements IDeliveryCustomer {
    private String address;

    /**
     *
     * @param i the customers ID
     * @param n the customers name
     * @param p the customers phone
     * @param a the customers address
     *
     * @ensures ID = i and name = n and phone = p and address = a and type = DBNinja.delivery
     */
    public DeliveryCustomer(int i, String n, String p, String a)
    {
        ID = i;
        name = n;
        phone = p;
        address = a;
        type = DBNinja.delivery;
    }

    public String getAddress()
    {
        return address;
    }


    @Override
    public String toString()
    {
        String s = "Customer " + name + " Phone: " + phone + " Address: " + address;
        return s;
    }
}
