/* 
 * Start Class this way... for frontend pages?
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class HomePage extends GridPane {
    private DreamChaserApp app;

    public HomePage(DreamChaserApp app) {
        this.app = app;
    }
}
 */

package GoalCreation;

/**
 * The creation class will help create the goals that want to be 	
 * reached by the users. It represents a personal or project goal 	
 * with a title, descrption, deadline, and completion status. 
 */

public class creation
{
	
}

//Pseudocode
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

    
        
        