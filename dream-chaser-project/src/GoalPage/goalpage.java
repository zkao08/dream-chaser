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

package GoalPage;

/**
 * The GoalPage class will display the status and information of the
 * goals the users are working on.
 */


public class GoalPage
{
	
}

//Pseudocode
Class GoalPage
		creation goalObj = new creation();	//creating an object from creation class
Methods
	setGoalName(goalName: String)
		goalName = goalObj.getGoalname();

	setDescription(description: String)
		goalDescrption = goalObj.getDescrption();

	setTargetDate(targetDate: Date)
		goalTargetDate = goalObj.getTargetDate();
	setStatus()
		status = goalObj.getisCompleted();
	
//displays goal information
displayGoal()
    PRINT "Goal: " + goalName
    PRINT "Description: " + goalDescrption
    PRINT "Target Date: " + goaltargetDate
    PRINT "Completed: " + (status ? "Yes" : "No")