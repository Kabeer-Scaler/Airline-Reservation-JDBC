 CREATE DATABASE airline_reservation;
 USE airline_reservation;

-- Airport
 CREATE TABLE Airport (
     airport_id BIGINT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     city VARCHAR(50),
     country VARCHAR(50)
 );

 -- Route
 CREATE TABLE Route (
     route_id BIGINT PRIMARY KEY,
     source_airport_id BIGINT,
     dest_airport_id BIGINT,
     distance FLOAT,
     FOREIGN KEY (source_airport_id) REFERENCES Airport(airport_id),
     FOREIGN KEY (dest_airport_id) REFERENCES Airport(airport_id)
 );

 -- Plane
 CREATE TABLE Plane (
     plane_id BIGINT PRIMARY KEY,
     model VARCHAR(50),
     upcode BIGINT
 );

 -- Seats
 CREATE TABLE Seats (
     plane_id BIGINT,
     seat_id BIGINT,
     class_type BIGINT,
     seat_number VARCHAR(10),
     PRIMARY KEY (plane_id, seat_id),
     FOREIGN KEY (plane_id) REFERENCES Plane(plane_id)
 );

 -- Flight
 CREATE TABLE Flight (
     flight_id BIGINT PRIMARY KEY,
     route_id BIGINT,
     plane_id BIGINT,
     inDeparture_time DATETIME,
     inArrival_time DATETIME,
     FOREIGN KEY (route_id) REFERENCES Route(route_id),
     FOREIGN KEY (plane_id) REFERENCES Plane(plane_id)
 );

 -- Crew
 CREATE TABLE Crew (
     crew_id BIGINT PRIMARY KEY,
     name VARCHAR(100),
     gender CHAR(1),
     age INT,
     email VARCHAR(100)
 );

 -- Roles
 CREATE TABLE Roles (
     role_id BIGINT PRIMARY KEY,
     name VARCHAR(50),
     descr VARCHAR(200)
 );

 -- Flight_Crew_tasks
 CREATE TABLE Flight_Crew_tasks (
     flight_id BIGINT,
     crew_id BIGINT,
     role_id BIGINT,
     PRIMARY KEY (flight_id, crew_id, role_id),
     FOREIGN KEY (flight_id) REFERENCES Flight(flight_id),
     FOREIGN KEY (crew_id) REFERENCES Crew(crew_id),
     FOREIGN KEY (role_id) REFERENCES Roles(role_id)
 );

 -- Flight_Status_Log
 CREATE TABLE Flight_Status_Log (
     log_id BIGINT PRIMARY KEY,
     flight_id BIGINT,
     log_time DATETIME,
     reason VARCHAR(200),
     status ENUM('Scheduled','Delayed','Cancelled','Completed'),
     updt_dept_time DATETIME,
     updt_arrival_time DATETIME,
     FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
 );

 -- Passenger
 CREATE TABLE Passenger (
     passenger_id BIGINT PRIMARY KEY,
     name VARCHAR(100),
     age INT,
     gender CHAR(1),
     email VARCHAR(100),
     frequent_flyer_status ENUM('None','Silver','Gold','Platinum'),
     distance_covered BIGINT
 );

 -- Booking
 CREATE TABLE Booking (
     booking_id BIGINT PRIMARY KEY,
     flight_id BIGINT,
     booking_date DATETIME,
     FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
 );

 -- Booking_Passenger (Mapping)
 CREATE TABLE Booking_Passenger (
     booking_id BIGINT,
     passenger_id BIGINT,
     seat_id BIGINT,
     PRIMARY KEY (booking_id, passenger_id),
     FOREIGN KEY (booking_id) REFERENCES Booking(booking_id),
     FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id)
 );

 -- Baggage
 CREATE TABLE Baggage (
     baggage_id BIGINT PRIMARY KEY,
     passenger_id BIGINT,
     booking_id BIGINT,
     weight DECIMAL(5,2),
     FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id),
     FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
 );

 -- Flight_Seat_Availability
 CREATE TABLE Flight_Seat_Availability (
     flight_id BIGINT,
     seat_id BIGINT,
     seat_availability BOOLEAN,
     PRIMARY KEY (flight_id, seat_id),
     FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
 );