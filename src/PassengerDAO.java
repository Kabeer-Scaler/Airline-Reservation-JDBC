import java.sql.*;

public class PassengerDAO {
    public void getTopPassengersByMiles() {
        String sql = "SELECT passenger_id, name, distance_covered FROM Passenger ORDER BY distance_covered DESC LIMIT 5";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Top Passengers by Miles:");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Miles: %d\n",
                        rs.getInt("passenger_id"),
                        rs.getString("name"),
                        rs.getLong("distance_covered"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}