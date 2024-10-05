package Frontend;

/* HomePage.java
 * Created By: Anointiyae Beasley
 * Date Created: 10/02/2024
 * Version: 1.0
 * Description: This class represents the Home Page of the Dream Chaser goal tracking application.
 * It provides the user interface for entering a goal and estimated time to complete the goal.
 * The page will include buttons for submitting the goal, viewing the main goal page,adding time to a goal, 
 * viewing the study session page and accessing settings.
 * 
 * Usage:
 * - Instantiate the HomePage with a reference to the main DreamChaserApp instance.
 * - Includes handlers for submitting goals and navigating to other pages.
 * 
 * Changelog:
 * - Version 1.0: Initial version with the starting UI elements and layout set up
 */


public class HomePage extends GridPane {
    private DreamChaserApp app;

    public HomePage(DreamChaserApp app) {
        this.app = app;

        // Set up the layout
        setPadding(new javafx.geometry.Insets(10));
        setVgap(8);
        setHgap(10);

        // Create UI elements
        Label goalLabel = new Label("Enter your goal:");
        TextField goalInput = new TextField();
        Label timeLabel = new Label("Estimated time (hours):");
        TextField timeInput = new TextField();
        Button submitButton = new Button("Submit");
        Button statsButton = new Button("View Statistics");
        Button settingsButton = new Button("Settings");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);


    }
}
