package Backend;

// Import necessary libraries
import com.fasterxml.jackson.databind.JsonNode;

/**
 * StatisticsService.java Created By: Max Henson Date Created: 10/03/2024
 * Version: 1.0
 *
 * Description:
 *
 * Usage:
 *
 * Change Log: Version 1.0 (10/30/2024): - Updated placeholder functions/code,
 * added a method to increment number of tasks a user has started
 */
/**
 * Service class responsible for managing statistics logic. It uses
 * StatisticsFileManager for reading and writing statistics and provides
 * user-facing functionality.
 */
public class StatisticsService {

    /**
     * Saves user statistics after calculating the completion percentage. This
     * method handles logic for calculating the completion percentage and
     * ensures statistics are stored correctly.
     *
     * @param username The username of the user.
     * @param totalTimeSpent Total time spent by the user.
     * @param totalTasks Total number of tasks the user has.
     * @param completedTasks Number of tasks completed by the user.
     */
    public static void saveStatistics(String username, int totalTimeSpent, int totalTasks, int completedTasks) {
        // Calculate the completion percentage (ensure it's valid based on tasks)
        double completionPercentage = (totalTasks > 0) ? ((double) completedTasks / totalTasks) * 100 : 0.0;

        // Save the statistics using the FileManager
        StatisticsFileManager.saveUserStatistics(username, totalTimeSpent, totalTasks, completedTasks, completionPercentage);
    }

    /**
     * Retrieves user statistics and formats them for display. This method is
     * designed to return statistics in a user-friendly format.
     *
     * @param username The username of the user.
     * @return A formatted string of the user's statistics, or an error message
     * if not found.
     */
    public static String displayStatistics(String username) {
        // Retrieve the user's statistics from the file
        JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

        // If the user data is found, format it for display
        if (userStats != null) {
            return String.format(
                    "User: %s\n"
                    + "Total Time Spent: %d hours\n"
                    + "Total Tasks: %d\n"
                    + "Completed Tasks: %d\n"
                    + "Completion Percentage: %.2f%%\n"
                    + "Last Updated: %s\n",
                    username,
                    userStats.get("totalTimeSpent").asInt(),
                    userStats.get("totalTasks").asInt(),
                    userStats.get("completedTasks").asInt(),
                    userStats.get("completionPercentage").asDouble(),
                    userStats.get("lastUpdated").asText()
            );
        } else {
            return "User statistics not found.";
        }
    }

    /**
     * Updates the statistics for a user by adding time and task information.
     * This method is used when a user adds more time or completes a task.
     *
     * @param username The username of the user.
     * @param additionalTime The time to add to the user's total.
     * @param tasksCompleted The additional tasks the user completed.
     */
    public static void updateStatistics(String username, int additionalTime, int tasksCompleted) {
        // Retrieve the existing statistics for the user
        JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

        if (userStats != null) {
            // Get the current statistics values
            int totalTimeSpent = userStats.get("totalTimeSpent").asInt() + additionalTime;
            int totalTasks = userStats.get("totalTasks").asInt();
            int completedTasks = userStats.get("completedTasks").asInt() + tasksCompleted;

            // Recalculate completion percentage
            double completionPercentage = (totalTasks > 0) ? ((double) completedTasks / totalTasks) * 100 : 0.0;

            // Save the updated statistics
            StatisticsFileManager.saveUserStatistics(username, totalTimeSpent, totalTasks, completedTasks, completionPercentage);
        } else {
            System.out.println("User not found: " + username);
        }
    }

    /**
     * Increment the number of total tasks for a user. This method is used when
     * a new task is added for the user.
     *
     * @param username The username of the user.
     */
    public static void addTask(String username) {
        // Retrieve the existing statistics for the user
        JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

        if (userStats != null) {
            // Increment the total tasks
            int totalTasks = userStats.get("totalTasks").asInt() + 1;
            int totalTimeSpent = userStats.get("totalTimeSpent").asInt();
            int completedTasks = userStats.get("completedTasks").asInt();

            // Recalculate completion percentage
            double completionPercentage = (totalTasks > 0) ? ((double) completedTasks / totalTasks) * 100 : 0.0;

            // Save the updated statistics
            StatisticsFileManager.saveUserStatistics(username, totalTimeSpent, totalTasks, completedTasks, completionPercentage);
        } else {
            System.out.println("User not found: " + username);
        }
    }
}
//dummy commit 3 
