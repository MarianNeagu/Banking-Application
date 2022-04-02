package services;

import cards.Card;
import user.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Service {

    private static final List<String> loginCommands = Arrays.asList("1. Login", "2. Exit");
    private static final List<String> customerCommands = Arrays.asList("1. View account details", "2. Deposit cash", "3. Withdraw", "4. New Card", "5. New Deposit", "6. Close account", "7. Exit");
    private static final List<String> adminCommands = Arrays.asList("1. Create account", "2. View customer details", "3. Delete account", "4. Delete Card", "5. Exit");

    private static Service instance;

    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    public void login(){
        // dupa ce dai login se va apela meniul corespunzator tipului de cont
    }

    public void viewAccountDetails(){

    }

    public void depositCash(){

    }

    public void withdraw(int amount){

    }

    public void createCard(){

    }

    public void createDeposit(){

    }

    public void closeAccount(){

    }
    
    public void createAccount(){

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
            try {
                switch (command) {
                    case "1" -> login();
                    case "2" -> login();
                    case "3" -> login();
                    case "4" -> login();
                    case "5" -> login();
                    case "6" -> exit = true;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }

    public void adminMenu() {

    }



    public void loginMenu() {

        boolean exit = false;

        System.out.println("Union Bank!\n");

        for (String loginCommand : loginCommands) {
            System.out.println(loginCommand);
        }

        while(!exit)
        {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            try {
                switch (command) {
                    case "1" -> login();
                    case "2" -> exit = true;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }


}
