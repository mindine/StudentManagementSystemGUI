package sms;

import javax.swing.SwingUtilities;

/**
 * Entry point of the Student Management System (SMS) application.
 * Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT),
 * which is the correct thread for all Swing component interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Run the SMS application in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            SMS app = new SMS(); // Instantiate the main GUI window
            app.setVisible(true); // Display the window
        });
    }
}
