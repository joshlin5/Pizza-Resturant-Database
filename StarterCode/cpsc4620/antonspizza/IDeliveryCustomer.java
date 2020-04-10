package cpsc4620.antonspizza;

/**
 * IDeliveryCustomer extends IDineOutCustomer and adds the ability to have an address
 *
 * @Defines: Address: String
 *
 * Initialization Ensures: Address is set
 */
public interface IDeliveryCustomer extends IDineOutCustomer {
    /**
     *
     * @return the customer's address
     * @ensures getAddress = Address
     */
    String getAddress();

}
