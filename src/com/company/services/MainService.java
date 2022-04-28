package com.company.services;

import com.company.cards.Card;
import com.company.user.Customer;

import java.text.ParseException;
import java.util.*;

public class MainService {

    private static final List<String> loginCommands = Arrays.asList("1. Create Account", "2. Login", "3. Exit");
    private static final List<String> customerCommands = Arrays.asList("1. View account details", "2. Deposit cash", "3. Withdraw", "4. New Card", "5. New Deposit", "6. Close account", "7. Exit");
    private static final List<String> adminCommands = Arrays.asList( "1. View customer details", "2. Delete account", "3. Delete Card", "4. Exit");

    private static MainService instance;
    private final UserService userService = new UserService();

    private final List<Customer> customers = new ArrayList<>();



    public static MainService getInstance(){
        if(instance == null){
            instance = new MainService();
        }
        return instance;
    }

    public void login(){
        // dupa ce dai login se va apela meniul corespunzator tipului de cont

        // call the appropriate menu by user type

//        if()
    }

    public void viewAccountDetails(){

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

    public void createAdminAccount()
    {

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
        customers.add(newCustomer);
    }

    public void viewCustomerDetails(Customer customer){
    }

    public void deleteAccount(Customer customer){

    }

    public void deleteCard(Card card){

    }

    public void customerMenu() {

        boolean exit = false;

        System.out.println("Welcome!\n");

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
                    case "1" -> viewAccountDetails();
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

    public void adminMenu()
    {

    }



    public void loginMenu()
    {

        boolean exit = false;

        System.out.println("Genesis Bank\n");

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
