/**
 * This class extends Common_Methods.
 */
class TV extends Common_Methods{
    /**
     *
     * @param name Name of the TV.
     * @param price Price of the TV.
     * @param quantity Quantity of the TV.
     */
    public TV(String name, double price, int quantity) {
        this.category = "TV";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}