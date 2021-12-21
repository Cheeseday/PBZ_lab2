package sample;

public class Employee {
    private final String fullName;
    private String position;
    private Rank rank;
    private boolean isUnionMember;
    private Salary salary;

    public Employee(String fullName, String position, Rank rank, boolean isUnionMember, Salary salary) {
        this.fullName = fullName;
        this.position = position;
        this.rank = rank;
        this.isUnionMember = isUnionMember;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isUnionMember() {
        return isUnionMember;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setUnionMember(boolean unionMember) {
        isUnionMember = unionMember;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
