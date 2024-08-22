public class operator extends person{
    private int wage;
    private customer[] customers;
    public operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
        this.customers = new customer[100];
    }
    public void print_operator(){
        System.out.println(" *** Operator Screen ***");
        System.out.println("--------------------------");
        System.out.println("Name & Surname: " +  getName() + getSurname());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getID());
        System.out.println("Wage: " + wage);
        System.out.println("--------------------------");
        print_customers();

    };
    public void define_customers(customer[] temp) {
        int count = 0;
        for (int k = 0; k < temp.length; k++) {
            if (temp[k] != null) {
                count++;
            }
        }
        int j = 0;
        for (int i = 0; i < temp.length && j < count; i++) {
            if (temp[i] != null && getID() == temp[i].getOperator_ID()) {
                customers[j] = temp[i];
                j++;
            }
        }
    }


    public void print_customers(){
        int numberOfCustomers = sizeofArray();
        //System.out.println("Number of customers: " + numberOfCustomers);

        if(numberOfCustomers != 0){
            for(int i = 0; i < numberOfCustomers; i++){

                System.out.print("Customer #" + (i+1) + " ");
                if(this.customers[i] != null) {
                    if(this.customers[i] instanceof retail_customer)
                        System.out.println("(a retail customer):");
                    else System.out.println("(a corporate customer):");

                    this.customers[i].print_customer();
                }
//                else{
//                    System.out.println("boş");
//                }
            }
        }
        else
            System.out.println("This operator doesn't have any customer.");
        System.out.println("--------------------------");
    }
    public int sizeofArray(){
        int numberOfCustomers = 0; // Counter for non-null customers

        for (int i = 0; i < this.customers.length; i++) {
            if (this.customers[i] != null) {
                numberOfCustomers++;
            }
        }
        return numberOfCustomers;
    };

}
