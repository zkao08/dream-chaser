package Frontend;

import java.awt.*;
import javax.swing.*;

public class SignInScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SignInScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new GridLayout(4, 2));
        add(new JLabel("Username:"));
        add(new JTextField());
        add(new JLabel("Password:"));
        add(new JPasswordField());
        
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> handleSignIn());
        add(signInButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        add(signUpButton);
    }

    private void handleSignIn() {
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


