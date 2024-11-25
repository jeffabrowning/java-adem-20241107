/*
Automation training assignment #3: 11/07/24 (1 new question by Adem)


Question: Bank Account Management System
Problem:
    You are tasked with creating a Bank Account Management System using Object-Oriented Programming (OOP) concepts.
    The system will allow multiple customers to create and manage bank accounts, deposit and withdraw money, and track their balances.
    Your task is to design and implement the following: (see details in Confluence page)
*/

//Main class imports:
import java.util.Scanner;                                                                       //import scanner class


public class Main {

    /*
    see BankAccount class
    see Bank Class
    Main class:
        In the main class, provide a simple text-based menu system that allows users to interact with the Bank System.
            Menu Options:
                Create Account
                Deposit Money
                Withdraw Money
                Transfer Money
                View Account Details
                List All Accounts
                Delete Account
                Exit
            Use a loop to keep displaying the menu until the user chooses to exit the program.
            Implement appropriate if conditions to ensure valid user inputs.
            */


    //Main class fields:
    static int selection;                                                                       //declare variable for scanner input and switch


    //Main class methods:
    public static void main(String[] args) {        //OVERALL METHOD AND EACH SWITCH CASE COMPLETED AND VERIFIED--REVIEW AND COMMENT ALL
        //display user menu, prompt for input, triggers appropriate transactions, and exit program

        Scanner scanner = new Scanner(System.in);                                               //instantiate scanner
        System.out.println("\nWelcome to Your Friendly Neighborhood Bank!");                    //display initial greeting

        //loop for initial menu display, and redisplay after each invalid entry or successful transaction (except for exit)
        do {

            //loop to validate menu selection
            do {
                displayMenu();                                                                  //call method to display menu of options
                selection = 0;                                                                  //assign/reassign selection variable to an invalid value before looping
                if (scanner.hasNextInt()) {                                                     //check if user input is an integer
                    selection = scanner.nextInt();                                              //if so, reassign selection variable and feed input to switch below
                } else {
                    System.out.println("\nInvalid entry. Please enter a number 1-8.\n");        //if not, prompt to enter a valid selection
                    scanner.nextLine();                                                         //clear newline
                }
            }
            while (selection == 0);                                                             //loop iterates until integer selection made

            //9-way switch to trigger appropriate transactions or else prompt user for valid input
            switch (selection) {
                case 1:     //COMPLETED, VERIFIED, COMMENTED
                    //create account workflow--calls BankAccount.createAccount

                    //validates name input
                    scanner.nextLine();                                                         //clear linebreak from menu selection
                    System.out.print("\nEnter account holder name: ");                          //prompt user for name input
                    String name = scanner.nextLine();                                           //assign name variable from input
                    while (name.isEmpty()) {                                                    //validation loop to reprompt if name is left blank; allows any string
                        System.out.print("Name field is blank. Please enter an account holder name, or EXIT to return to main menu: ");
                        name = scanner.nextLine();                                              //assign name variable from input
                    }
                    if (name.equals("EXIT")) {                                                  //allows user to exit to main menu by entering string value
                        System.out.println();
                        break;
                    }

                    //validates account type input
                    System.out.print("Enter account type (Checking or Savings): ");             //prompt user for account type input
                    String type = scanner.nextLine();                                           //assign type variable from input
                    while (!(type.equals("Checking") || type.equals("Savings") || type.equals("EXIT"))) {              //validation loop to reprompt if invalid account type entered
                        System.out.print("Invalid account type. Please enter Checking or Savings, or EXIT to return to main menu: ");
                        type = scanner.nextLine();                                              //assign type variable from input
                    }
                    if (type.equals("EXIT")) {                                                  //allows user to exit to main menu by entering string value
                        System.out.println();
                        break;
                    }

                    //post-validation account creation method
                    Bank.createAccount(name, type);                                             //call the method (from class, not object) to create account passing the name and type
                    break;                                                                      //break switch to redisplay menu

                case 2:     //COMPLETED, VERIFIED, COMMENTED
                    //deposit workflow--calls BankAccount.deposit & .getBalance

                    //validates account number input
                    scanner.nextLine();                                                         //clear linebreak from menu selection
                    System.out.print("\nEnter account number: ");                               //prompt user for account number input
                    String number = scanner.nextLine();                                         //assign number variable from input--validations to follow
                    while (Bank.findAccount(number) == null && !(number.equals("EXIT"))) {      //loops for account that doesn't exist
                        System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                        number = scanner.nextLine();                                            //reassigns number with new input
                    }
                    if (number.equals("EXIT")) {                                                //allows user to exit to main menu by entering string value
                        System.out.println();
                        break;
                    }
                    System.out.printf("\nCurrent balance: $%,.2f\n", Bank.findAccount(number).getBalance());        //displays balance for valid account using formatted getBalance method

                    //validates deposit amount
                    double doubleAmount = 0;                                                    //initial deposit amount to 0
                    String stringAmount = null;                                                 //variable used for exit route
                    boolean exit = false;                                                       //boolean flag for exit route
                    boolean valid = false;                                                      //boolean flag for invalid entry (string value != EXIT, or non-positive double)
                    System.out.print("Enter amount to deposit: ");                              //prompts user for deposit amount
                    do {                                                                        //loops until user submits valid positive double or else EXIT

                        if (scanner.hasNextDouble()) {                                          //checks if input is a double
                            doubleAmount = scanner.nextDouble();                                //if so, sets amount variable
                            if (doubleAmount <= 0) {                                            //if 0 or negative, prompts user to try again via !valid flag
                                System.out.print("Invalid entry. Please enter a positive amount, or EXIT to return to main menu: ");
                                scanner.nextLine();                                             //clear newline
                            } else {
                            valid = true;                                                       //if positive integer,escape loop to next validate for EXIT flag
                            }
                        } else if (!(scanner.hasNextDouble())) {                                //if input is not a double
                            stringAmount = scanner.nextLine();                                  //assigns it to a string variable to check if equals EXIT
                            if (stringAmount.equals("EXIT")) {                                  //if so, sets valid flag to escape loop and exit flag for next validation
                                valid = true;
                                exit = true;
                            } else {                                                            //if not, reloops for valid input
                                System.out.print("Invalid entry. Please enter an amount to deposit, or EXIT to return to main menu: ");
                            }
                        }
                    }
                    while (valid == false);                                                     //loops until user enters positive double or EXIT
                    if (exit == true) {                                                         //once escapes above, checks if EXIT--if so, breaks to main menu; if not, proceed to deposit method
                        System.out.println();
                        break;
                    }

                    //post-validation deposit method
                    (Bank.findAccount(number)).deposit(doubleAmount);                           //calls BankAccount.deposit method using Bank.findAccount with account number to retrieve object, and passes double amount
                    break;                                                                      //break to main menu

                case 3:     //COMPLETED, VERIFIED, COMMENTED
                    //withdrawal workflow

                    //validates account number input--repurposed from case 2 deposit
                    scanner.nextLine();
                    System.out.print("\nEnter account number: ");
                    number = scanner.nextLine();
                    while (Bank.findAccount(number) == null && !(number.equals("EXIT"))) {
                        System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                        number = scanner.nextLine();
                    }
                    if (number.equals("EXIT")) {
                        System.out.println();
                        break;
                    }
                    System.out.printf("\nCurrent balance: $%,.2f\n", Bank.findAccount(number).getBalance());

                    //validates withdrawal amount--repurposed from case 2 deposit
                    doubleAmount = 0;
                    stringAmount = null;
                    exit = false;
                    valid = false;
                    System.out.print("Enter amount to withdraw: ");
                    do {

                        if (scanner.hasNextDouble()) {
                            doubleAmount = scanner.nextDouble();
                            if (doubleAmount <= 0) {
                                System.out.print("Invalid entry. Please enter a positive amount, or EXIT to return to main menu: ");
                                scanner.nextLine();
                            } else {
                                valid = true;
                            }
                        } else if (!(scanner.hasNextDouble())) {
                            stringAmount = scanner.nextLine();
                            if (stringAmount.equals("EXIT")) {
                                valid = true;
                                exit = true;
                            } else {
                                System.out.print("Invalid entry. Please enter an amount to withdraw, or EXIT to return to main menu: ");
                            }
                        }
                    }
                    while (valid == false);
                    if (exit == true) {
                        System.out.println();
                        break;
                    }

                    //post-validation withdrawal method
                    (Bank.findAccount(number)).withdraw(doubleAmount);                          //calls BankAccount.withdraw method using Bank.findAccount with account number to retrieve object, and passes double amount
                    break;

                case 4:     //COMPLETED, VERIFIED, COMMENTED
                    //transfer workflow

                    //validates account from--repurposed from case 2 deposit
                    scanner.nextLine();
                    System.out.print("\nEnter from account number: ");
                    String numberFrom = scanner.nextLine();
                    while (Bank.findAccount(numberFrom) == null && !(numberFrom.equals("EXIT"))) {
                        System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                        numberFrom = scanner.nextLine();
                    }
                    if (numberFrom.equals("EXIT")) {
                        System.out.println();
                        break;
                    }
                    System.out.printf("\nCurrent balance: $%,.2f\n", Bank.findAccount(numberFrom).getBalance());

                    //validates account to--repurposed from case 2 deposit
                    System.out.print("Enter to account number: ");
                    String numberTo = scanner.nextLine();
                    while ((Bank.findAccount(numberTo) == null || numberFrom.equals(numberTo)) && !(numberTo.equals("EXIT"))) {
                        if (numberFrom.equals(numberTo)) {                                      //added validation for entering same case twice
                            System.out.print("\nTo and from accounts entered are the same account. Please enter a valid to account number, or EXIT to return to main menu: ");
                            numberTo = scanner.nextLine();
                        } else {
                            System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                            numberTo = scanner.nextLine();
                        }
                    }
                    if (numberTo.equals("EXIT")) {
                        System.out.println();
                        break;
                    }
                    System.out.printf("\nCurrent balance: $%,.2f\n", Bank.findAccount(numberTo).getBalance());

                    //validates transfer amount--repurposed from case 2 deposit
                    doubleAmount = 0;
                    stringAmount = null;
                    exit = false;
                    valid = false;
                    System.out.print("Enter amount to transfer: ");
                    do {

                        if (scanner.hasNextDouble()) {
                            doubleAmount = scanner.nextDouble();
                            if (doubleAmount <= 0) {
                                System.out.print("Invalid entry. Please enter a positive amount, or EXIT to return to main menu: ");
                                scanner.nextLine();
                            } else {
                                valid = true;
                            }
                        } else if (!(scanner.hasNextDouble())) {
                            stringAmount = scanner.nextLine();
                            if (stringAmount.equals("EXIT")) {
                                valid = true;
                                exit = true;
                            } else {
                                System.out.print("Invalid entry. Please enter an amount to transfer, or EXIT to return to main menu: ");
                            }
                        }
                    }
                    while (valid == false);
                    if (exit == true) {
                        System.out.println();
                        break;
                    }

                    //post-validation transfer method
                    Bank.transferMoney(numberFrom, numberTo, doubleAmount);                     //calls BankAccount.transferMoney method passing both accounts and the double amount
                    break;

                case 5:     //COMPLETED, VERIFIED, COMMENTED
                    //view account workflow

                    //validates account number--repurposed from case 2 deposit
                    scanner.nextLine();
                    System.out.print("\nEnter account number: ");
                    number = scanner.nextLine();
                    while (Bank.findAccount(number) == null && !(number.equals("EXIT"))) {
                        System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                        number = scanner.nextLine();
                    }
                    if (number.equals("EXIT")) {
                        System.out.println();
                        break;
                    }

                    //post-validation account details method
                    System.out.println("\nAccount details: ");
                    System.out.println(Bank.findAccount(number).getAccountInfo());              //prints return from getAccountInfo method using account number
                    System.out.println();
                    break;

                case 6:     //COMPLETED, VERIFIED, COMMENTED
                    //list accounts workflow
                    
                    //nothing to validate outside of Bank method
                    Bank.listAccounts();                                                        //calls listAccounts method with no parameters
                    break;

                case 7:     //COMPLETED, VERIFIED, COMMENTED
                    //delete account workflow

                    //validates account number--repurposed from case 2 deposit
                    scanner.nextLine();
                    System.out.print("\nEnter account number: ");
                    number = scanner.nextLine();
                    while (Bank.findAccount(number) == null && !(number.equals("EXIT"))) {
                        System.out.print("Account does not exist. Please enter a valid account number, or EXIT to return to main menu: ");
                        number = scanner.nextLine();
                    }
                    if (number.equals("EXIT")) {
                        System.out.println();
                        break;
                    }

                    //post-validation delete method
                    Bank.deleteAccount(number);                                                 //passes account number to delete method
                    break;

                case 8:     //COMPLETED, VERIFIED, COMMENTED
                    //exit workflow

                    //ends program upon user input
                    System.out.println("\nThank you for doing business with Your Friendly Neighborhood Bank! Have a nice day!\n");      //goodbye message
                    scanner.close();                                                            //closes scanner
                    break;

                default:        //COMPLETED, VERIFIED, COMMENTED                                //default case catches invalid integers (<1 or >8)
                    //invalid input error message
                    System.out.println("\nInvalid entry. Please enter a number 1-8.\n");
            }                                                                                   //end of menu switch
        }
        while (selection != 8);                                                                 //triggers loop again after invalid integer or completed transaction unless user chooses to exit
    }

    public static void displayMenu() {      //COMPLETED, VERIFIED, COMMENTED
        //display user interface menu when called by main method

        System.out.println("Please make a selection:");                                         //display menu options and a prompt for input
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. View Account Details");
        System.out.println("6. List All Accounts");
        System.out.println("7. Delete Account");
        System.out.println("8. Exit");
        System.out.print("\nYour selection: ");
    }
}