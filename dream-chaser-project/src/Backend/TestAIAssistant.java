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
 */
public class TestAIAssistant
{

	public static void main(String[] args)
	{
		//load env
		AIAssistant.loadEnvFile(".env");
		
		//prompt ChatGPT for a response
		String response = AIAssistant.chatGPT(
			"Give me a list of tasks to break down the goal 'Learn to code in python'"
			+ "starting " + LocalDate.now() + " into smaller achievable tasks. "
			+ "Format the output in a csv as follows, which each task on a new line: 'task name, hours to complete task, minutes to complete task' "
			+ "The hours and minutes should add up to the total time estimated to complete the task. "
			+ "The tasks should not be numbered and the first line should be the format specification. "
			+ "The tasks should be simple, achievable through self-study, and should not require professional instruction, public performance, or additional resources. "
			+ "Ensure task names are short, simple, and do not contain commas or special punctuation.");
		   
		//print the response
		System.out.println(response);
		
		//parse the response
		List<Task> tasks = new ArrayList<Task>();
		tasks = AIAssistant.parseCSV(response);
		
		//print the tasks
		for(Task task : tasks)
		{
			System.out.println(task);
		}
	}
}
