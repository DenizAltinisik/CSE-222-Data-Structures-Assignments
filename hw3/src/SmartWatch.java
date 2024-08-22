/**
 * This class extends Common_Methods.
 */
class SmartWatch extends Common_Methods{
    /**
     *
     * @param name Name of the SmartWatch.
     * @param price Price of the SmartWatch.
     * @param quantity Quantity of the SmartWatches.
     */
    public SmartWatch(String name, double price, int quantity) {
        this.category = "Smart Watch";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}