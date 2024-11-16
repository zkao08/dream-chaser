package Backend;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * AIAssistant class tester
 * 
 * @Author: Zachary Kao
 * @Version: 1.0
 * @Since: 11/8/2024
 * 
 * Usage:
 * Verify that the AIAssistant class is working as intended
 * 
 * Change Log:
 * Version 1.0 (11/8/2024):
 * - Created tester for the AIAssistant class
 * Version 2.0 (11/16/2024):
 * - Update to match new verison of AIAssistant
 */

public class TestAIAssistant
{

	public static void main(String[] args)
	{
		//load env
		AIAssistant.loadEnvFile(".env");

		//now use AIAssistant to get tasks for a goal
		System.out.println("Using AI to get tasks for a goal");
		LocalDate dueDate = LocalDate.of(2024, 12, 31);
		List<Task> taskList = AIAssistant.getTasksAI("Learn to code in python", dueDate);
		
		//verify tasks were added to goal 
		for(Task task : taskList)
		{
			System.out.println(task);
		}
	}
}
