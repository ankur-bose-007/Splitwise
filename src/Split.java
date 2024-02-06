public class Split {
    private int id;
    private User user;
    private double amount;

    public Split(int id, User user, double amount) {
        this.id = id;
        this.user = user;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
