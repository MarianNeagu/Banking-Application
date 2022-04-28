package com.company.services;

import com.company.user.Customer;
import com.company.user.Admin;

import java.text.ParseException;
import java.util.Scanner;

public class UserService {

    private static long uniqueId = 0;

    public UserService(){
    }

    public Customer createCustomer(String firstName, String lastName, String email, String password, String cnp, String phoneNumber) throws ParseException {
        return new Customer(++uniqueId, firstName, lastName, email, password, cnp, phoneNumber);
    }
}




