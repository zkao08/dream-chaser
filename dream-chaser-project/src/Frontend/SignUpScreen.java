package Frontend;

import java.awt.*;
import javax.swing.*;

public class SignUpScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SignUpScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Username:"));
        add(new JTextField());
        add(new JLabel("Password:"));
        add(new JPasswordField());
        add(new JLabel("Re-enter Password:"));
        add(new JPasswordField());
        
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> handleSignUp());
        add(signUpButton);

        JButton backButton = new JButton("Back to Sign In");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SignIn"));
        add(backButton);
    }

    private void handleSignUp() {
        // Switch to loading screen
        cardLayout.show(mainPanel, "Loading");

        // Use SwingWorker to simulate loading, then switch to Progress Report
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                Thread.sleep(2000); // Simulate loading time
                return null;
            }

            @Override
            protected void done() {
                // Switch to Progress Report after loading
                cardLayout.show(mainPanel, "ProgressReport");
            }
        };

        worker.execute();
    }
}


