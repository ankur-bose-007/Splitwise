import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(int id, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(id, amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for(Split split: getSplits()){
            if(!(split instanceof ExactSplit))
                return false;
        }

        double totalAmount = getAmount();
        double sumTotalSplitsAmount = 0;

        for(Split split: getSplits()){
            ExactSplit exactSplit = (ExactSplit) split;
            sumTotalSplitsAmount += exactSplit.getAmount();
        }

        return totalAmount == sumTotalSplitsAmount;
    }
}
