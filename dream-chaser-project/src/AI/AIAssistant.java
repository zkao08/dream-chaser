package AI;

/**
 * The AI Assistant class will integrate genAI API to provide the user
 * with assistance in creating and managing goals
 * 
 * @author Zachary Kao
 * @version 1.0
 * @since 10/4/2024
 */
public class AIAssistant
{
	//Static methods for getting AI assistance
	
	/**
	 * A method to prompt the AI for a suggestion on how long a task will take to accomplish
	 * 
	 * @param task : description or name of the task
	 * @return Task? data structure with task description and time estimate
	 */
	public static void getTaskTimeAI(String task)
	{
		//query the AI with a prompt such as "Give me an estimate for how long 'task' will take in the format 'task : time'"
		//return the result 
	}
	
	/**
	 * A method to prompt the AI to break down a goal into a list of tasks
	 * 
	 * @param goal : description or name of the goal
	 * @return List<Task?> : a list of tasks
	 */
	public static void getTasksAI(String goal)
	{
		//query the AI with a prompt such as "Give me a list of tasks to break down 'goal' 
		//into achievable parts in the format 'task, task, task,...'"
		
		//use the method getTaskTimeAI to get the time for each task
		//add all the tasks to a list and return it
	}
}
