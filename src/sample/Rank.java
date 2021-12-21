package sample;

public class Rank {
    private final int number;
    private double coefficient;

    public Rank(int number, double coefficient) {
        this.number = number;
        this.coefficient = coefficient;
    }

    public int getNumber() {
        return number;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
