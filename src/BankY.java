import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Account class to represent a bank account
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

// Bank class to manage accounts and transactions
class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolder, double balance) {
        accounts.put(accountNumber, new Account(accountNumber, accountHolder, balance));
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found");
        }
    }

    public void transfer(String senderAccountNumber, String recipientAccountNumber, double amount) {
        Account sender = getAccount(senderAccountNumber);
        Account recipient = getAccount(recipientAccountNumber);
        if (sender != null && recipient != null) {
            sender.transfer(recipient, amount);
        } else {
            System.out.println("Account not found");
        }
    }
}

public class BankY {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder: ");
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    bank.createAccount(accountNumber, accountHolder, balance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    bank.deposit(accountNumber, amount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    bank.withdraw(accountNumber, amount);
                    break;
                case 4:
                    System.out.print("Enter sender account number: ");
                    String senderAccountNumber = scanner.nextLine();
                    System.out.print("Enter recipient account number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    bank.transfer(senderAccountNumber, recipientAccountNumber, amount);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}