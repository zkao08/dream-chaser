package Frontend;

/**
 * CreateAccountPage.java
 * Created By: Venus Ubani and Mai-Lisa Atis
 * Date Created: 10/02/2024
 * Version: 1.0
 * Description: The CreateAccountPage class provides a user interface for creating 
 * a new user account in the Dream Chaser goal tracking application. It includes fields 
 * for user input such as username, password, and email address.
 * 
 * Usage:
 * - Instantiate the CreateAccountPage class to create a new page for account creation.
 * 
 * Changelog:
 * - Version 1.0: Initial version with a basic structure for creating user accounts.
 */

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GoalCreationPage extends Stage {
    //code here
}

/*
 * //Pseudocode
Class creation

ATTRIBUTES
    goalName: String			//name of the goal
    description: String			//descrption of goal
    targetDate: Date			//date goal intended to be reached
    isCompleted: Boolean		//if the goal is completed or not

METHODS
	//sets all the goal information from creation of object
    constructor(goalName: String, description: String, targetDate: Date)
        this.goalName = goalName
        this.description = description
        this.targetDate = targetDate
        this.isCompleted = false

    //getter functions
    getGoalName(): String
        return this.goalName

    getDescription(): String
        return this.description

    getTargetDate(): Date
        return this.targetDate

    getIsCompleted(): Boolean
        return this.isCompleted

    //setter functions
    setGoalName(goalName: String)
        this.goalName = goalName

    setDescription(description: String)
        this.description = description

    setTargetDate(targetDate: Date)
        this.targetDate = targetDate

    //marks goal completed
    markComplete()
        this.isCompleted = true

    
        
        
 * 
 */