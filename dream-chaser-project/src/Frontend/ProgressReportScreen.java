package Frontend;

import java.awt.*;
import javax.swing.*;

public class ProgressReportScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ProgressReportScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Progress Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Panel to hold progress information and buttons
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Example progress bars for demonstration
        JProgressBar htmlProgress = new JProgressBar(0, 100);
        htmlProgress.setValue(60);
        htmlProgress.setStringPainted(true);
        contentPanel.add(new JLabel("Complete HTML Course"));
        contentPanel.add(htmlProgress);

        JProgressBar cssProgress = new JProgressBar(0, 100);
        cssProgress.setValue(30);
        cssProgress.setStringPainted(true);
        contentPanel.add(new JLabel("Complete CSS Course"));
        contentPanel.add(cssProgress);

        JProgressBar vueProgress = new JProgressBar(0, 100);
        vueProgress.setValue(80);
        vueProgress.setStringPainted(true);
        contentPanel.add(new JLabel("Complete Vue Course"));
        contentPanel.add(vueProgress);

        add(contentPanel, BorderLayout.CENTER);

        // Button panel for navigation options
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // "Statistics" button navigates to the Statistics screen
        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(e -> cardLayout.show(mainPanel, "Statistics"));
        buttonPanel.add(statisticsButton);

        // "Add Time" button navigates to the Add Time screen
        JButton addTimeButton = new JButton("Add Time");
        addTimeButton.addActionListener(e -> cardLayout.show(mainPanel, "AddTime"));
        buttonPanel.add(addTimeButton);

        // "Goal Creation" button navigates to the Goal Creation screen
        JButton goalCreationButton = new JButton("Create Goal");
        goalCreationButton.addActionListener(e -> cardLayout.show(mainPanel, "GoalCreation"));
        buttonPanel.add(goalCreationButton);

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel at the bottom
    }
}



