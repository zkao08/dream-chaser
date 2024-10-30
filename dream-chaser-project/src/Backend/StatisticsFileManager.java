package Backend;

import java.io.File;
import java.io.IOException;
import org.json.JSONObject;
import java.util.logging.Logger;

/**
 * StatisticsFileManager.java Created By: Max Henson Date Created: 10/03/2024
 * Version: 1.0
 *
 * Description: The StatisticsFileManager class handles the reading and writing
 * of user statistics to and from JSON files..
 *
 * Usage: 
 * 1. Create an instance of StatisticsFileManager. 
 * 2. Use the
 * writeStatisticsToJson(String userID, Statistics statistics) method to save a
 * Statistics object to a JSON file. 
 * 3. Use the readStatisticsFromJson(String
 * userID) method to retrieve a Statistics object from a JSON file.
 *
 * Change Log: 
 * Version 1.0 (10/03/2024): - Initial implementation with methods
 * to write and read statistics from JSON files. - Placeholder methods for JSON
 * conversion and file I/O operations.
 * Version 1.1 (10/30/2024): - Overhaul of code - Placeholders replaced with methods
 * - Updated implementation of reading and writing
 */

/**
 * Manages the reading and writing of user statistics to and from the JSON file.
 * This class interacts with jsonUtils for JSON operations and handles input
 * validation and logging.
 */
public class StatisticsFileManager {

    private static final Logger logger = Logger.getLogger(StatisticsFileManager.class.getName());  // Logger for error and info handling

    /**
     * Creates default statistics for a new user in the system.
     *
     * @param username The username for which to create the default statistics.
     * @return JSONObject containing default user statistics.
     */
    private static JSONObject createDefaultStatistics(String username) {
        // Create a new JSONObject for storing default statistics
        JSONObject defaultStats = new JSONObject();
        defaultStats.put("totalTimeSpent", 0);
        defaultStats.put("totalTasks", 0);
        defaultStats.put("completedTasks", 0);
        defaultStats.put("completionPercentage", 0.0);
        defaultStats.put("lastUpdated", java.time.LocalDateTime.now().toString());

        logger.info("Created default statistics for new user: " + username);
        return defaultStats;
    }

    /**
     * Saves a user's statistics data to the JSON file. If the user already
     * exists, their data is updated. Otherwise, a new entry is created. This
     * method includes input validation to ensure data integrity.
     *
     * @param username The username of the user.
     * @param totalTimeSpent The total time spent by the user.
     * @param totalTasks The total number of tasks the user has.
     * @param completedTasks The number of tasks completed by the user.
     * @param completionPercentage The percentage of tasks completed.
     */
    public static void saveUserStatistics(String username, int totalTimeSpent, int totalTasks, int completedTasks, double completionPercentage) {
        try {
            // Input validation to ensure valid statistics data
            if (totalTimeSpent < 0 || totalTasks < 0 || completedTasks < 0 || completedTasks > totalTasks || completionPercentage < 0 || completionPercentage > 100) {
                throw new IllegalArgumentException("Invalid statistics data provided for user: " + username);
            }

            // Create a new JSONObject for the user's statistics
            JSONObject userStats = new JSONObject();
            userStats.put("totalTimeSpent", totalTimeSpent);
            userStats.put("totalTasks", totalTasks);
            userStats.put("completedTasks", completedTasks);
            userStats.put("completionPercentage", completionPercentage);
            userStats.put("lastUpdated", java.time.LocalDateTime.now().toString());

            // Update the JSON file with the user's statistics
            jsonUtils.updateUserData(username, userStats);

            logger.info("User statistics saved successfully for user: " + username);

        } catch (IllegalArgumentException | IOException e) {
            logger.severe("Error saving user statistics: " + e.getMessage());
        }
    }

