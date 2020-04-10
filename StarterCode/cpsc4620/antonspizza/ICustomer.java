package cpsc4620.antonspizza;

/**
 * An interface to hold information that all customers will have
 * Defines: ID : Z
 *          Type : String
 *
 * Initialization Ensures: ID set to a valid id in the database or -1
 *
 *
 * Constraints Type = DBNinja.dine_in || DBNinja.pickup || DBNinja.delivery
 */
public interface ICustomer {
    /**
     *
     * @return the ID of the customer
     * @ensures getID = ID
     */
    int getID();

    /**
     *
     * @return the type of the customer
     * @ensures getType = Type
     */
    String getType();
}
