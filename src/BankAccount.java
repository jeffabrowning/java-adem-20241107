public class BankAccount {

    /*
    BankAccount class
        Instance Variables:
            accountNumber (String): The unique account number.
            accountHolder (String): The name of the account holder.
            balance (double): The current balance of the account.
            accountType (String): The type of the account (e.g., "Savings", "Checking").
        Methods:
            deposit(double amount): Adds money to the account balance.
            withdraw(double amount): Withdraws money from the account if the balance is sufficient. If not, print a message indicating insufficient funds.
            getBalance(): Returns the current balance of the account.
            getAccountInfo(): Returns a string with the account holder's name, account type, and balance.
            */


    //BankAccount class fields:                                                                     //declare constructor variables--private for encapsulation, and immutable ones are final
    private final String accountNumber;                                                             //unique account number
    private final String accountHolder;                                                             //name of account holder
    private double balance;                                                                         //current balance of account
    private final String accountType;                                                               //type of account (e.g. "Savings", "Checking")


    //BankAccount class constructor:                                                                                  //creates new BankAccount objects
    public BankAccount(String accountNumber, String accountHolder, double balance, String accountType) {
        //instantiate each field when object is created
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountType = accountType;
    }


    //BankAccount class methods:
    public void deposit(double in) {                                                                //simple balance increase with success and balance message
        //accepts string and double; adds money to account balance

        balance = balance + in;
        System.out.printf("\nDeposit successful! New balance: $%,.2f\n\n", getBalance());
    }

    public void withdraw(double out) {
        //accepts double; withdraws money from account if balance sufficient
            //if not, print message indicating insufficient funds

        if (balance >= out) {                                                                       //validates sufficient funds to withdraw
            balance = balance - out;                                                                //if so, simple balance increase with success and balance message
            System.out.printf("\nWithdrawal successful! New balance: $%,.2f\n\n", getBalance());
        } else {                                                                                    //if not, fail message with current balance
            System.out.printf("\nInsufficient funds to complete this transaction! Current balance: $%,.2f\n\n", getBalance());
        }
    }

    public double getBalance() {                                                                     //simple data return getter method used in other methods
        //returns current balance of account

        return balance;
    }

    public String getAccountInfo() {                                                                 //string return getter method used in other methods
        //returns a string with the account holder's name, account type, and balance

        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Account Type: " + accountType + String.format(", Balance: $%,.2f", balance);
    }

    public String getAccountNumber() {                                                              //supplemental data return getter method used in other methods
        //returns account number of account

        return accountNumber;
    }

    public void setBalance(double amount) {                                                         //supplemental balance setter method used in other methods
        //sets balance with input

        balance = amount;
    }
}
