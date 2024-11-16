/**
 * Goal.java
 * Author: Luke Barnett
 * Version: 3.0
 * Date: 11/16/2024
 * Description: This class represents a goal with associated tasks, required time,
 * time logged, and start date. Provides methods to retrieve and update goal details.
 */

package Backend;

import java.util.ArrayList;
import java.time.LocalDate;

public class Goal {
	//attributes
    private String goalName;
    private String goalDescription;
    private ArrayList<Task> tasks;
    private int requiredTimeHours;
    private int requiredTimeMinutes;
    private int timeLoggedHours;
    private int timeLoggedMinutes;
    private LocalDate startDate;
    private LocalDate dueDate;

    /**
     * Constructor initializes goal attributes with validations for goal name,
     * description length, and time limits.
     */
    public Goal(String goalName, ArrayList<Task> tasks, LocalDate dueDate) {
    	//initialize attributes with input parameters
        setGoalName(goalName);
        if (this.tasks == null) {
            this.tasks = new ArrayList<>();
        } 
        
        if(tasks != null)
        {
	        for(Task task : tasks)
	        {
	        	this.addTask(task);
	        }
        }
        
        //initialize to default values
        this.timeLoggedHours = 0;
        this.timeLoggedMinutes = 0;
        this.startDate = LocalDate.now();
    }

    // Setters and Getters with input validation
    public void setGoalName(String goalName) {
        if (goalName != null && goalName.length() <= 100) {
            this.goalName = goalName;
        } else {
            throw new IllegalArgumentException("Goal name should be within 100 characters.");
        }
    }

    public void setGoalDescription(String goalDescription) {
        if (goalDescription != null && goalDescription.length() <= 300) {
            this.goalDescription = goalDescription;
        } else {
            throw new IllegalArgumentException("Goal description should be within 300 characters.");
        }
    }

    public void setRequiredTime(int hours, int minutes) {
        if (hours >= 0 && hours <= 1500 && minutes >= 0 && minutes < 60) { // realistic time limit
            this.requiredTimeHours = hours;
            this.requiredTimeMinutes = minutes;
        } else {
            throw new IllegalArgumentException("Time should be realistic, within 0-1500 hours and 0-59 minutes.");
        }
    }
    
    public void setDueDate(LocalDate dueDate)
    {
    	if(dueDate != null)
    	{
    		this.dueDate = dueDate;
    	}
    }

    public ArrayList<Task> getTasks() { return tasks; }
    
    //add a task to the goal, and increase the time it takes to complete a task
    public void addTask(Task task) 
    { 
    	tasks.add(task); 
    	
    	//add the task's time to the goal's time
    	this.requiredTimeHours += task.getTimeToCompleteHours();
    	this.requiredTimeMinutes += task.getTimeToCompleteMinutes();
    	
    	//rollover extra minutes to hours
    	this.requiredTimeHours += (int)(this.requiredTimeMinutes / 60);
    	this.requiredTimeMinutes = (this.requiredTimeMinutes % 60);
	}

    //Getter methods for attributes
    public String getGoalName() { return goalName; }
    public String getGoalDescription() { return goalDescription; }
    public int getRequiredTimeHours() { return requiredTimeHours; }
    public int getRequiredTimeMinutes() { return requiredTimeMinutes; }
    public LocalDate getStartDate() { return startDate; }
    public int getTimeLoggedHours() { return timeLoggedHours; }
    public int getTimeLoggedMinutes() { return timeLoggedMinutes; }
    public LocalDate getDueDate() { return dueDate; }
}

