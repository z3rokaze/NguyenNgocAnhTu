package view;

import manager.BookingManager;
import manager.FacilityManager;
import util.InputHandler;
import util.Validator;

/**
 * Main menu interface for the Sports Booking System
 */
public class Menu {
    private FacilityManager facilityManager;
    private BookingManager bookingManager;

    public Menu() {
        this.facilityManager = new FacilityManager();
        this.bookingManager = new BookingManager(facilityManager);
    }

    /**
     * Display main menu and handle user choices
     */
    public void showMainMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + Validator.repeat("=", 60));
            System.out.println("    SPORTS BOOKING SYSTEM - MAIN MENU");
            System.out.println(Validator.repeat("=", 60));
            System.out.println("1.  Facility Management");
            System.out.println("2.  Booking Management");
            System.out.println("3.  View Statistics");
            System.out.println("0.  Exit");
            System.out.println(Validator.repeat("=", 60));

            int choice = InputHandler.getInt("Enter your choice: ", 0, 3);

            switch (choice) {
                case 1:
                    showFacilityMenu();
                    break;
                case 2:
                    showBookingMenu();
                    break;
                case 3:
                    showStatisticsMenu();
                    break;
                case 0:
                    if (InputHandler.getConfirmation("Are you sure you want to exit?")) {
                        System.out.println("\nThank you for using Sports Booking System!");
                        System.out.println("Goodbye!\n");
                        return;
                    }
                    break;
            }
        }
    }

    /**
     * Display facility management menu
     */
    private void showFacilityMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + Validator.repeat("=", 60));
            System.out.println("    FACILITY MANAGEMENT");
            System.out.println(Validator.repeat("=", 60));
            System.out.println("1.  View All Facilities");
            System.out.println("2.  View Available Facilities");
            System.out.println("3.  Add New Facility");
            System.out.println("4.  Update Facility Status");
            System.out.println("5.  Search Facilities by Type");
            System.out.println("0.  Back to Main Menu");
            System.out.println(Validator.repeat("=", 60));

            int choice = InputHandler.getInt("Enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    facilityManager.displayAllFacilities();
                    InputHandler.pause();
                    break;
                case 2:
                    facilityManager.displayAvailableFacilities();
                    InputHandler.pause();
                    break;
                case 3:
                    facilityManager.addFacility();
                    InputHandler.pause();
                    break;
                case 4:
                    facilityManager.updateFacilityStatus();
                    InputHandler.pause();
                    break;
                case 5:
                    facilityManager.searchFacilitiesByType();
                    InputHandler.pause();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Display booking management menu
     */
    private void showBookingMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + Validator.repeat("=", 60));
            System.out.println("    BOOKING MANAGEMENT");
            System.out.println(Validator.repeat("=", 60));
            System.out.println("1.  Create New Booking");
            System.out.println("2.  View All Bookings");
            System.out.println("3.  View Active Bookings");
            System.out.println("4.  Cancel Booking");
            System.out.println("5.  Search Bookings by Customer Name");
            System.out.println("6.  Search Bookings by Phone Number");
            System.out.println("7.  View Bookings by Date");
            System.out.println("0.  Back to Main Menu");
            System.out.println(Validator.repeat("=", 60));

            int choice = InputHandler.getInt("Enter your choice: ", 0, 7);

            switch (choice) {
                case 1:
                    bookingManager.createBooking();
                    InputHandler.pause();
                    break;
                case 2:
                    bookingManager.displayAllBookings();
                    InputHandler.pause();
                    break;
                case 3:
                    bookingManager.displayActiveBookings();
                    InputHandler.pause();
                    break;
                case 4:
                    bookingManager.cancelBooking();
                    InputHandler.pause();
                    break;
                case 5:
                    bookingManager.searchBookingsByCustomer();
                    InputHandler.pause();
                    break;
                case 6:
                    bookingManager.searchBookingsByPhone();
                    InputHandler.pause();
                    break;
                case 7:
                    bookingManager.displayBookingsByDate();
                    InputHandler.pause();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Display statistics menu
     */
    private void showStatisticsMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n" + Validator.repeat("=", 60));
            System.out.println("    STATISTICS & REPORTS");
            System.out.println(Validator.repeat("=", 60));
            System.out.println("1.  View Revenue Statistics");
            System.out.println("2.  View Facility Summary");
            System.out.println("3.  View Booking Summary");
            System.out.println("0.  Back to Main Menu");
            System.out.println(Validator.repeat("=", 60));

            int choice = InputHandler.getInt("Enter your choice: ", 0, 3);

            switch (choice) {
                case 1:
                    bookingManager.displayRevenue();
                    InputHandler.pause();
                    break;
                case 2:
                    facilityManager.displayAllFacilities();
                    InputHandler.pause();
                    break;
                case 3:
                    bookingManager.displayAllBookings();
                    InputHandler.pause();
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Clear console screen
     */
    private void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            // If clear doesn't work, just print some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
