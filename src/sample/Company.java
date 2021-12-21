package sample;

import java.util.ArrayList;

public class Company {
    ArrayList<Employee> employeeList = new ArrayList<>();
    ArrayList<Rank> rankList = new ArrayList<>();

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public ArrayList<Rank> getRankList() {
        return rankList;
    }

    public void setRankList(ArrayList<Rank> rankList) {
        this.rankList = rankList;
    }

    public void addEmployee(String fullName, String position, int rankNumber, boolean isUnionMember) {
        double rankCoefficient = 0;
        for (Rank value : rankList) {
            if (value.getNumber() == rankNumber) {
                rankCoefficient = value.getCoefficient();
            }
        }
        Rank rank = new Rank(rankNumber, rankCoefficient);
        Employee employee = new Employee(fullName, position, rank, isUnionMember, calculateSalary(rank, isUnionMember));
        employeeList.add(employee);
    }

    public void deleteEmployee(String fullName) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getFullName().equals(fullName)) {
                employeeList.remove(employeeList.get(i));
            }
        }
    }

    public void editEmployee(String fullName, String newPosition, int newRankNumber, boolean newUnionMember) {
        for (Employee employee : employeeList) {
            if (employee.getFullName().equals(fullName)) {
                employee.setPosition(newPosition);
                double rankCoefficient = 0;
                for (Rank value : rankList) {
                    if (value.getNumber() == newRankNumber) {
                        rankCoefficient = value.getCoefficient();
                    }
                }
                Rank rank = new Rank(newRankNumber, rankCoefficient);
                employee.setRank(rank);
                employee.setUnionMember(newUnionMember);
                employee.setSalary(calculateSalary(rank, newUnionMember));
            }
        }
    }

    public void addRank(int number, double coefficient) {
        Rank rank = new Rank(number, coefficient);
        rankList.add(rank);
    }

    public void deleteRank(int number) {
        for (int i = 0; i < rankList.size(); i++) {
            if (rankList.get(i).getNumber() == number) {
                rankList.remove(rankList.get(i));
            }
        }
    }

    public void editRank(int number, double newCoefficient) {
        for (Rank rank : rankList) {
            if (rank.getNumber() == number) {
                rank.setCoefficient(newCoefficient);
            }
        }
        double rankCoefficient = 0;
        for (Rank rank : rankList) {
            if (rank.getNumber() == number) {
                rankCoefficient = rank.getCoefficient();
            }
        }
        for (Employee employee : employeeList) {
            if (employee.getRank().getNumber() == number) {
                employee.setSalary(calculateSalary(new Rank(number, rankCoefficient), employee.isUnionMember()));
            }
        }
    }

    public void printRanks() {
        for (Rank rank : rankList) {
            System.out.println("Number " + rank.getNumber() + " - coefficient " + rank.getCoefficient());
        }
        System.out.println();
    }

    public void printEmployees() {
        double tempMinSalary = employeeList.get(0).getSalary().getToPayoff();
        for (Employee employee : employeeList) {
            if (employee.getSalary().getToPayoff() < tempMinSalary) {
                tempMinSalary = employee.getSalary().getToPayoff();
            }
        }
        for (Employee employee : employeeList) {
            if (employee.getSalary().getToPayoff() == tempMinSalary) {
                System.out.println("Full name: " + employee.getFullName());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("To payoff: " + employee.getSalary().getToPayoff());
                System.out.println();
            }
        }
    }

    public void printSalaryDetails(String fullName) {
        for (Employee employee : employeeList) {
            if (employee.getFullName().equals(fullName)) {
                System.out.println("Full name: " + employee.getFullName());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Accrued - " + employee.getSalary().getAccrued()
                        + "; withheld - " + employee.getSalary().getWithheld()
                        + "; toPayoff - " + employee.getSalary().getToPayoff());
            }
        }
    }

    public Salary calculateSalary(Rank rank, boolean isUnionMember) {
        double accrued = Salary.minWage * rank.getCoefficient();
        double withheld;
        if (isUnionMember) {
            withheld = accrued * (Retention.incomeTax + Retention.pensionFund + Retention.unionFee);
        } else {
            withheld = accrued * (Retention.incomeTax + Retention.pensionFund);
        }
        double toPayoff = accrued - withheld;
        return new Salary(accrued, withheld, toPayoff);
    }
}
