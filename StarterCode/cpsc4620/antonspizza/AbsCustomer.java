package cpsc4620.antonspizza;

/**
 * An abstract class that includes the ID and customer type that all customers can extend
 *
 * Correspondences: ID = ID
 *                  Type = type
 */
public abstract class AbsCustomer implements ICustomer{
    protected int ID;
    protected String type;

    public int getID()
    {
        return ID;
    }

    public String getType()
    {
        return type;
    }

}
