import java.util.Scanner;
import java.util.UUID;
import java.util.Date;

public class Main {
    private static UserManager userManager = new UserManager();
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    if (login(scanner)) {
                        bankMenu(scanner);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Banking App!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        userManager.register(username, password);
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        return userManager.login(username, password);
    }

    private static void bankMenu(Scanner scanner) {
        while (true) {
            System.out.println("Welcome to the Banking App");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Print Transaction History");
            System.out.println("7. Print All Accounts");
            System.out.println("8. Add Recurring Transaction");
            System.out.println("9. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    checkBalance(scanner);
                    break;
                case 6:
                    printTransactionHistory(scanner);
                    break;
                case 7:
                    bank.printAllAccounts();
                    break;
                case 8:
                    addRecurringTransaction(scanner);
                    break;
                case 9:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        String accountNumber = generateAccountNumber();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        Account newAccount = new Account(accountNumber, initialBalance);
        bank.addAccount(newAccount);
        System.out.println("Account created successfully with Account Number: " + accountNumber);
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        Account depositAccount = bank.findAccount(accountNumber);
        if (depositAccount != null) {
            System.out.print("Enter Deposit Amount: ");
            double depositAmount = scanner.nextDouble();
            depositAccount.deposit(depositAmount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        Account withdrawAccount = bank.findAccount(accountNumber);
        if (withdrawAccount != null) {
            System.out.print("Enter Withdrawal Amount: ");
            double withdrawAmount = scanner.nextDouble();
            withdrawAccount.withdraw(withdrawAmount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transfer(Scanner scanner) {
        System.out.print("Enter Source Account Number: ");
        String fromAccountNumber = scanner.next();
        System.out.print("Enter Destination Account Number: ");
        String toAccountNumber = scanner.next();
        Account fromAccount = bank.findAccount(fromAccountNumber);
        Account toAccount = bank.findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            System.out.print("Enter Transfer Amount: ");
            double transferAmount = scanner.nextDouble();
            bank.transfer(fromAccount, toAccount, transferAmount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        Account checkBalanceAccount = bank.findAccount(accountNumber);
        if (checkBalanceAccount != null) {
            System.out.println("Balance: $" + checkBalanceAccount.checkBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void printTransactionHistory(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        Account printHistoryAccount = bank.findAccount(accountNumber);
        if (printHistoryAccount != null) {
            printHistoryAccount.printTransactionHistory();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void addRecurringTransaction(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();
            System.out.print("Enter Description: ");
            scanner.nextLine(); // Consume newline
            String description = scanner.nextLine();
            System.out.print("Enter Frequency (e.g., weekly, monthly): ");
            String frequency = scanner.next();
            RecurringTransaction recurringTransaction = new RecurringTransaction(accountNumber, amount, description, new Date(), frequency);
            bank.addRecurringTransaction(recurringTransaction);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
