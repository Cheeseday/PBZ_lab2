package sample;

import java.sql.*;
import java.util.ArrayList;

public class EmployeesDatabase {
    public void updateDatabase(ArrayList<Employee> employeeList) {
        //java -classpath c:\Program Files(x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.27.jar;
        try {
            String url = "jdbc:mysql://localhost:3306/second_lab";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("TRUNCATE TABLE employees");
                for (Employee employee : employeeList) {
                    PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ? )");
                    pStatement.setString(1, employee.getFullName());
                    pStatement.setString(2, employee.getPosition());
                    pStatement.setInt(3, employee.getRank().getNumber());
                    if (employee.isUnionMember()) {
                        pStatement.setString(4, "Да");
                    } else {
                        pStatement.setString(4, "Нет");
                    }
                    pStatement.setFloat(5, (float) employee.getSalary().getAccrued());
                    pStatement.setFloat(6, (float) employee.getSalary().getWithheld());
                    pStatement.setFloat(7, (float) employee.getSalary().getToPayoff());
                    pStatement.execute();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public ArrayList<Employee> downloadDatabase() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try{
            String url = "jdbc:mysql://localhost:3306/company";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
                while(resultSet.next()){
                    String fullName = resultSet.getString(1);
                    String position = resultSet.getString(2);
                    int rankNumber = resultSet.getInt(3);
                    Rank rank = new Rank(rankNumber, 0);
                    String unionMember = resultSet.getString(4);
                    boolean isUnionMember;
                    isUnionMember = unionMember.equals("Да");
                    double accrued = resultSet.getDouble(5);
                    double withheld = resultSet.getDouble(6);
                    double toPayoff = resultSet.getDouble(7);
                    Salary salary = new Salary(accrued, withheld, toPayoff);
                    Employee employee = new Employee(fullName, position, rank, isUnionMember, salary);
                    employeeList.add(employee);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return employeeList;
    }
}
