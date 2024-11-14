package Backend;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class StatisticsTester {

    public static void main(String[] args) {
        testCreateNewUser();
        testUpdateUserStatistics();
        testRetrieveUserStatistics();
        testDisplayUserStatistics();
    }

    public static void testCreateNewUser() {
        String username = "testUser1";

        try {
            JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

            if (userStats != null) {
                System.out.println("testCreateNewUser: SUCCESS - Default statistics created for user " + username);
            } else {
                System.out.println("testCreateNewUser: FAILURE - Could not create default statistics for user " + username);
            }
        } catch (IOException e) {
            System.err.println("testCreateNewUser: ERROR - " + e.getMessage());
        }
    }

    public static void testUpdateUserStatistics() {
        String username = "testUser1";
        int totalTimeSpent = 5;
        int totalTasks = 10;
        int completedTasks = 7;

        StatisticsService.saveStatistics(username, totalTimeSpent, totalTasks, completedTasks);

        try {
            JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

            if (userStats != null &&
                userStats.get("totalTimeSpent").asInt() == totalTimeSpent &&
                userStats.get("totalTasks").asInt() == totalTasks &&
                userStats.get("completedTasks").asInt() == completedTasks) {

                System.out.println("testUpdateUserStatistics: SUCCESS - Statistics updated for user " + username);
            } else {
                System.out.println("testUpdateUserStatistics: FAILURE - Statistics not updated correctly for user " + username);
            }
        } catch (IOException e) {
            System.err.println("testUpdateUserStatistics: ERROR - " + e.getMessage());
        }
    }

    public static void testRetrieveUserStatistics() {
        String username = "testUser1";

        try {
            JsonNode userStats = StatisticsFileManager.getUserStatistics(username);

            if (userStats != null) {
                System.out.println("testRetrieveUserStatistics: SUCCESS - Retrieved statistics for user " + username);
            } else {
                System.out.println("testRetrieveUserStatistics: FAILURE - No statistics found for user " + username);
            }
        } catch (IOException e) {
            System.err.println("testRetrieveUserStatistics: ERROR - " + e.getMessage());
        }
    }

    public static void testDisplayUserStatistics() {
        String username = "testUser1";

        String result = StatisticsService.displayStatistics(username);
        if (result.contains("User: " + username)) {
            System.out.println("testDisplayUserStatistics: SUCCESS - Displayed statistics for user " + username);
        } else {
            System.out.println("testDisplayUserStatistics: FAILURE - Could not display statistics for user " + username);
        }
    }
}
