package sample;

import java.sql.*;
import java.util.ArrayList;

public class RanksDatabase {
    public void updateDatabase(ArrayList<Rank> rankList) {
        try {
            String url = "jdbc:mysql://localhost:3306/company";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("TRUNCATE TABLE ranks");
                for (Rank rank : rankList) {
                    PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ranks VALUES (?, ?)");
                    pStatement.setInt(1, rank.getNumber());
                    pStatement.setFloat(2, (float) rank.getCoefficient());
                    pStatement.execute();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public ArrayList<Rank> downloadDatabase() {
        ArrayList<Rank> rankList = new ArrayList<>();
        try{
            String url = "jdbc:mysql://localhost:3306/company";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM ranks");
                while(resultSet.next()){
                    int number = resultSet.getInt(1);
                    double coefficient = resultSet.getDouble(2);
                    Rank rank = new Rank(number, coefficient);
                    rankList.add(rank);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return rankList;
    }
}
