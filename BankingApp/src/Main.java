import java.util.ArrayList;
import java.util.List;
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
            System.out.println("9. Set Overdraft Limit");
            System.out.println("10. Freeze/Unfreeze Account");
            System.out.println("11. Logout");
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
                    setOverdraftLimit(scanner);
                    break;
                case 10:
                    toggleFreezeAccount(scanner);
                    break;
                case 11:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        try {
            String accountNumber = generateAccountNumber();
            System.out.print("Enter Initial Balance: ");
            double initialBalance = scanner.nextDouble();
            System.out.print("Enter Account Type (1 for Savings, 2 for Checking, 3 for Fixed Deposit): ");
            int accountTypeChoice = scanner.nextInt();
            AccountType accountType = AccountType.SAVINGS; // Default type
            switch (accountTypeChoice) {
                case 1:
                    accountType = AccountType.SAVINGS;
                    break;
                case 2:
                    accountType = AccountType.CHECKING;
                    break;
                case 3:
                    accountType = AccountType.FIXED_DEPOSIT;
                    break;
                default:
                    System.out.println("Invalid account type. Defaulting to Savings.");
                    break;
            }

            List<String> holders = new ArrayList<>();
            System.out.print("Is this a joint account? (yes/no): ");
            String isJointAccount = scanner.next();
            if (isJointAccount.equalsIgnoreCase("yes")) {
                System.out.print("Enter the number of holders: ");
                int numHolders = scanner.nextInt();
                for (int i = 0; i < numHolders; i++) {
                    System.out.print("Enter holder username: ");
                    String holder = scanner.next();
                    holders.add(holder);
                }
            } else {
                holders.add("current_user"); // Replace with actual logged-in user
            }

            System.out.print("Enter Overdraft Limit: ");
            double overdraftLimit = scanner.nextDouble();

            Account newAccount = new Account(accountNumber, initialBalance, accountType, holders, overdraftLimit);
            bank.addAccount(newAccount);
            System.out.println("Account created successfully with Account Number: " + accountNumber);
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private static void deposit(Scanner scanner) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error depositing funds: " + e.getMessage());
        }
    }

    private static void withdraw(Scanner scanner) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error withdrawing funds: " + e.getMessage());
        }
    }

    private static void transfer(Scanner scanner) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error transferring funds: " + e.getMessage());
        }
    }

    private static void checkBalance(Scanner scanner) {
        try {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.next();
            Account checkBalanceAccount = bank.findAccount(accountNumber);
            if (checkBalanceAccount != null) {
                System.out.println("Balance: $" + checkBalanceAccount.checkBalance());
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error checking balance: " + e.getMessage());
        }
    }

    private static void printTransactionHistory(Scanner scanner) {
        try {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.next();
            Account printHistoryAccount = bank.findAccount(accountNumber);
            if (printHistoryAccount != null) {
                printHistoryAccount.printTransactionHistory();
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error printing transaction history: " + e.getMessage());
        }
    }

    private static void addRecurringTransaction(Scanner scanner) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error adding recurring transaction: " + e.getMessage());
        }
    }

    private static void setOverdraftLimit(Scanner scanner) {
        try {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.next();
            Account account = bank.findAccount(accountNumber);
            if (account != null) {
                System.out.print("Enter New Overdraft Limit: ");
                double overdraftLimit = scanner.nextDouble();
                account.setOverdraftLimit(overdraftLimit);
                System.out.println("Overdraft limit updated.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error setting overdraft limit: " + e.getMessage());
        }
    }

    private static void toggleFreezeAccount(Scanner scanner) {
        try {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.next();
            Account account = bank.findAccount(accountNumber);
            if (account != null) {
                account.setFrozen(!account.isFrozen());
                System.out.println("Account " + (account.isFrozen() ? "frozen." : "unfrozen."));
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("Error toggling account freeze: " + e.getMessage());
        }
    }

    private static String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
