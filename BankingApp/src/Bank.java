import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private List<RecurringTransaction> recurringTransactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.recurringTransactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    public void addRecurringTransaction(RecurringTransaction recurringTransaction) {
        recurringTransactions.add(recurringTransaction);
        System.out.println("Recurring transaction added: " + recurringTransaction.getDescription());
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount != null && toAccount != null) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }
    }

    public void printAllAccounts() {
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.checkBalance());
        }
    }

    // Implement method to process recurring transactions
    public void processRecurringTransactions() {
        // Logic to process each recurring transaction based on its frequency
        // Update account balances and log transactions
    }
}
