package Backend;

// github commit comment lol
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * jsonUtils.java 
 * Created By: Max Henson 
 * Date Created: 10/03/2024
 * Version: 1.0
 *
 * Description: Utility class for handling JSON read/write operations. This
 * class abstracts all interactions with the users.json file using `org.json`.
 *
 * Usage: Undetermined
 *
 * Change Log: 
 * Version 1.0 (10/30/2024): ?
 * Version 1.1 (10/30/2024): Updated code, moved file into Backend folder
 */

/**
 * 
 */
public class jsonUtils {

    private static final String FILE_PATH = "users.json";  // Path to the JSON file
    private static final Logger logger = Logger.getLogger(jsonUtils.class.getName());  // Logger for logging errors and info

    /**
     * Reads the JSON data from users.json file and returns it as a JSONObject.
     * This method handles reading from the file and ensuring that the file
     * exists.
     *
     * @return JSONObject containing all user data, or an empty object if the
     * file does not exist.
     * @throws IOException if there's an issue reading the file.
     */
    public static JSONObject readJsonFile() throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            // Read the JSON content using JSONTokener
            return new JSONObject(new JSONTokener(reader));
        } catch (IOException e) {
            logger.warning("JSON file not found, creating a new empty users.json file.");
            return new JSONObject();  // Return an empty JSONObject if file doesn't exist
        }
    }

    /**
     * Writes the given JSONObject to the users.json file. This method ensures
     * that the JSON is pretty-printed (formatted for readability).
     *
     * @param jsonData The JSONObject data to write.
     * @throws IOException if there's an issue writing to the file.
     */
    public static void writeJsonFile(JSONObject jsonData) throws IOException {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonData.toString(4));  // Pretty print with 4-space indentation
            logger.info("JSON data successfully written to " + FILE_PATH);
        }
    }

    /**
     * Updates a specific user's data in the JSON file. If the user does not
     * exist, their data is added. If they do exist, their data is updated.
     *
     * @param username The key (username) to update in the JSON.
     * @param userData The user's statistics data as a JSONObject.
     * @throws IOException if there's an issue reading or writing the file.
     */
    public static void updateUserData(String username, JSONObject userData) throws IOException {
        // Read the current JSON file content
        JSONObject allUsersData = readJsonFile();

        // Update or add the user data
        allUsersData.put(username, userData);

        // Write the updated JSON data back to the file
        writeJsonFile(allUsersData);
        logger.info("Updated statistics for user: " + username);
    }

    /**
     * Retrieves a specific user's data from the JSON file.
     *
     * @param username The username of the user whose data to retrieve.
     * @return The user's statistics data as a JSONObject, or null if the user
     * doesn't exist.
     * @throws IOException if there's an issue reading the file.
     */
    public static JSONObject getUserData(String username) throws IOException {
        // Read the JSON data from the file
        JSONObject allUsersData = readJsonFile();

        // Return the user's data if found, else return null
        if (allUsersData.has(username)) {
            logger.info("User found: " + username);
            return allUsersData.getJSONObject(username);
        } else {
            logger.info("User not found: " + username);
            return null;
        }
    }
}   // End of public class jsonUtils
