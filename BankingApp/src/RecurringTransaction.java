import java.util.Date;

public class RecurringTransaction {
    private String accountNumber;
    private double amount;
    private String description;
    private Date startDate;
    private String frequency; // e.g., "weekly", "monthly"

    public RecurringTransaction(String accountNumber, double amount, String description, Date startDate, String frequency) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
        this.startDate = startDate;
        this.frequency = frequency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getFrequency() {
        return frequency;
    }
}
