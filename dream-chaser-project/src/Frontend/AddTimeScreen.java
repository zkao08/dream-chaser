package Frontend;

import java.awt.*;
import javax.swing.*;

public class AddTimeScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AddTimeScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Add Time to Goal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel);

        // Example content for adding time
        add(new JLabel("Select Goal:"));
        add(new JComboBox<>(new String[] {"HTML", "CSS", "Vue"}));
        add(new JLabel("Time Spent (hours):"));
        add(new JTextField());

        // Add "Back to Progress Report" button
        JButton backButton = new JButton("Back to Progress Report");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ProgressReport"));
        add(backButton);
    }
}


