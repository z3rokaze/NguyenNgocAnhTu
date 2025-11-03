import view.Menu;
import util.Validator;

/**
 * Main class - Entry point for Sports Booking System
 * 
 * @author Sports Booking System Team
 * @version 1.0
 * 
 * This system allows users to:
 * - Manage sports facilities (tennis courts, football fields, etc.)
 * - Create and manage bookings
 * - View availability and schedules
 * - Generate revenue reports
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("\n" + Validator.repeat("=", 60));
        System.out.println("    WELCOME TO SPORTS BOOKING SYSTEM");
        System.out.println(Validator.repeat("=", 60));
        System.out.println("\nInitializing system...");
        
        try {
            Thread.sleep(1000);
            Menu menu = new Menu();
            menu.showMainMenu();
        } catch (Exception e) {
            System.err.println("Error starting system: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
