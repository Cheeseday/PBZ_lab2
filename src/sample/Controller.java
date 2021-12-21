package sample;

public class Controller {
    private final Company company = new Company();
    private final EmployeesDatabase employeesDatabase = new EmployeesDatabase();
    private final RanksDatabase ranksDatabase = new RanksDatabase();

    public void addEmployee(String fullName, String position, int rankNumber, boolean isUnionMember) {
        getInfoFromDB();
        company.addEmployee(fullName, position, rankNumber, isUnionMember);
        employeesDatabase.updateDatabase(company.getEmployeeList());
    }

    public void editEmployee(String fullName, String newPosition, int newRankNumber, boolean newUnionMember) {
        getInfoFromDB();
        company.editEmployee(fullName, newPosition, newRankNumber, newUnionMember);
        employeesDatabase.updateDatabase(company.getEmployeeList());
    }

    public void deleteEmployee(String fullName) {
        getInfoFromDB();
        company.deleteEmployee(fullName);
        employeesDatabase.updateDatabase(company.getEmployeeList());
    }

    public void addRank(int number, double coefficient) {
        getInfoFromDB();
        company.addRank(number, coefficient);
        ranksDatabase.updateDatabase(company.getRankList());
    }

    public void editRank(int number, double newCoefficient) {
        getInfoFromDB();
        company.editRank(number, newCoefficient);
        ranksDatabase.updateDatabase(company.getRankList());
        employeesDatabase.updateDatabase(company.getEmployeeList());
    }

    public void deleteRank(int number) {
        getInfoFromDB();
        company.deleteRank(number);
        ranksDatabase.updateDatabase(company.getRankList());
    }

    public void printRanks() {
        getInfoFromDB();
        company.printRanks();
    }

    public void printEmployees() {
        getInfoFromDB();
        company.printEmployees();
    }

    public void printSalaryDetails(String fullName) {
        getInfoFromDB();
        company.printSalaryDetails(fullName);
    }

    public void getInfoFromDB() {
        company.setEmployeeList(employeesDatabase.downloadDatabase());
        company.setRankList(ranksDatabase.downloadDatabase());
    }
}
