package manager;

import model.Facility;
import util.InputHandler;
import util.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for handling facility operations
 */
public class FacilityManager {
    private List<Facility> facilities;

    public FacilityManager() {
        this.facilities = new ArrayList<>();
        initializeDefaultFacilities();
    }

    /**
     * Initialize system with default facilities
     */
    private void initializeDefaultFacilities() {
        facilities.add(new Facility("TC001", "Tennis Court 1", "Tennis", 25.0));
        facilities.add(new Facility("TC002", "Tennis Court 2", "Tennis", 25.0));
        facilities.add(new Facility("FB001", "Football Field A", "Football", 50.0));
        facilities.add(new Facility("FB002", "Football Field B", "Football", 50.0));
        facilities.add(new Facility("BB001", "Basketball Court 1", "Basketball", 30.0));
        facilities.add(new Facility("BM001", "Badminton Court 1", "Badminton", 20.0));
        facilities.add(new Facility("BM002", "Badminton Court 2", "Badminton", 20.0));
    }

    /**
     * Add a new facility
     */
    public void addFacility() {
        System.out.println("\n=== ADD NEW FACILITY ===");
        
        String facilityId = InputHandler.getString("Enter Facility ID: ");
        if (!Validator.isValidFacilityId(facilityId)) {
            System.out.println("Invalid Facility ID!");
            return;
        }

        if (getFacilityById(facilityId) != null) {
            System.out.println("Facility ID already exists!");
            return;
        }

        String name = InputHandler.getString("Enter Facility Name: ");
        String type = InputHandler.getString("Enter Facility Type (Tennis/Football/Basketball/Badminton): ");
        double price = InputHandler.getDouble("Enter Price per Hour: $");

        if (Validator.isValidPrice(price)) {
            Facility facility = new Facility(facilityId, name, type, price);
            facilities.add(facility);
            System.out.println("\n✓ Facility added successfully!");
        } else {
            System.out.println("Invalid price!");
        }
    }

    /**
     * Display all facilities
     */
    public void displayAllFacilities() {
        System.out.println("\n=== ALL FACILITIES ===");
        if (facilities.isEmpty()) {
            System.out.println("No facilities available.");
            return;
        }

        System.out.println(Validator.repeat("-", 100));
        for (Facility facility : facilities) {
            System.out.println(facility);
        }
        System.out.println(Validator.repeat("-", 100));
        System.out.println("Total facilities: " + facilities.size());
    }

    /**
     * Display available facilities only
     */
    public void displayAvailableFacilities() {
        System.out.println("\n=== AVAILABLE FACILITIES ===");
        List<Facility> available = getAvailableFacilities();
        
        if (available.isEmpty()) {
            System.out.println("No facilities available at the moment.");
            return;
        }

        System.out.println(Validator.repeat("-", 100));
        for (Facility facility : available) {
            System.out.println(facility);
        }
        System.out.println(Validator.repeat("-", 100));
        System.out.println("Total available facilities: " + available.size());
    }

    /**
     * Get facility by ID
     */
    public Facility getFacilityById(String facilityId) {
        for (Facility facility : facilities) {
            if (facility.getFacilityId().equalsIgnoreCase(facilityId)) {
                return facility;
            }
        }
        return null;
    }

    /**
     * Get all available facilities
     */
    public List<Facility> getAvailableFacilities() {
        List<Facility> available = new ArrayList<>();
        for (Facility facility : facilities) {
            if (facility.isAvailable()) {
                available.add(facility);
            }
        }
        return available;
    }

    /**
     * Update facility status
     */
    public void updateFacilityStatus() {
        System.out.println("\n=== UPDATE FACILITY STATUS ===");
        displayAllFacilities();
        
        String facilityId = InputHandler.getString("\nEnter Facility ID to update: ");
        Facility facility = getFacilityById(facilityId);
        
        if (facility == null) {
            System.out.println("Facility not found!");
            return;
        }

        System.out.println("\nCurrent status: " + (facility.isAvailable() ? "Available" : "Unavailable"));
        System.out.println("1. Set to Available");
        System.out.println("2. Set to Unavailable");
        
        int choice = InputHandler.getInt("Enter choice: ", 1, 2);
        facility.setAvailable(choice == 1);
        System.out.println("\n✓ Facility status updated successfully!");
    }

    /**
     * Search facilities by type
     */
    public void searchFacilitiesByType() {
        System.out.println("\n=== SEARCH FACILITIES BY TYPE ===");
        String type = InputHandler.getString("Enter facility type: ");
        
        List<Facility> results = new ArrayList<>();
        for (Facility facility : facilities) {
            if (facility.getType().equalsIgnoreCase(type)) {
                results.add(facility);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No facilities found for type: " + type);
            return;
        }

        System.out.println("\n" + Validator.repeat("-", 100));
        for (Facility facility : results) {
            System.out.println(facility);
        }
        System.out.println(Validator.repeat("-", 100));
        System.out.println("Found " + results.size() + " facility(ies)");
    }

    public List<Facility> getAllFacilities() {
        return facilities;
    }
}
