import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_reservation?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "java_user";
    private static final String PASSWORD = "JavaPass123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



}


