package Utilities;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BimabharosaDatabaseHelper {

    static int count = 0;

    public static final void executeQuery(String sql) throws IOException {

        Properties property = PropertyFileReader.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/BimabharosaDB_Details.properties");

        String url = property.getProperty("DB_Endpoint");
        String username = property.getProperty("Username");
        String password = property.getProperty("Password");

        try {
            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            try {
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
                prepareStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Close connection
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertRecord(String test_name, String request, String response, String errorCode, String status, String message, Timestamp timestamp, String entityRefNo, String module) throws IOException {

        List<Object> ls = new ArrayList<>();
        ls.add(test_name);
        ls.add(request);
        ls.add(response);
        ls.add(errorCode);
        ls.add(status);
        ls.add(message);
        ls.add(timestamp);
        ls.add(entityRefNo);

        count = count + 1;

        String sql = "INSERT INTO LOG_HISTORY VALUES('"+ls.get(0)+"','"+ls.get(1)+"','"+ls.get(2)+"','"+ls.get(3)+"','"+ls.get(4)+"','"+ls.get(5)+"','"+ls.get(6)+"','"+ls.get(7)+"',"+count+",'"+module+"')";
        //System.out.println(sql);
        executeQuery(sql);

    }
}



