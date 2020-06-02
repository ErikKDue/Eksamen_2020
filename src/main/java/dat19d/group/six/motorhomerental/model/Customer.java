package dat19d.group.six.motorhomerental.model;

import annotations.StoreableAttribute;

public class Customer implements IStoreable{
    @StoreableAttribute
    public int customerID;
    @StoreableAttribute
    public String firstName;
    @StoreableAttribute
    public String lastName;
    @StoreableAttribute
    public String address;
    @StoreableAttribute
    public String phone;
    @StoreableAttribute
    public String email; //maybe needs to be its own class to check for being a valid email?

    public Customer(){}

    public Customer(String firstName, String lastName, String address, String phone, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int ID, String firstName, String lastName, String address, String phone, String email){
        this.customerID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Override //returns the name of the SQL TABLE
    public String getType() {
        return "customer";
    }

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getAddress() {return address;}

    public String getPhone() {return phone;}

    public String getEmail() {return email;}


}
