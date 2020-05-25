package model;

public class Customer implements IStoreable{
    int ID;
    String firstName;
    String lastName;
    String address;
    String phone;
    String email; //maybe needs to be its own class to check for being a valid email?

    public Customer(int ID, String firstName, String lastName, String address, String phone, String email){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
