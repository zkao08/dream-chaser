package Backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
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
 * Usage:
 *
 * Change Log: Version 1.0 (10/30/2024): - Updated code, moved it into Backend folder
 */

 /**
  * Utility class for handling JSON read/write operations.
  * This class abstracts all interactions with the users.Json file.
  */
public class JsonUtils {

    private static final String FILE_PATH = "users.Json";
    private static final Logger LOGGER = Logger.getLogger(JsonUtils.class.getName());
    static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads the JSON file and returns the root JsonNode. 
     * If the file does not exist, it creates a new empty ObjectNode.
     *
     * @return the root JsonNode of the JSON file, or a new empty ObjectNode if the file is missing
     */
    public static JsonNode readJsonFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                LOGGER.log(Level.WARNING, "JSON file not found, creating a new empty users.Json file.");
                return createEmptyJsonFile();
            }
            return objectMapper.readTree(file);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "I/O error reading JSON file: {0}", e.getMessage());
            return createEmptyJsonFile();
        }
    }

    /**
     * Writes the given JsonNode to the JSON file, replacing its content.
     *
     * @param root the JsonNode to write to the JSON file
     */
    public static void writeJsonFile(JsonNode root) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), root);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "I/O error writing to JSON file: {0}", e.getMessage());
        }
    }

    /**
     * Retrieves the data for a specific user from the JSON file.
     *
     * @param username the username to retrieve data for
     * @return the JsonNode representing the user's data, or null if the user is not found
     */
    public static JsonNode getUserData(String username) {
        JsonNode root = readJsonFile();
        return root.path(username).isMissingNode() ? null : root.get(username);
    }

    /**
     * Updates the data for a specific user in the JSON file.
     * If the user does not exist, it creates a new entry.
     *
     * @param username the username to update data for
     * @param userData the JsonNode representing the user's data
     */
    public static void updateUserData(String username, JsonNode userData) throws IOException {
        JsonNode root = readJsonFile();

        if (root instanceof ObjectNode) {
            ((ObjectNode) root).set(username, userData);
            writeJsonFile(root);
        } else {
            LOGGER.log(Level.SEVERE, "Root JSON node is not an ObjectNode. Cannot update user data.");
        }
    }

    /**
     * Creates a new empty JSON file with an empty root ObjectNode.
     *
     * @return a new empty ObjectNode
     */
    private static ObjectNode createEmptyJsonFile() {
        ObjectNode emptyRoot = objectMapper.createObjectNode();
        writeJsonFile(emptyRoot);
        return emptyRoot;
    }
}	// End of JsonUtils class