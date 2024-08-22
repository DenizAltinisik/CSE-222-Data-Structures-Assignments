public class customer extends person{
    private order[] orders;
    private int operator_ID;
    public order[] getOrders() {
        return orders;
    }
    public customer(String name, String surname, String address, String phone, int id, int op_id){
      super(name,surname,address,phone,id);
      this.operator_ID = op_id;
      this.orders = new order[100];
    };
    public int getOperator_ID() {
        return operator_ID;
    }

    public void print_customer(){


        System.out.println("Name & Surname: " +  getName() + getSurname());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getID());
        System.out.println("Operator ID: " + operator_ID);

        print_orders();
    };
    public void print_orders(){
        int numberoforders = sizeofArray();
        //System.out.println("Number of orders: " + numberoforders);
        if(numberoforders != 0){
            for(int i = 0; i < numberoforders; i++){

                System.out.print("Order #" + (i+1) + " ");
                if(orders[i] != null)
                    this.orders[i].print_order();
            }

        }
        else
            System.out.println("This customer doesn't have any order.");

        System.out.println("--------------------------");
    };
    public void define_orders(order[] temp) {
        int count = 0;
        for (int k = 0; k < temp.length; k++) {
            if (temp[k] != null) {
                count++;
            }
        }
        int j = 0;
        for (int i = 0; i < temp.length && j < count; i++) {
            if (temp[i] != null && getID() == temp[i].getCustomer_ID()) {
                orders[j] = temp[i];
                j++;
            }
        }
    }

    public int sizeofArray(){
        int numberOf0rders = 0; // Counter for non-null customers

        for (int i = 0; i < this.orders.length; i++) {
            if (this.orders[i] != null) {
                numberOf0rders++;
            }
        }
        return numberOf0rders;
    };

}
