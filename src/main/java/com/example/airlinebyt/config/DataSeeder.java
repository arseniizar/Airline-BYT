package com.example.airlinebyt.config;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.ConstructionType;
import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.enums.RoleType;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.operations.Issue;
import com.example.airlinebyt.models.person.Mechanic;
import com.example.airlinebyt.models.person.Passenger;
import com.example.airlinebyt.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final MechanicRepository mechanicRepository;
    private final BacklogRepository backlogRepository;
    private final IssueRepository issueRepository;

    @Autowired
    public DataSeeder(AircraftRepository aircraftRepository,
                      AirportRepository airportRepository,
                      PassengerRepository passengerRepository,
                      FlightRepository flightRepository,
                      BookingRepository bookingRepository,
                      MechanicRepository mechanicRepository,
                      BacklogRepository backlogRepository,
                      IssueRepository issueRepository) {
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.mechanicRepository = mechanicRepository;
        this.backlogRepository = backlogRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (aircraftRepository.count() == 0 && airportRepository.count() == 0) {
            log.info("Database is empty. Seeding initial data...");

            Aircraft boeing737 = loadAircraftData();
            Airport waw = loadAirportData();
            Passenger johnDoe = loadPassengerData();

            Flight flightLO3921 = loadFlightData(boeing737, waw);
            loadBookingData(johnDoe, flightLO3921);

            loadMaintenanceData(boeing737);

            log.info("Finished seeding all data.");
        } else {
            log.info("Database already contains data. Skipping seeding.");
        }
    }

    private Aircraft loadAircraftData() {
        Aircraft boeing737 = new Aircraft("Boeing 737", 189, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);
        boeing737.setFixedWingAttributes(34.3, 6);
        boeing737.setCommercialAttributes(4500.0);
        aircraftRepository.save(boeing737);
        log.info("Saved Commercial Aircraft: {}", boeing737.getModel());

        Aircraft bell407 = new Aircraft("Bell 407", 6, ConstructionType.ROTORCRAFT, RoleType.PRIVATE);
        bell407.setRotorcraftAttributes(4, 3500.0);
        bell407.setPrivateAttributes("VIP leather interior and minibar");
        aircraftRepository.save(bell407);
        log.info("Saved Private Aircraft: {}", bell407.getModel());

        Aircraft cessna172 = new Aircraft("Cessna 172 Skyhawk", 4, ConstructionType.FIXED_WING, RoleType.PRIVATE);
        cessna172.setFixedWingAttributes(11.0, 3);
        cessna172.setPrivateAttributes("Standard training interior");
        aircraftRepository.save(cessna172);
        log.info("Saved training Aircraft: {}", cessna172.getModel());

        return boeing737;
    }

    private Airport loadAirportData() {
        Airport waw = new Airport("WAW", "Warsaw Chopin Airport", new Location("Poland", "Warsaw"));
        airportRepository.save(waw);
        log.info("Saved Airport: {}", waw.getName());
        return waw;
    }

    private Passenger loadPassengerData() {
        Passenger passenger = new Passenger(
                "John", "Doe", LocalDate.of(1990, 5, 15),
                "JD12345", "john.doe@example.com", "+11234567890"
        );
        passenger.addLoyaltyPoints(1500);
        passengerRepository.save(passenger);
        log.info("Saved Passenger: {} {}", passenger.getFirstName(), passenger.getLastName());
        return passenger;
    }

    private Flight loadFlightData(Aircraft aircraft, Airport airport) {
        Flight flight = new Flight();
        flight.setFlightNumber("LO3921");
        flight.setOrigin(airport);
        flight.setDestination(airport);
        flight.setAircraft(aircraft);
        flight.setExpectedDepartureTime(LocalDateTime.now().plusDays(10).withHour(15).withMinute(0));
        flight.setExpectedArrivalTime(LocalDateTime.now().plusDays(10).withHour(17).withMinute(30));
        flight.setStatus(FlightStatus.SCHEDULED);
        flightRepository.save(flight);
        log.info("Saved Flight: {}", flight.getFlightNumber());
        return flight;
    }

    private void loadBookingData(Passenger passenger, Flight flight) {
        Booking booking = new Booking(new BigDecimal("350.00"), passenger, new HashSet<>());
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingFee(new BigDecimal("25.00"));
        bookingRepository.save(booking);
        log.info("Saved Booking for passenger ID: {}", passenger.getPassengerID());
    }

    private void loadMaintenanceData(Aircraft aircraft) {
        Mechanic mechanic = new Mechanic(
                "Bob", "Builder", LocalDate.of(1985, 1, 1),
                LocalDate.of(2010, 6, 1), "Aeronautical Engineering", 75000.0
        );
        mechanicRepository.save(mechanic);
        log.info("Saved Mechanic: {} {}", mechanic.getFirstName(), mechanic.getLastName());

        Backlog backlog = new Backlog(aircraft);

        Issue issue1 = new Issue("Routine engine check required before next flight.", aircraft);
        issueRepository.save(issue1);

        Issue issue2 = new Issue("Fix landing gear light indicator.", aircraft);
        issueRepository.save(issue2);

        backlog.addIssue(issue1);
        backlog.addIssue(issue2);
        backlogRepository.save(backlog);
        log.info("Saved Backlog for Aircraft: {}", aircraft.getModel());

        mechanic.assignBacklog(backlog);
        mechanicRepository.save(mechanic);
        log.info("Assigned backlog to mechanic {}", mechanic.getFirstName());
    }
}
