package cpsc4620.antonspizza;

/**
 * IDineOutCustomer extends ICustomer to inherit the basic customer information
 * ADineOutCustomer has a phone and name, so they would be able to place orders for pickup
 *
 * Defines: Phone: String
 *          Name: String
 *
 * Initialization Ensures Phone and Name are given values
 */

public interface IDineOutCustomer extends ICustomer {
    /**
     *
     * @return the customer phone number
     * @ensures getPhone = Phone
     */
    String getPhone();

    /**
     *
     * @return the customers name
     * @ensures getName = name
     */
    String getName();

}
