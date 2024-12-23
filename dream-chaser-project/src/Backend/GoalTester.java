package Backend;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class GoalTester {
    public static void main(String[] args) {
        // Create tasks
        Task task1 = new Task("Research project requirements", 5, 0);
        Task task2 = new Task("Design project structure", 3, 30);
        Task task3 = new Task("Implement core features", 10, 0);
        Task task4 = new Task("Test project thoroughly", 4, 0);

        // Add tasks to a list
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        // Create a goal with tasks
        Goal goal = new Goal("Develop Personal Project Tracker", tasks, LocalDate.of(2024, 12, 31));

        // Display goal and task details
        System.out.println("Goal Created:");
        System.out.println("Name: " + goal.getGoalName());
        System.out.println("Tasks:");
        for (Task task : goal.getTasks()) {
            System.out.println(" - " + task);
        }
    }
}
