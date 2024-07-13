import java.util.Date;

public class Transaction {
    private Date timestamp;
    private String description;
    private double amount;

    public Transaction(Date timestamp, String description, double amount) {
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
