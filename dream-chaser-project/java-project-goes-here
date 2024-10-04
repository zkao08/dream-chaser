package Statistics;

// Import necessary libraries
import java.util.List;

// Task Model
class Task {
    private String name;
    private boolean isCompleted;
    private int timeSpent;

    // Constructor
    public Task(String name, boolean isCompleted, int timeSpent) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.timeSpent = timeSpent;
    }

    // Getters
    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}

// Goal Model
class Goal {
    private int goalID;
    private String name;
    private List<Task> tasks;

    // Constructor
    public Goal(int goalID, String name, List<Task> tasks) {
        this.goalID = goalID;
        this.name = name;
        this.tasks = tasks;
    }

    // Getters
    public int getGoalID() {
        return goalID;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Setters
    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

// Statistics Model
class Statistics {
    private int totalTimeSpent;
    private int totalTasks;
    private int completedTasks;
    private double completionPercentage;

    // Constructor
    public Statistics(int totalTimeSpent, int totalTasks, int completedTasks, double completionPercentage) {
        this.totalTimeSpent = totalTimeSpent;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.completionPercentage = completionPercentage;
    }

    // Getters
    public int getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    // Setters
    public void setTotalTimeSpent(int totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}

// Service class for fetching and calculating statistics
public class StatisticsService {

    // Simulate fetching goals for a user (to be replaced with database logic)
    public List<Goal> fetchGoalsFromDatabase(String userID) {
        // Simulate goals for demonstration purposes
        return mockGoalsForUser(userID);
    }

    // Method to calculate statistics for a user based on goals
    public Statistics calculateStatistics(String userID) {
        List<Goal> goals = fetchGoalsFromDatabase(userID);
        int totalTimeSpent = 0;
        int totalTasksCompleted = 0;
        int totalTasks = 0;

        // Loop through each goal and its tasks
        for (Goal goal : goals) {
            List<Task> tasks = goal.getTasks();
            totalTasks += tasks.size();

            for (Task task : tasks) {
                if (task.isCompleted()) {
                    totalTasksCompleted++;
                }
                totalTimeSpent += task.getTimeSpent();
            }
        }

        // Calculate completion percentage
        double completionPercentage = (totalTasks > 0) ? ((double) totalTasksCompleted / totalTasks) * 100 : 0.0;

        // Return the calculated statistics
        return new Statistics(totalTimeSpent, totalTasks, totalTasksCompleted, completionPercentage);
    }

    // Placeholder to simulate goal data (to be replaced with actual database retrieval later)
    private List<Goal> mockGoalsForUser(String userID) {
        // Mock goals and tasks for testing purposes
        return List.of(new Goal(1, "Example Goal 1", List.of(
                new Task("Task 1", true, 2),
                new Task("Task 2", false, 3))),
                new Goal(2, "Example Goal 2", List.of(
                new Task("Task 1", true, 5))));
    }
}