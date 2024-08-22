/**
 * Interface that the abstract class "Common_Methods" implements.
 */
public interface Device {
    /**
     * Returns the name of the device.
     * @return name of the device
     */
    public String getName();

    /**
     * Modifies the name of the device.
     * @param name the new name of the device
     */
    public void setName(String name);

    /**
     * Returns the price of the device.
     * @return price of the device
     */
    public double getPrice();

    /**
     * Sets the price of the device.
     * @param price the new price of the device
     */
    public void setPrice(double price);

    /**
     * Returns the quantity of the device.
     * @return quantity of the device
     */
    public int getQuantity();

    /**
     * Sets the quantity of the device.
     * @param quantity the new quantity of the device
     */
    public void setQuantity(int quantity);

    /**
     * Increments the quantity of the device by 1.
     */


    /**
     * Returns the category of the device.
     * @return category of the device
     */
    public String getCategory();

    /**
     * Sets the category of the device.
     * @param category the new category of the device
     */
    public void setCategory(String category);
}