    /**
     * Retrieves a user's statistics from the JSON file. If the user does not
     * exist, a new entry is created with default values.
     *
     * @param username The username of the user.
     * @return A JSONObject containing the user's statistics, or null if an
     * error occurs.
     */
    public static JSONObject getUserStatistics(String username) {
        try {
            // Retrieve the user's statistics from the JSON file
            JSONObject userStats = jsonUtils.getUserData(username);

            // If the user doesn't exist, automatically create a new entry with default statistics
            if (userStats == null) {
                logger.info("User not found, creating a new entry for user: " + username);
                JSONObject defaultStats = createDefaultStatistics(username);
                jsonUtils.updateUserData(username, defaultStats);
                return defaultStats;  // Return the newly created default stats
            }

            return userStats;

        } catch (IOException e) {
            logger.severe("Error retrieving user statistics: " + e.getMessage());
            return null;
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * Manages the reading and writing of user statistics to and from a JSON file.
 * This class interacts with the JSON file using `org.json` library.
 */

public class StatisticsFileManager {

    private static final String FILE_PATH = "users.json";  // Path to the JSON file
    private static final Logger logger = Logger.getLogger(StatisticsFileManager.class.getName());  // Logger for logging info

    /*
     * Saves a user's statistics data to the JSON file.
     * If the user already exists, their data is updated. Otherwise, a new entry is created.
     * This method includes input validation to ensure data integrity.
     * @param username The username of the user.
     * @param totalTimeSpent The total time spent by the user.
     * @param totalTasks The total number of tasks the user has.
     * @param completedTasks The number of tasks completed by the user.
     * @param completionPercentage The percentage of tasks completed.
     */
    public static void saveUserStatistics(String username, int totalTimeSpent, int totalTasks, int completedTasks, double completionPercentage) {
        try {
            // Input validation to ensure valid statistics data
            if (totalTimeSpent < 0 || totalTasks < 0 || completedTasks < 0 || completedTasks > totalTasks || completionPercentage < 0 || completionPercentage > 100) {
                throw new IllegalArgumentException("Invalid statistics data provided for user: " + username);
            }

            // Load the existing JSON file
            JSONObject allUsersData = loadJsonFile();

            // Create a new JSONObject for the user's statistics
            JSONObject userStats = new JSONObject();
            userStats.put("totalTimeSpent", totalTimeSpent);
            userStats.put("totalTasks", totalTasks);
            userStats.put("completedTasks", completedTasks);
            userStats.put("completionPercentage", completionPercentage);
            userStats.put("lastUpdated", java.time.LocalDateTime.now().toString());

            // Update the JSON object with the new or updated user data
            allUsersData.put(username, userStats);

            // Save the updated JSON object to the file
            saveJsonFile(allUsersData);

            logger.info("User statistics saved successfully for user: " + username);

        } catch (IOException | IllegalArgumentException e) {
            logger.severe("Error saving user statistics: " + e.getMessage());
        }
    }

    /**
     * Loads the JSON data from the file.
     * @return JSONObject containing all user data.
     * @throws IOException if the file cannot be read.
     */
    private static JSONObject loadJsonFile() throws IOException {
        FileReader reader = new FileReader(FILE_PATH);
        StringBuilder content = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            content.append((char) i);
        }
        reader.close();
        return new JSONObject(content.toString());
    }

    /**
     * Saves the given JSONObject to the JSON file.
     * @param jsonData The JSONObject data to write.
     * @throws IOException if the file cannot be written to.
     */
    private static void saveJsonFile(JSONObject jsonData) throws IOException {
        FileWriter file = new FileWriter(FILE_PATH);
        file.write(jsonData.toString(4));  // Pretty print with indentation
        file.flush();
        file.close();
    }
}



// Added code on 10/30/2024, potentially replacing above code.
// Keep commented out until otherwise specified
/*
import org.json.JSONObject;
import java.io.IOException;
import java.util.logging.Logger;
*/

/*
 * Manages the reading and writing of user statistics to and from the JSON file.
 * This class interacts with jsonUtils for JSON operations and handles input validation and logging.
 */

/* 

public class StatisticsFileManager {

    private static final Logger logger = Logger.getLogger(StatisticsFileManager.class.getName());  // Logger for error and info handling

    /**
     * Creates default statistics for a new user in the system.
     *
     * @param username The username for which to create the default statistics.
     * @return JSONObject containing default user statistics.
     */
    
    /*////////////////////////////////////////////////////////////////////////////////
    
    private static JSONObject createDefaultStatistics(String username) {
        // Create a new JSONObject for storing default statistics
        JSONObject defaultStats = new JSONObject();
        defaultStats.put("totalTimeSpent", 0);
        defaultStats.put("totalTasks", 0);
        defaultStats.put("completedTasks", 0);
        defaultStats.put("completionPercentage", 0.0);
        defaultStats.put("lastUpdated", java.time.LocalDateTime.now().toString());
    
        logger.info("Created default statistics for new user: " + username);
        return defaultStats;
    }
    
    /**
     * Saves a user's statistics data to the JSON file. If the user already
     * exists, their data is updated. Otherwise, a new entry is created. This
     * method includes input validation to ensure data integrity.
     *
     * @param username The username of the user.
     * @param totalTimeSpent The total time spent by the user.
     * @param totalTasks The total number of tasks the user has.
     * @param completedTasks The number of tasks completed by the user.
     * @param completionPercentage The percentage of tasks completed.
     */
    
    /*////////////////////////////////////////////////////////////////////////////////
     
    public static void saveUserStatistics(String username, int totalTimeSpent, int totalTasks, int completedTasks, double completionPercentage) {
        try {
            // Input validation to ensure valid statistics data
            if (totalTimeSpent < 0 || totalTasks < 0 || completedTasks < 0 || completedTasks > totalTasks || completionPercentage < 0 || completionPercentage > 100) {
                throw new IllegalArgumentException("Invalid statistics data provided for user: " + username);
            }
    
            // Create a new JSONObject for the user's statistics
            JSONObject userStats = new JSONObject();
            userStats.put("totalTimeSpent", totalTimeSpent);
            userStats.put("totalTasks", totalTasks);
            userStats.put("completedTasks", completedTasks);
            userStats.put("completionPercentage", completionPercentage);
            userStats.put("lastUpdated", java.time.LocalDateTime.now().toString());
    
            // Update the JSON file with the user's statistics
            jsonUtils.updateUserData(username, userStats);
    
            logger.info("User statistics saved successfully for user: " + username);
    
        } catch (IllegalArgumentException | IOException e) {
            logger.severe("Error saving user statistics: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves a user's statistics from the JSON file. If the user does not
     * exist, a new entry is created with default values.
     *
     * @param username The username of the user.
     * @return A JSONObject containing the user's statistics, or null if an
     * error occurs.
     */

    /*////////////////////////////////////////////////////////////////////////////////
    
    public static JSONObject getUserStatistics(String username) {
        try {
            // Retrieve the user's statistics from the JSON file
            JSONObject userStats = jsonUtils.getUserData(username);

            // If the user doesn't exist, automatically create a new entry with default statistics
            if (userStats == null) {
                logger.info("User not found, creating a new entry for user: " + username);
                JSONObject defaultStats = createDefaultStatistics(username);
                jsonUtils.updateUserData(username, defaultStats);
                return defaultStats;  // Return the newly created default stats
            }

            return userStats;

        } catch (IOException e) {
            logger.severe("Error retrieving user statistics: " + e.getMessage());
            return null;
        }
    }
}
    */
