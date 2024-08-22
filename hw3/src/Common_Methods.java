/**
 * A dummy abstract class that is used not to override methods for multiple times.
 */
public abstract class Common_Methods implements Device {
    protected String category;
    protected String name;
    protected double price;
    protected int quantity;

    /**
     * Default constructor.
     * Initializes category, name, price, and quantity to default values.
     */
    public Common_Methods() {
        this.category = null;
        this.name = null;
        this.price = 0;
        this.quantity = 0;
    }

    /**
     * Constructor with parameters.
     * Initializes category, name, price, and quantity with specified values.
     * @param category The category of the device.
     * @param name The name of the device.
     * @param price The price of the device.
     * @param quantity The quantity of the device.
     */
    public Common_Methods(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the device, Time complexity: O(1).
     * @return The category of the device.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the device, Time complexity: O(1).
     * @param category The new category of the device.
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the name of the device, Time complexity: O(1).
     * @return The name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the device, Time complexity: O(1).
     * @param name The new name of the device.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the device, Time complexity: O(1).
     * @return The price of the device.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the device, Time complexity: O(1).
     * @param price The new price of the device.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the quantity of the device, Time complexity: O(1).
     * @return The quantity of the device.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the device, Time complexity: O(1).
     * @param quantity The new quantity of the device.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
