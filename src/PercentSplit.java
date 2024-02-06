public class PercentSplit extends Split{
    private double percent;
    public PercentSplit(int id, User user, double amount) {
        super(id, user, amount);
    }

    public double getPercent() {
        return percent;
    }
}
