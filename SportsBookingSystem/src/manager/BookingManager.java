package manager;

import model.Booking;
import model.Facility;
import model.TimeSlot;
import util.InputHandler;
import util.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for handling booking operations
 */
public class BookingManager {
    private List<Booking> bookings;
    private FacilityManager facilityManager;

    public BookingManager(FacilityManager facilityManager) {
        this.bookings = new ArrayList<>();
        this.facilityManager = facilityManager;
    }

    /**
     * Create a new booking
     */
    public void createBooking() {
        System.out.println("\n=== CREATE NEW BOOKING ===");
        
        // Display available facilities
        facilityManager.displayAvailableFacilities();
        
        if (facilityManager.getAvailableFacilities().isEmpty()) {
            System.out.println("No facilities available for booking.");
            return;
        }

        // Get facility
        String facilityId = InputHandler.getString("\nEnter Facility ID: ");
        Facility facility = facilityManager.getFacilityById(facilityId);
        
        if (facility == null) {
            System.out.println("Facility not found!");
            return;
        }

        if (!facility.isAvailable()) {
            System.out.println("This facility is currently unavailable!");
            return;
        }

        // Get customer information
        String customerName = InputHandler.getValidName("Enter Customer Name: ");
        String phoneNumber = InputHandler.getValidPhoneNumber("Enter Phone Number: ");

        // Get booking date
        LocalDate bookingDate = InputHandler.getValidDate("Enter Booking Date (dd/MM/yyyy): ");

        // Get time slot
        System.out.println("\nEnter Time Slot (Operating hours: 06:00 - 22:00)");
        int startHour = InputHandler.getInt("Start Hour (6-21): ", 6, 21);
        int endHour = InputHandler.getInt("End Hour (" + (startHour + 1) + "-22): ", startHour + 1, 22);

        TimeSlot timeSlot = new TimeSlot(startHour, endHour);

        // Check if time slot is available
        if (!isTimeSlotAvailable(facility, bookingDate, timeSlot)) {
            System.out.println("\nThis time slot is already booked!");
            System.out.println("Please choose another time slot.");
            return;
        }

        // Create booking
        Booking booking = new Booking(customerName, phoneNumber, facility, bookingDate, timeSlot);
        
        // Display booking details and confirm
        System.out.println("\n=== BOOKING DETAILS ===");
        System.out.println(booking);
        System.out.println(Validator.repeat("=", 80));

        if (InputHandler.getConfirmation("\nConfirm booking?")) {
            bookings.add(booking);
            System.out.println("\n✓ Booking created successfully!");
            System.out.println("Your Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("\nBooking cancelled.");
        }
    }

    /**
     * Check if time slot is available for booking
     */
    private boolean isTimeSlotAvailable(Facility facility, LocalDate date, TimeSlot timeSlot) {
        for (Booking booking : bookings) {
            if (booking.getStatus().equals("ACTIVE") &&
                booking.getFacility().getFacilityId().equals(facility.getFacilityId()) &&
                booking.getBookingDate().equals(date) &&
                booking.getTimeSlot().overlaps(timeSlot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Display all bookings
     */
    public void displayAllBookings() {
        System.out.println("\n=== ALL BOOKINGS ===");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println(Validator.repeat("=", 80));
        for (Booking booking : bookings) {
            System.out.println(booking);
            System.out.println(Validator.repeat("-", 80));
        }
        System.out.println("Total bookings: " + bookings.size());
    }

    /**
     * Display active bookings only
     */
    public void displayActiveBookings() {
        System.out.println("\n=== ACTIVE BOOKINGS ===");
        List<Booking> activeBookings = getActiveBookings();
        
        if (activeBookings.isEmpty()) {
            System.out.println("No active bookings found.");
            return;
        }

        System.out.println(Validator.repeat("=", 80));
        for (Booking booking : activeBookings) {
            System.out.println(booking);
            System.out.println(Validator.repeat("-", 80));
        }
        System.out.println("Total active bookings: " + activeBookings.size());
    }

    /**
     * Cancel a booking
     */
    public void cancelBooking() {
        System.out.println("\n=== CANCEL BOOKING ===");
        displayActiveBookings();
        
        if (getActiveBookings().isEmpty()) {
            return;
        }

        String bookingId = InputHandler.getString("\nEnter Booking ID to cancel: ");
        Booking booking = getBookingById(bookingId);

        if (booking == null) {
            System.out.println("Booking not found!");
            return;
        }

        if (!booking.getStatus().equals("ACTIVE")) {
            System.out.println("This booking is already cancelled!");
            return;
        }

        System.out.println("\n" + booking);
        
        if (InputHandler.getConfirmation("\nAre you sure you want to cancel this booking?")) {
            booking.cancel();
            System.out.println("\n✓ Booking cancelled successfully!");
        } else {
            System.out.println("\nCancellation aborted.");
        }
    }

    /**
     * Search bookings by customer name
     */
    public void searchBookingsByCustomer() {
        System.out.println("\n=== SEARCH BOOKINGS BY CUSTOMER ===");
        String customerName = InputHandler.getString("Enter customer name: ");
        
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                results.add(booking);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No bookings found for customer: " + customerName);
            return;
        }

        System.out.println("\n" + Validator.repeat("=", 80));
        for (Booking booking : results) {
            System.out.println(booking);
            System.out.println(Validator.repeat("-", 80));
        }
        System.out.println("Found " + results.size() + " booking(s)");
    }

    /**
     * Search bookings by phone number
     */
    public void searchBookingsByPhone() {
        System.out.println("\n=== SEARCH BOOKINGS BY PHONE ===");
        String phone = InputHandler.getString("Enter phone number: ");
        
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getPhoneNumber().contains(phone)) {
                results.add(booking);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No bookings found for phone: " + phone);
            return;
        }

        System.out.println("\n" + Validator.repeat("=", 80));
        for (Booking booking : results) {
            System.out.println(booking);
            System.out.println(Validator.repeat("-", 80));
        }
        System.out.println("Found " + results.size() + " booking(s)");
    }

    /**
     * Display bookings by date
     */
    public void displayBookingsByDate() {
        System.out.println("\n=== VIEW BOOKINGS BY DATE ===");
        LocalDate date = InputHandler.getValidDate("Enter date (dd/MM/yyyy): ");
        
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getBookingDate().equals(date) && booking.getStatus().equals("ACTIVE")) {
                results.add(booking);
            }
        }

        if (results.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("No active bookings found for date: " + date.format(formatter));
            return;
        }

        // Sort by time slot
        results.sort((b1, b2) -> Integer.compare(b1.getTimeSlot().getStartHour(), 
                                                  b2.getTimeSlot().getStartHour()));

        System.out.println("\n" + Validator.repeat("=", 80));
        for (Booking booking : results) {
            System.out.println(booking);
            System.out.println(Validator.repeat("-", 80));
        }
        System.out.println("Found " + results.size() + " booking(s)");
    }

    /**
     * Get booking by ID
     */
    private Booking getBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Get all active bookings
     */
    private List<Booking> getActiveBookings() {
        List<Booking> active = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getStatus().equals("ACTIVE")) {
                active.add(booking);
            }
        }
        return active;
    }

    /**
     * Calculate total revenue
     */
    public void displayRevenue() {
        System.out.println("\n=== REVENUE STATISTICS ===");
        
        double totalRevenue = 0;
        int activeCount = 0;
        int cancelledCount = 0;

        for (Booking booking : bookings) {
            if (booking.getStatus().equals("ACTIVE")) {
                totalRevenue += booking.getTotalPrice();
                activeCount++;
            } else {
                cancelledCount++;
            }
        }

        System.out.println(Validator.repeat("-", 50));
        System.out.println("Total Bookings: " + bookings.size());
        System.out.println("Active Bookings: " + activeCount);
        System.out.println("Cancelled Bookings: " + cancelledCount);
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println(Validator.repeat("-", 50));
    }
}
