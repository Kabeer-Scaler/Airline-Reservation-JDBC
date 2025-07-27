import java.sql.*;

public class BookingDAO {
    public void getAvgBaggagePerClass() {
        String sql = "SELECT s.class_type, AVG(b.weight) AS avg_weight " +
                "FROM Baggage b " +
                "JOIN Booking bo ON b.booking_id = bo.booking_id " +
                "JOIN Booking_Passenger bp ON bo.booking_id = bp.booking_id " +
                "JOIN Seats s ON bp.seat_id = s.seat_id " +
                "GROUP BY s.class_type";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Average Baggage Weight per Class:");
            while (rs.next()) {
                System.out.printf("Class: %d | Avg Weight: %.2f\n",
                        rs.getInt("class_type"),
                        rs.getDouble("avg_weight"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
