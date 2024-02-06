import java.util.List;

public abstract class Expense {
    private int id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetadata expenseMetadata;

    public Expense(int id, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseMetadata = expenseMetadata;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public abstract boolean validate();
}
