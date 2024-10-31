package Backend;

/**
 * The DreamTrackerclass implements a goal tracking system that allows users 
 * to create goals, manage tasks, and track their progress.
 * 
 * Author: Luke Barnett
 * Version: 1.0
 * Since: 10/04/2024
 * 
 * Usage:
 * This class provides functionality for users to define goals, add tasks to 
 * those goals, mark tasks as completed, and check goal progress.
 * 
 * Change Log:
 * Version 1.0 (10/04/2024):
 * - Initial creation of the DreamTracker class, along with the Goal and Task classes.
 */

 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 class Task {
     // Attributes
     private String taskName;
     private String taskDescription;
     private Date dueDate;
     private boolean isCompleted;
 
     // Constructor
     public Task(String name, String description, Date due) {
         this.taskName = name;
         this.taskDescription = description;
         this.dueDate = due;
         this.isCompleted = false;
     }
 
     // Method to mark task as completed
     public void markTaskAsCompleted() {
         this.isCompleted = true;
     }
 
     // Getter to check if task is completed
     public boolean isCompleted() {
         return this.isCompleted;
     }
 
     // Method to display task details
     public void displayTaskDetails() {
         System.out.println("Task Name: " + this.taskName);
         System.out.println("Description: " + this.taskDescription);
         System.out.println("Due Date: " + this.dueDate);
         System.out.println("Completed: " + (this.isCompleted ? "Yes" : "No"));
     }
 }
 
 class Goal {
     // Attributes
     private String goalTitle;
     private String goalDescription;
     private Date startDate;
     private Date endDate;
     private List<Task> taskList;
     private boolean isCompleted;
 
     // Constructor
     public Goal(String title, String description, Date start, Date end) {
         this.goalTitle = title;
         this.goalDescription = description;
         this.startDate = start;
         this.endDate = end;
         this.isCompleted = false;
         this.taskList = new ArrayList<>();
     }
 
     // Method to add a task to the goal
     public void addTask(String taskName, String taskDescription, Date dueDate) {
         Task task = new Task(taskName, taskDescription, dueDate);
         this.taskList.add(task);
     }
 
     // Method to mark the goal as completed
     public void markGoalAsCompleted() {
         boolean allTasksCompleted = true;
         for (Task task : taskList) {
             if (!task.isCompleted()) {
                 allTasksCompleted = false;
                 break;
             }
         }
         if (allTasksCompleted) {
             this.isCompleted = true;
         } else {
             System.out.println("Some tasks are still incomplete.");
         }
     }
 
     // Method to get goal progress as a percentage
     public double getProgress() {
         int totalTasks = taskList.size();
         int completedTasks = 0;
         for (Task task : taskList) {
             if (task.isCompleted()) {
                 completedTasks++;
             }
         }
         if (totalTasks > 0) {
             return (completedTasks / (double) totalTasks) * 100;
         } else {
             return 0;
         }
     }
 
     // Method to display goal details
     public void displayGoalDetails() {
         System.out.println("Goal Title: " + this.goalTitle);
         System.out.println("Goal Description: " + this.goalDescription);
         System.out.println("Start Date: " + this.startDate);
         System.out.println("End Date: " + this.endDate);
         System.out.println("Tasks:");
         for (Task task : taskList) {
             task.displayTaskDetails();
         }
     }
 
     // Method to check if the goal is overdue
     public boolean isOverdue() {
         Date currentDate = new Date();
         return currentDate.after(this.endDate);
     }
 }
 
 public class DreamTracker {
     public static void main(String[] args) {
         // Example usage of Goal and Task classes
         Date startDate = new Date();
         Date endDate = new Date(startDate.getTime() + (7L * 24 * 60 * 60 * 1000)); // 7 days from now
 
         Goal myGoal = new Goal("Learn Java", "Complete all Java tutorials", startDate, endDate);
         myGoal.addTask("Watch Java videos", "Watch tutorials on Java basics", new Date());
         myGoal.addTask("Complete exercises", "Finish Java coding exercises", new Date());
 
         myGoal.displayGoalDetails();
         System.out.println("Progress: " + myGoal.getProgress() + "%");
 
         // Mark tasks as completed
         myGoal.taskList.get(0).markTaskAsCompleted();
         System.out.println("Progress after completing one task: " + myGoal.getProgress() + "%");
 
         // Check if goal is overdue
         if (myGoal.isOverdue()) {
             System.out.println("Goal is overdue.");
         } else {
             System.out.println("Goal is on track.");
         }
     }
 }
 
