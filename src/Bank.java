//Bank class imports
import java.util.ArrayList;

public class Bank {

    /*
    Bank class
        Instance Variables:
            accounts (ArrayList<BankAccount>): A list of all bank accounts in the system.
        Methods:
            createAccount(String accountHolder, String accountType): Creates a new bank account with a unique account number and adds it to the system.
                The account number should be auto-generated and stored as a string (for simplicity, use "ACC" followed by an integer ID).
            findAccount(String accountNumber): Finds and returns the account with the specified account number. If the account doesn't exist, return null.
            listAccounts(): Lists all the bank accounts in the system, displaying the account holder’s name, account number, and account balance.
            deleteAccount(String accountNumber): Deletes an account based on the account number.
            transferMoney(String fromAccount, String toAccount, double amount): Transfers money between two accounts if both accounts exist and the sender has sufficient funds.
            */

    //Bank class fields:
    static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();                      //arraylist to hold created account objects
    private static int totalAccounts;                                                           //master account counter--static variable to keep running total of all objects (accounts)


    //Bank class methods:
    public static void createAccount(String accountHolder, String accountType) {                //must be instantiated to run, but does not save objects by name--only stored to arraylist
        //creates new bank account with unique account number and adds it to system
            //account number should be auto-generated and stored as a string (e.g. "ACC" + integer ID)

        totalAccounts = totalAccounts + 1;                                                      //increment master account counter which is used for unique ID creation
        String accountNumber = String.format("ACC%05d", totalAccounts);                         //create unique account ID from counter
        double newBalance = 0.00;                                                               //set initial balance to 0
        BankAccount newAccount = new BankAccount(accountNumber, accountHolder, newBalance, accountType);        //create object using BankAccount constructor
        accounts.add(newAccount);                                                               //add new object to arraylist
        System.out.println("\nAccount created successfully!");                                  //display success message
        System.out.println(newAccount.getAccountInfo());                                        //display account details via getAccountInfo method from new object
        System.out.println();
    }

    public static BankAccount findAccount(String number) {                                      //made static so can be called without instantiation and to avoid object instance data
        //finds and returns account with specified account number
            //if account doesn't exist, return null

        for (int i = 0; i < accounts.size(); i = i + 1) {                                       //iterates through arraylist
            BankAccount found = accounts.get(i);                                                //setting variable found to each of the arraylist objects
            if (found.getAccountNumber().equals(number)) {                                      //until accountNumber attribute of object matches number passed in
                return found;                                                                   //then returns that object
            }
        }
        return null;                                                                            //returns null if account doesn't exist for validation error message in main method
    }

    public static void listAccounts() {                                                         //made static so can be called without instantiation and to avoid object instance data
        //list all bank accounts in the system displaying the account holder's name, account number, and account balance

        if (accounts.isEmpty()) {                                                               //validation for empty arraylist
            System.out.println("\nNo accounts exist--please open one!\n");
        } else {
            System.out.println("\nAll accounts: ");                                             //if not empty, iterates through arraylist with for-each loop displaying getAccountInfo for each
            for (BankAccount T : accounts) {
                System.out.println(T.getAccountInfo());
            }
            System.out.println();
        }
    }

    public static String deleteAccount(String number) {                                         //made static so can be called without instantiation and to avoid object instance data
        //deletes account based on account number

        for (int i = 0; i < accounts.size(); i = i + 1) {                                       //repurposed from findAccount method
            BankAccount found = accounts.get(i);
            if (found.getAccountNumber().equals(number)) {                                      //if a match found, displays a statement with account info
                System.out.println("\nAccount " + number + " balance " + String.format("$%,.2f", Bank.findAccount(number).getBalance()) + " withdrawn and account successfully closed!\n" );
//                Bank.findAccount(number).balance = 0;                                         //and resets account balance to 0 (in case of master bank ledger not currently in use)
                Bank.findAccount(number).setBalance(0);                                         //replaced the above line with setter method instead of direct attribute access
                accounts.remove(found);                                                         //before removing account object from arraylist
            }
        }
        return null;                                                                            //returns null if account doesn't exist for validation error message in main method
    }

    public static void transferMoney (String fromAccount, String toAccount, double amount) {        //method passes in to/from accounts and transfer amount
        //transfers money between two accounts if both accounts exist and the sender has sufficient funds

//        if (findAccount(fromAccount).balance >= amount) {                                             //validates sufficient balance for transfer
//            findAccount(fromAccount).balance = findAccount(fromAccount).balance - amount;             //if so, withdraws from account
//            findAccount(toAccount).balance = findAccount(toAccount).balance + amount;                 //and deposits to account
        if (findAccount(fromAccount).getBalance() >= amount) {                                          //replaced the above 3 lines using balance setter and getter instead of direct access
            findAccount(fromAccount).setBalance(findAccount(fromAccount).getBalance() - amount);        //
            findAccount(toAccount).setBalance(findAccount(toAccount).getBalance() + amount);            //
            System.out.println("\nTransfer successful! New balances: ");                                //prints success message and new balances of both accounts
            System.out.println("Account " + fromAccount + ": " + String.format("$%,.2f", findAccount(fromAccount).getBalance()));
            System.out.println("Account " + toAccount + ": " + String.format("$%,.2f\n", findAccount(toAccount).getBalance()));
        } else {                                                                                        //if insufficient funds, displays fail message and current balances of both accounts
            System.out.println("\nInsufficient funds to complete this transaction! Current balances: ");
            System.out.println("Account " + fromAccount + ": " + String.format("$%,.2f", findAccount(fromAccount).getBalance()));
            System.out.println("Account " + toAccount + ": " + String.format("$%,.2f\n", findAccount(toAccount).getBalance()));
        }
    }
}
