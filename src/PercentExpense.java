import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(int id, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(id, amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for(Split split: getSplits()){
            if(!(split instanceof PercentSplit))
                return false;
        }

        double totalPercent = 100;
        double sumTotalSplitsPercent = 0;

        for(Split split: getSplits()){
            PercentSplit percentSplit = (PercentSplit) split;
            sumTotalSplitsPercent += percentSplit.getPercent();
        }

        return totalPercent == sumTotalSplitsPercent;
    }
}
