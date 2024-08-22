public class corporate_customer extends customer{
    public String company_name;

    public corporate_customer(String name, String surname, String address, String phone, int id, int op_id, String comp_name) {
        super(name, surname, address, phone, id, op_id);
        this.company_name = comp_name;
    };
    @Override
    public void print_customer(){


            System.out.println("Name & Surname: " + getName() + " " + getSurname());
            System.out.println("Address: " + getAddress());
            System.out.println("Phone: " + getPhone());
            System.out.println("ID: " + getID());
            System.out.println("Operator ID: " + getOperator_ID());
            System.out.println("Company name: " + company_name);

            print_orders();

        };

    }

