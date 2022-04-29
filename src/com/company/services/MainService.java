package com.company.services;

import com.company.cards.Card;
import com.company.user.Admin;
import com.company.user.Customer;
import com.company.user.User;

import java.text.ParseException;
import java.util.*;

public class MainService {

    private static final List<String> loginCommands = Arrays.asList("1. Create Account", "2. Login", "3. Exit");
    private static final List<String> customerCommands = Arrays.asList("1. View account details", "2. Deposit cash", "3. Withdraw", "4. New Card", "5. New Deposit", "6. Close account", "7. Exit");
    private static final List<String> adminCommands = Arrays.asList( "1. View customer details", "2. Delete account", "3. Delete Card", "4. Exit");

    private static MainService instance;
    private final UserService userService = new UserService();

    private final List<User> users = new ArrayList<>();


    public static MainService getInstance(){
        if(instance == null){
            instance = new MainService();
        }
        return instance;
    }

    private User getUserByEmailAndPassword(String email, String password)
    {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password))
                return user;
        }
        return null;
    }


    public void login(){
        Scanner in = new Scanner(System.in);
        // call the appropriate menu by user type after logged in

        System.out.println("Enter email address: ");
        String email = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();

        User loggedUser = getUserByEmailAndPassword(email, password);

        while(loggedUser == null)
        {
            System.out.println("Email/Password not found. Try again.");
            System.out.println("Enter email address: ");
            email = in.nextLine();
            System.out.println("Enter password: ");
            password = in.nextLine();
            loggedUser = getUserByEmailAndPassword(email, password);
        }
        if(Objects.equals(loggedUser.getTypeOfUser(), "customer"))
            customerMenu((Customer) loggedUser);
        else adminMenu((Admin) loggedUser);
    }

    public void depositCash(int ammount){

    }

    public void withdraw(double amount){

    }

    public void createCard(){

    }

    public void createDeposit(){

    }

    public void closeAccount(){

    }
    
    public void createAccount() throws ParseException {
        // choose what type of accout to create:
        Scanner in = new Scanner(System.in);

        System.out.println("Type of account (1 - customer, 2 - admin): ");
        String command = in.nextLine();
        while(!Objects.equals(command, "1") || !Objects.equals(command, "2"))
        {
            if (Objects.equals(command, "1"))
            {
                createCustomerAccount();
                break;
            }
            else if (Objects.equals(command, "2"))
            {
                createAdminAccount();
                break;
            }
            else
            {
                System.out.println("Command unknown. Please try again");
                System.out.println("Type of account (1 - customer, 2 - admin): ");
                command = in.nextLine();
            }
        }

        // after the account creation, return to the login panel
        loginMenu();
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


        Admin newAdmin = this.userService.createAdmin(firstName, lastName, email, password);
        users.add(newAdmin);
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

        Customer newCustomer = this.userService.createCustomer(firstName, lastName, email, password, cnp, phoneNumber);
        users.add(newCustomer);
    }

    public void viewCustomerDetails(Customer customer)
    {
        System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Identified by CNP: " + customer.getCnp());
        System.out.println("Email address: " + customer.getEmail());
        System.out.println("Phone number: " + customer.getPhoneNumber());
    }

    public void deleteAccount(Customer customer){

    }

    public void deleteCard(Card card){

    }

    public void customerMenu(Customer loggedCustomer) {

        boolean exit = false;

        System.out.println("Welcome, " + loggedCustomer.getFirstName() + "!\n");

        for (String customerCommand : customerCommands) {
            System.out.println(customerCommand);
        }

        while(!exit)
        {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            try
            {
                switch (command)
                {
                    case "1" -> viewCustomerDetails(loggedCustomer);
                    case "2" -> {
                        System.out.println("Enter the ammount to deposit: ");
                        Scanner in_ammount = new Scanner(System.in);
                        int ammount = Integer.parseInt(in_ammount.nextLine());
                        depositCash(ammount);
                    }
                    case "3" -> {
                        System.out.println("Enter the ammount to withdraw: ");
                        Scanner in_ammount = new Scanner(System.in);
                        double ammount = Double.parseDouble(in_ammount.nextLine());
                        withdraw(ammount);
                    }
                    case "4" -> createCard();
                    case "5" -> createDeposit();
                    case "6" -> closeAccount();
                    case "7" -> exit = true;
                }
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }

        }
    }

    public void adminMenu(Admin loggedUser)
    {

    }


    public void loginMenu()
    {

        boolean exit = false;

        System.out.println("~Genesis Bank~\n");

        for (String loginCommand : loginCommands)
        {
            System.out.println(loginCommand);
        }

        while(!exit)
        {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            try
            {
                switch (command)
                {
                    case "1" -> {
                        exit = true;
                        createAccount();
                    }
                    case "2" -> {
                        exit = true;
                        login();
                    }
                    case "3" -> exit = true;
                }
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }

        }
    }


}
