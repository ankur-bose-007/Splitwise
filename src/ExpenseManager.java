import enums.ExpenseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager(){
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        userMap.put(user.getId(), user);
        balanceSheet.putIfAbsent(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata){
        Expense expense = ExpenseService.createExpense(amount, paidBy, expenseType, splits, expenseMetadata);
        expenses.add(expense);

        for(Split split: splits){
            String paidTo = split.getUser().getId();

            Map<String, Double> balances = balanceSheet.get(paidBy.getId());

            balances.putIfAbsent(paidTo, 0.0);

            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);

            balances.putIfAbsent(paidBy.getId(), 0.0);

            balances.put(paidBy.getId(), balances.get(paidBy.getId()) - split.getAmount());
        }
    }

    public void showBalance(String userId){
        boolean isEmpty = true;

        for(Map.Entry<String, Double> entry: balanceSheet.get(userId).entrySet()){
            if(entry.getValue() != 0){
                printBalance(userId, entry.getKey(), entry.getValue());
                isEmpty = false;
            }
        }

        if(isEmpty)
            System.out.println("No balances");
    }

    public void showBalances(){
        for(String id: balanceSheet.keySet()){
            showBalance(id);
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }
}
