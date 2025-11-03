package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for input validation
 */
public class Validator {
    
    /**
     * Repeat a string n times (Java 8 compatible)
     */
    public static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    /**
     * Validate phone number (10-11 digits)
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        String cleaned = phone.replaceAll("[^0-9]", "");
        return cleaned.length() >= 10 && cleaned.length() <= 11;
    }

    /**
     * Validate customer name (not empty, alphabetic characters and spaces)
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.trim().matches("[a-zA-Z\\s]+") && name.trim().length() >= 2;
    }

    /**
     * Validate date format (dd/MM/yyyy)
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Validate if date is not in the past
     */
    public static boolean isValidBookingDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

    /**
     * Validate time slot (0-24 hours)
     */
    public static boolean isValidHour(int hour) {
        return hour >= 0 && hour <= 24;
    }

    /**
     * Validate price (positive number)
     */
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    /**
     * Validate facility ID format
     */
    public static boolean isValidFacilityId(String id) {
        return id != null && !id.trim().isEmpty() && id.trim().length() >= 2;
    }
}
