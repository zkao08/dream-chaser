package Frontend;

/*DreamChaserApp.java
 * Created By: Anointiyae Beasley
 * Date Created: 10/02/2024
 * Version: 1.0
 * Description: This JavaFX application is the main entry point for the Dream Chaser goal tracking application. 
 * It manages the primary window and navigation between various pages.  
 * 
 * Usage:
 * - Compile and run the application using JavaFX.
 * - Ensure that all dependent pages (HomePage, StatisticsPage, SettingsPage) are implemented.
 * 
* Changelog:
 * - Version 1.0: Initial version with basic navigation structure and home page display.
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DreamChaserApp extends Application {
    private StackPane mainLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dream Chaser - Goal Tracking Application");

        mainLayout = new StackPane();
        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        showHomePage(); // Show the home page first
    }

    public void showHomePage() {
        break;
    }

    public void showStatisticsPage() {
        break;
    }

    public void showStudySessionPage() {
        break;
    }

    public void showGoalCreationPage() {
        break;
    }
}
