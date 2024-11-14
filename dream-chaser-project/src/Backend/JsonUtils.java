package Backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
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
 * Usage:
 *
 * Change Log: Version 1.0 (10/30/2024): - Updated code, moved it into Backend folder
 */

 /**
  * Utility class for handling JSON read/write operations.
  * This class abstracts all interactions with the users.Json file.
  */
 public class JsonUtils {

     // Constants for file paths and logger
     private static final String FILE_PATH = "UserData/users.Json";  // Main JSON file where user data is stored
     private static final Logger logger = Logger.getLogger(JsonUtils.class.getName());  // Logger for logging errors and info
     public static ObjectMapper objectMapper = new ObjectMapper();  // ObjectMapper to handle JSON operations

     /**
      * Reads the JSON data from users.Json file and returns it as a JsonNode.
      * This method handles reading from the file and ensuring that the file exists.
      * @return JsonNode containing all user data, or an empty object if the file does not exist.
      * @throws IOException if there's an issue reading the file.
      */
     public static JsonNode readJsonFile() throws IOException {
         File file = new File(FILE_PATH);

         // Check if the JSON file exists, and create an empty one if it doesn't
         if (!file.exists()) {
             logger.warning("JSON file not found, creating a new empty users.Json file.");
             file.createNewFile();  // Create an empty file
             objectMapper.writeValue(file, objectMapper.createObjectNode());  // Write an empty JSON object to the new file
         }

         // Read and return the content of the file as a JsonNode
         return objectMapper.readTree(file);
     }

     /**
      * Writes the given JsonNode to the users.Json file.
      * This method ensures that the JSON is pretty-printed (formatted for readability).
      * @param jsonNode The JsonNode data to write.
      * @throws IOException if there's an issue writing to the file.
      */
     public static void writeJsonFile(JsonNode jsonNode) throws IOException {
         File file = new File(FILE_PATH);

         // Write the updated JSON data to the file with pretty-print formatting for readability
         objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, jsonNode);
         logger.info("JSON data successfully written to " + FILE_PATH);
     }

     /**
      * Updates a specific user's data in the JSON file.
      * If the user does not exist, their data is added. If they do exist, their data is updated.
      * @param username The key (username) to update in the JSON.
      * @param userData The user's statistics data as a JsonNode.
      * @throws IOException if there's an issue reading or writing the file.
      */
     public static void updateUserData(String username, JsonNode userData) throws IOException {
         // Read the current JSON file content
         JsonNode rootNode = readJsonFile();

         // Cast rootNode as an ObjectNode to allow modification (ObjectNode allows adding/updating fields)
         if (rootNode.isObject()) {
             ((ObjectNode) rootNode).set(username, userData);  // Update or add the user data
         }

         // Write the updated JSON data back to the file
         writeJsonFile(rootNode);
         logger.info("Updated statistics for user: " + username);
     }

     /**
      * Retrieves a specific user's data from the JSON file.
      * @param username The username of the user whose data to retrieve.
      * @return The user's statistics data as a JsonNode, or null if the user doesn't exist.
      * @throws IOException if there's an issue reading the file.
      */
     public static JsonNode getUserData(String username) throws IOException {
         // Read the JSON data from the file
         JsonNode rootNode = readJsonFile();

         // Return the user's data if found, else return null
         if (rootNode.has(username)) {
             logger.info("User found: " + username);
             return rootNode.get(username);
         } else {
             logger.info("User not found: " + username);
             return null;
         }
     }
 }
