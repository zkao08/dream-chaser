package Backend;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.time.LocalDate;

/**
 * The AIAssistant class integrates with a generative AI API to provide users 
 * with assistance in creating and managing goals.
 * 
 * @Author: Zachary Kao
 * @Version: 1.1
 * @Since: 10/20/2024
 * 
 * Usage:
 * This class allows users to leverage AI capabilities 
 * for estimating task durations and generating task lists from goals.
 * 
 * Change Log:
 * Version 1.0 (10/04/2024):
 * - Initial creation of the AIAssistant class.
 * Version 1.1 (10/20/2024):
 * - Import ChatGPT integration test code
 */
public class AIAssistant
{
	public static void loadEnvFile(String filePath) 
	{
        Properties properties = new Properties();
        try 
        {
            File envFile = new File(filePath);
            if (envFile.exists()) 
            {
                FileInputStream fileInputStream = new FileInputStream(envFile);
                properties.load(fileInputStream);

                // Set each property as a system environment variable
                for (String key : properties.stringPropertyNames()) 
                {
                    String value = properties.getProperty(key);
                    System.setProperty(key, value);
                }

                fileInputStream.close();
            } 
            else
            {
                System.out.println(".env file not found at " + filePath);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
	public static String chatGPT(String prompt) 
	{
       String url = "https://api.openai.com/v1/chat/completions";
       String apiKey = System.getProperty("OPENAI_API_KEY");
       String model = "gpt-3.5-turbo";

       try 
       {
           URL obj = new URL(url);
           HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
           connection.setRequestMethod("POST");
           connection.setRequestProperty("Authorization", "Bearer " + apiKey);
           connection.setRequestProperty("Content-Type", "application/json");

           // The request body
           String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
           connection.setDoOutput(true);
           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
           writer.write(body);
           writer.flush();
           writer.close();

           // Response from ChatGPT
           BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           String line;

           StringBuffer response = new StringBuffer();

           while ((line = br.readLine()) != null) 
           {
               response.append(line);
           }
           br.close();

           // calls the method to extract the message.
           return extractMessageFromJSONResponse(response.toString());

       } 
       catch (IOException e) 
       {
           throw new RuntimeException(e);
       }
   }

   public static String extractMessageFromJSONResponse(String response) 
   {
       int start = response.indexOf("content")+ 11;

       int end = response.indexOf("\"", start);

       String extractedMessage = response.substring(start, end);
       
       return extractedMessage.replace("\\n", "\n");

   }

   public static void main(String[] args)
   {
	   AIAssistant.loadEnvFile(".env");
       System.out.println(chatGPT("Give me a list of tasks to break down the goal 'Learn to code in Java' by '2024-12-31' "
       							+ "starting " + LocalDate.now() + " into smaller achievable tasks. "
       							+ "Format the output in a csv as follows, which each task on a new line: 'task name, task description, task due date' "
       							+ "The dates should be in java.time.LocalDate format and the csv should not be numbered and the first line should be the format specification."));

   }
	
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
