/**
 * This class extends Common_Methods.
 */
class Smartphone extends Common_Methods{
    /**
     *
     * @param name Name of the smartphone.
     * @param price Price of the smartphone.
     * @param quantity Quantity of the smartphones.
     */
    public Smartphone(String name, double price, int quantity) {
        this.category = "Smart Phone";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}