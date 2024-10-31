/**
 * GoalCreation.java
 * Author: Luke Barnett
 * Version: 2
 * Date: 10/28/2024
 * Description: This class provides functionality for creating goals, either manually
 * by user input or automatically by AI suggestions.
 */

package Goals;

import java.util.ArrayList;
import java.util.Date;

public class GoalCreation {

    /**
     * Creates a goal manually with user-defined details.
     */
    public Goal createGoalManually(String goalName, String goalDescription, ArrayList<String> tasks, int requiredTimeHours, int requiredTimeMinutes, Date startDate) {
        // Validate tasks: each task should be a single sentence
        if (tasks != null && tasks.stream().allMatch(task -> task.length() <= 100)) {
            return new Goal(goalName, goalDescription, tasks, requiredTimeHours, requiredTimeMinutes, startDate);
        } else {
            throw new IllegalArgumentException("Each task should be a short sentence, under 100 characters.");
        }
    }

    /**
     * Creates a goal with AI suggestions for goal name, tasks, and estimated time.
     */
    public Goal createGoalWithAI() {
        // Example AI-generated goal and tasks
        String aiGoalName = "Complete Java Programming Course";
        String aiGoalDescription = "A goal to finish a comprehensive Java course covering fundamental to advanced concepts.";
        ArrayList<String> aiTasks = new ArrayList<>();
        aiTasks.add("Finish Chapter 1 - Basics");
        aiTasks.add("Complete practical exercises");
        aiTasks.add("Take the final project");

        int aiRequiredTimeHours = 300; // Estimated time in hours
        int aiRequiredTimeMinutes = 0;
        Date startDate = new Date(); // Current date as start date

        return new Goal(aiGoalName, aiGoalDescription, aiTasks, aiRequiredTimeHours, aiRequiredTimeMinutes, startDate);
    }
}