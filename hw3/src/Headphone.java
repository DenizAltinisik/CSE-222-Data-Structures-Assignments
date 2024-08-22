/**
 * This class extends Common_Methods.
 */
class Headphone extends Common_Methods{
    /**
     *
     * @param name Name of the Headphone.
     * @param price Price of the Headphone.
     * @param quantity Quantity of the Headphones.
     */
    public Headphone(String name, double price, int quantity) {
        this.category = "Headphones";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}