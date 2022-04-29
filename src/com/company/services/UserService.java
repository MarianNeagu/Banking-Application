package com.company.services;

import com.company.cards.Card;
import com.company.user.Customer;
import com.company.user.Admin;
import com.company.user.User;

import java.text.ParseException;
import java.util.*;

public class UserService {

    private static long uniqueId = 0;

    private static UserService instance;
    private final List<User> users = new ArrayList<>();


    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public Customer createCustomer(String firstName, String lastName, String email, String password, String cnp, String phoneNumber) throws ParseException {
        return new Customer(++uniqueId, firstName, lastName, email, password, cnp, phoneNumber);
    }

    public Admin createAdmin(String firstName, String lastName, String email, String password) throws ParseException {
        return new Admin(++uniqueId, firstName, lastName, email, password);
    }



    public User getUserByEmailAndPassword(String email, String password)
    {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password))
                return user;
        }
        return null;
    }

    public void createCustomerAccount() throws ParseException {
        Scanner in = new Scanner(System.in);
        String firstName, lastName, email, password, cnp, phoneNumber;

        System.out.println("First name: ");
        firstName = in.nextLine();
        System.out.println("Last name: ");
        lastName = in.nextLine();
        System.out.println("CNP: ");
        cnp = in.nextLine();
        System.out.println("Email address: ");
        email = in.nextLine();
        System.out.println("Password: ");
        password = in.nextLine();
        System.out.println("Phone number: ");
        phoneNumber = in.nextLine();

        Customer newCustomer = createCustomer(firstName, lastName, email, password, cnp, phoneNumber);
        users.add(newCustomer);
    }

    public void createAdminAccount() throws ParseException {
        Scanner in = new Scanner(System.in);
        String firstName, lastName, email, password, cnp, phoneNumber;

        System.out.println("First name: ");
        firstName = in.nextLine();
        System.out.println("Last name: ");
        lastName = in.nextLine();
        System.out.println("Email address: ");
        email = in.nextLine();
        System.out.println("Password: ");
        password = in.nextLine();


        Admin newAdmin = createAdmin(firstName, lastName, email, password);
        users.add(newAdmin);
    }


}




