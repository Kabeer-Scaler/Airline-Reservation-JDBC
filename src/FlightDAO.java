import java.sql.*;

public class FlightDAO {
    public void getFlightsByRoute(String source, String destination) {
        String sql = "SELECT f.flight_id, f.inDeparture_time, f.inArrival_time " +
                "FROM Flight f " +
                "JOIN Route r ON f.route_id = r.route_id " +
                "JOIN Airport a1 ON r.source_airport_id = a1.airport_id " +
                "JOIN Airport a2 ON r.dest_airport_id = a2.airport_id " +
                "WHERE a1.name = ? AND a2.name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, source);
            ps.setString(2, destination);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.printf("Flight ID: %d | Departure: %s | Arrival: %s\n",
                        rs.getInt("flight_id"),
                        rs.getString("inDeparture_time"),
                        rs.getString("inArrival_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMostDelayedRoutes() {
        String sql = "SELECT r.route_id, COUNT(fsl.log_id) AS delay_count " +
                "FROM Flight_Status_Log fsl " +
                "JOIN Flight f ON fsl.flight_id = f.flight_id " +
                "JOIN Route r ON f.route_id = r.route_id " +
                "WHERE fsl.status = 'DELAYED' " +
                "GROUP BY r.route_id " +
                "ORDER BY delay_count DESC LIMIT 5";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Most Delayed Routes:");
            while (rs.next()) {
                System.out.printf("Route ID: %d | Delays: %d\n",
                        rs.getInt("route_id"),
                        rs.getInt("delay_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCrewWorkload() {
        String sql = "SELECT c.name, COUNT(fct.flight_id) AS total_flights, MONTH(f.inDeparture_time) AS month " +
                "FROM Flight_Crew_tasks fct " +
                "JOIN Crew c ON fct.crew_id = c.crew_id " +
                "JOIN Flight f ON fct.flight_id = f.flight_id " +
                "GROUP BY c.name, MONTH(f.inDeparture_time)";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Crew Workload (Flights per Month):");
            while (rs.next()) {
                System.out.printf("Crew: %s | Flights: %d | Month: %d\n",
                        rs.getString("name"),
                        rs.getInt("total_flights"),
                        rs.getInt("month"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
