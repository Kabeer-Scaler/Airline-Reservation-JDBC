import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PassengerDAO passengerDAO = new PassengerDAO();
        FlightDAO flightDAO = new FlightDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("\n--- Airline Reservation Reports ---");
            System.out.println("1. List Flights by Route");
            System.out.println("2. Top Passengers by Miles");
            System.out.println("3. Crew Workload (Flights/Month)");
            System.out.println("4. Most Delayed Routes");
            System.out.println("5. Average Baggage Weight per Class");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Source Airport Name: ");
                    String source = sc.nextLine();
                    System.out.print("Destination Airport Name: ");
                    String destination = sc.nextLine();
                    flightDAO.getFlightsByRoute(source, destination);
                    break;

                case 2:
                    passengerDAO.getTopPassengersByMiles();
                    break;

                case 3:
                    flightDAO.getCrewWorkload();
                    break;

                case 4:
                    flightDAO.getMostDelayedRoutes();
                    break;

                case 5:
                    bookingDAO.getAvgBaggagePerClass();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
