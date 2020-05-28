package model;

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
}
