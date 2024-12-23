package Frontend;

import Backend.AIAssistant;
import Backend.Task;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GoalCreationScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField goalNameField;
    private JTextField dueDateField;
    private JTextArea tasksArea;

    public GoalCreationScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("Create a New Goal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Goal Name input
        this.add(new JLabel("Goal Name:"));
        goalNameField = new JTextField();
        this.add(goalNameField);

        // Due Date input
        this.add(new JLabel("Due Date (yyyy-MM-dd):"));
        dueDateField = new JTextField();
        this.add(dueDateField);

        // Tasks display area
        this.add(new JLabel("Generated Tasks:"));
        tasksArea = new JTextArea(10, 30);
        tasksArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tasksArea);
        this.add(scrollPane);

        // Buttons
        JButton backButton = new JButton("Back to Progress Report");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ProgressReport"));
        this.add(backButton);

        JButton generateTasksButton = new JButton("Generate Tasks Using AI");
        generateTasksButton.addActionListener(e -> generateTasks());
        this.add(generateTasksButton);
    }

    private void generateTasks() {
        try {
            // Get input values
            String goalName = goalNameField.getText().trim();
            String dueDateString = dueDateField.getText().trim();



            if (goalName.isEmpty() || dueDateString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both goal name and due date.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse due date
            LocalDate dueDate = LocalDate.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Call AI Assistant to generate tasks
            ArrayList<Task> tasks = AIAssistant.getTasksAI(goalName, dueDate);

            System.out.println("Due Date:" + dueDate);
            System.out.println("Goal Name:" + goalName);
            System.out.println("Tasks:" + tasks);
            // Display tasks in the text area
            tasksArea.setText(""); // Clear previous tasks
            if (tasks != null && !tasks.isEmpty()) {
                for (Task task : tasks) {
                    tasksArea.append(task.toString() + "\n");
                }
            } else {
                tasksArea.setText("No tasks generated by AI.");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Task Generation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
