// Make tasks individual objects with attributes for a name and time to complete
/**
 * Task.java
 * Author: Luke Barnett
 * Version: 2
 * Date: 10/28/2024
 * Description: Represents a task with a name and estimated time to complete.
 */

package Backend;

public class Task {
    private String taskName;
    private int timeToCompleteHours;
    private int timeToCompleteMinutes;
    private int loggedTimeHours = 0;
    private int loggedTimeMinutes = 0;
    private boolean isComplete = false;

    /**
     * Constructor for Task with name and time validation.
     */
    public Task(String taskName, int timeToCompleteHours, int timeToCompleteMinutes) {
        setTaskName(taskName);
        setTimeToComplete(timeToCompleteHours, timeToCompleteMinutes);
    }

    // Setters and Getters with input validation
    public void setTaskName(String taskName) {
        if (taskName != null && !taskName.trim().isEmpty() && taskName.length() <= 100) {
            this.taskName = taskName;
        } else {
            throw new IllegalArgumentException("Task name should be a non-empty string within 100 characters.");
        }
    }

    public void setTimeToComplete(int hours, int minutes) {
        if (hours >= 0 && hours <= 100 && minutes >= 0 && minutes < 60) { // realistic task time limit
            this.timeToCompleteHours = hours;
            this.timeToCompleteMinutes = minutes;
        } else {
            throw new IllegalArgumentException("Time to complete should be realistic (0-100 hours, 0-59 minutes).");
        }
    }

    public String getTaskName() { return taskName; }
    public int getTimeToCompleteHours() { return timeToCompleteHours; }
    public int getTimeToCompleteMinutes() { return timeToCompleteMinutes; }

    @Override
    public String toString() {
        return "Task: " + taskName + " (" + timeToCompleteHours + " hours, " + timeToCompleteMinutes + " minutes)";
    }
    
    public int getLoggedTimeHours() { return loggedTimeHours; }
    public int getLoggedTimeMinutes() { return loggedTimeMinutes; }
    
    //apply time to task
    public void logTime(int hours, int minutes)
    {
		//add the logged time to this task
    	this.loggedTimeHours += hours;
    	this.loggedTimeMinutes += minutes;
    	
    	//rollover minutes to hours
    	this.loggedTimeHours += (this.loggedTimeMinutes / 60);
    	this.loggedTimeMinutes = (this.loggedTimeMinutes % 60);
    	
    	//check if task is considered complete
    	if(this.loggedTimeHours >= this.timeToCompleteHours && this.loggedTimeMinutes >= this.timeToCompleteMinutes)
    	{
    		//mark task as complete
    		this.isComplete = true;
    	}
    }
    
    public boolean getIsComplete() { return isComplete; }
}
