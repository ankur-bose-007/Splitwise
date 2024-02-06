import enums.ExpenseType;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(double amount, User paidBy, ExpenseType expenseType, List<Split> splitList, ExpenseMetadata expenseMetadata){
        switch (expenseType){
            case ExpenseType.EXACT :
                return new EqualExpense(1, amount, paidBy, splitList, expenseMetadata);
            case ExpenseType.PERCENT:
                for(Split split: splitList){
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount * percentSplit.getPercent()) / 100.00);
                }
                return new PercentExpense(1, amount, paidBy, splitList, expenseMetadata);
            case ExpenseType.EQUAL:
                int totalSplits = splitList.size();
                double splitAmount = ((double)Math.round(amount * 100 / totalSplits)) / 100.0;
                for(Split split: splitList){
                    split.setAmount(splitAmount);
                }
                splitList.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(1, amount, paidBy, splitList, expenseMetadata);
            default:
                return null;
        }
    }
}
