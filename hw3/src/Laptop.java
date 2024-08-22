/**
 * This class extends Common_Methods.
 */
class Laptop extends Common_Methods{
    /**
     *
     * @param name Name of the Laptop.
     * @param price Price of the Laptop.
     * @param quantity Quantity of the Laptop.
     */
    public Laptop(String name, double price, int quantity) {
        this.category = "Laptop";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}