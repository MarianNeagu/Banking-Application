package com.company.services;

import com.company.cards.Card;
import com.company.cards.PremiumCard;
import com.company.cards.StandardCard;
import com.company.user.Admin;
import com.company.user.Customer;
import com.company.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainService {

    private static final List<String> loginCommands = Arrays.asList("1. Create Account", "2. Login", "3. Exit");
    private static final List<String> customerCommands = Arrays.asList("1. View account details", "2. Deposit cash", "3. Withdraw", "4. New Card", "5. New Deposit", "6. Logout", "7. Exit");
    private static final List<String> adminCommands = Arrays.asList( "1. View customer details", "2. Delete account", "3. Delete Card", "4. Exit");

    private static MainService instance;
    private final UserService userService = new UserService();
    private final CardService cardService = new CardService();

    private final List<User> users = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();


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

    public void depositCash(double ammount, Customer loggedCustomer)
    {
        boolean hasCard = false;

        for (Card card : cards)
        {
            if (card.getUserUniqueId() == loggedCustomer.getUniqueId())
            {
                hasCard = true;
                break;
            }
        }
        if (!hasCard)
        {
            System.out.println("You don't have any cards in which to deposit money!\n");
            customerMenu(loggedCustomer);
            return;
        }
        int cardIndex = 1;
        System.out.println("Select the card in which to deposit:");
        for (Card card : cards)
        {
            if (card.getUserUniqueId() == loggedCustomer.getUniqueId())
            {
                System.out.println(cardIndex + ". Card number: " + card.getCardNumber());
                System.out.println("Current amount: " + card.getAmount() + "\n");
                cardIndex++;
            }
        }

        Scanner in = new Scanner(System.in);
        int command = Integer.parseInt(in.nextLine());
        while (command < 1 || command >= cardIndex)
        {
            System.out.println("Enter a valid number!");
            command = Integer.parseInt(in.nextLine());
        }

        cardIndex = 1;
        for (Card card : cards) {
            if (card.getUserUniqueId() == loggedCustomer.getUniqueId()) {
                if (cardIndex == command) {
                    // increment the amount
                    card.setAmount(card.getAmount() + ammount);
                    break;
                } else cardIndex++;
            }
        }

        customerMenu(loggedCustomer);
    }

    public void withdraw(double amount, Customer loggedCustomer){

    }

    public void createStandardCard(long uniqueId) throws ParseException
    {
        Random rand = new Random();
        int rand_month = rand.nextInt(1,13); // generate random month for exp date



        Scanner in = new Scanner(System.in);
        StringBuilder cardNumber = new StringBuilder();
        Date expirationDate = new Date(2025, rand_month, 1);

        double amount, withdrawFee;

        for(int i = 0; i < 16; i++)
        {
            int rand_number = rand.nextInt(9);
            cardNumber.append(rand_number);
        }

        System.out.println("Amount to deposit ($): ");
        amount = Double.parseDouble(in.nextLine());
        System.out.println("Withdrawal fee (%): ");
        withdrawFee = Double.parseDouble(in.nextLine());


        StandardCard newStandardCard = this.cardService.createStandardCard(uniqueId, String.valueOf(cardNumber), expirationDate, amount, withdrawFee);
        cards.add(newStandardCard);
    }
    public void createPremiumCard(long uniqueId) throws ParseException
    {
        Random rand = new Random();
        int rand_month = rand.nextInt(1,13); // generate random month for exp date

        Scanner in = new Scanner(System.in);
        StringBuilder cardNumber = new StringBuilder();
        Date expirationDate = new Date(2025, rand_month, 1);

        double amount, cashBack;

        for(int i = 0; i < 16; i++)
        {
            int rand_number = rand.nextInt(9);
            cardNumber.append(rand_number);
        }

        System.out.println("Amount to deposit ($): ");
        amount = Double.parseDouble(in.nextLine());
        System.out.println("Cashback  (%): ");
        cashBack = Double.parseDouble(in.nextLine());

        PremiumCard newPremiumCard = this.cardService.createPremiumCard(uniqueId, String.valueOf(cardNumber), expirationDate, amount, cashBack);
        cards.add(newPremiumCard);
    }

    public void createCard(Customer customer) throws ParseException {
        // choose what type of card to create:
        Scanner in = new Scanner(System.in);

        System.out.println("Type of card (1 - standard, 2 - premium): ");
        String command = in.nextLine();
        while(!Objects.equals(command, "1") || !Objects.equals(command, "2"))
        {
            if (Objects.equals(command, "1"))
            {
                createStandardCard(customer.getUniqueId());
                break;
            }
            else if (Objects.equals(command, "2"))
            {
                createPremiumCard(customer.getUniqueId());
                break;
            }
            else
            {
                System.out.println("Command unknown. Please try again");
                System.out.println("Type of card (1 - standard, 2 - premium): ");
                command = in.nextLine();
            }
        }

        // after the card creation, return to the customer menu
        customerMenu(customer);
    }

    public void deleteCard(){

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

        for (Card card : cards) {
            if (card.getUserUniqueId() == customer.getUniqueId()) {
                System.out.println("\nCard details: ");
                System.out.println("Card number: " + card.getCardNumber());

                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
                String formattedDate = formatter.format(card.getExpirationDate());

                System.out.println("Exp. Date: " + formattedDate);
                System.out.println("Amount on card: " + card.getAmount());
            }
        }
        customerMenu(customer);
    }

    public void customerMenu(Customer loggedCustomer) {


        System.out.println("Welcome, " + loggedCustomer.getFirstName() + "!\n");

        for (String customerCommand : customerCommands) {
            System.out.println(customerCommand);
        }

        while(true)
        {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            try
            {
                switch (command)
                {
                    case "1" -> viewCustomerDetails(loggedCustomer);
                    case "2" -> {
                        System.out.println("Enter the amount to deposit: ");
                        Scanner in_amount = new Scanner(System.in);
                        double amount = Double.parseDouble(in_amount.nextLine());
                        depositCash(amount, loggedCustomer);
                    }
                    case "3" -> {
                        System.out.println("Enter the amount to withdraw: ");
                        Scanner in_amount = new Scanner(System.in);
                        double amount = Double.parseDouble(in_amount.nextLine());
                        withdraw(amount, loggedCustomer);
                    }
                    case "4" -> createCard(loggedCustomer);
                    case "5" -> createDeposit();
                    case "6" -> loginMenu();
                    case "7" -> System.exit(0);
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

        System.out.println("\n~Genesis Bank~\n");

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
                    case "3" -> System.exit(0);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }

        }
    }


}
