public class order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;
    public order(String product_name, int count, int total_price, int status, int customer_ID){
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        this.customer_ID = customer_ID;
    }
    public void print_order(){
        System.out.printf("=> Product name: " + product_name + " - Count: " + count + " - Total price: " + total_price + " - Status: ");
        switch (status){
            case 0:
                System.out.print("Initialized\n");
                break;
            case 1:
                System.out.print("Processing\n");
                break;
            case 2:
                System.out.print("Completed\n");
                break;
            case 3:
                System.out.print("Cancelled\n");
                break;
        }
    }
    public int getCustomer_ID(){
        return customer_ID;
    }
}
