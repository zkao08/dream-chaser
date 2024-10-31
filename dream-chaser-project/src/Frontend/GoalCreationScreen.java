package Frontend;

import java.awt.*;
import javax.swing.*;

public class GoalCreationScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GoalCreationScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Create a New Goal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel);

        // Example form for creating a goal
        add(new JLabel("Goal Name:"));
        add(new JTextField());
        add(new JLabel("Due Date:"));
        add(new JTextField());
        add(new JLabel("Target Hours:"));
        add(new JTextField());

        // Add "Back to Progress Report" button
        JButton backButton = new JButton("Back to Progress Report");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ProgressReport"));
        add(backButton);
    }
}

