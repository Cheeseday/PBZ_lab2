package sample;

public class Salary {
    private final double accrued;
    private final double withheld;
    private final double toPayoff;
    public static double minWage = 100;

    public Salary(double accrued, double withheld, double toPayoff) {
        this.accrued = accrued;
        this.withheld = withheld;
        this.toPayoff = toPayoff;
    }

    public double getAccrued() {
        return accrued;
    }

    public double getWithheld() {
        return withheld;
    }

    public double getToPayoff() {
        return toPayoff;
    }
}
