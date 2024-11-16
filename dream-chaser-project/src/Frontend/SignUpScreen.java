package Frontend;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Declare instance variables for the fields
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // Simulate a "database" to store registered users (username -> password)
    private Map<String, String> registeredUsers;

    public SignUpScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Initialize the simulated database of users
        registeredUsers = new HashMap<>();

        // Use GridBagLayout for better control over component placement
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE); // Set a clean background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Reset grid width for the next components
        gbc.gridwidth = 1;

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);  // Set a default width for the text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ensure it doesn't span multiple columns
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);  // Set a default width for the password field
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Re-enter Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);  // Set a default width for the confirm password field
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(confirmPasswordField, gbc);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(new Color(0, 123, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(e -> handleSignUp());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Make the button span both columns
        add(signUpButton, gbc);

        // Back to Sign In Button
        JButton backButton = new JButton("Back to Sign In");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 53, 69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SignIn"));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Make the button span both columns
        add(backButton, gbc);
    }

    private void handleSignUp() {
        // Retrieve user input
        String username = usernameField.getText();  // This will now work because usernameField is an instance variable
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the username already exists in the "database"
        if (registeredUsers.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Store the username and password in the "database"
        registeredUsers.put(username, password);

        // Show loading screen (simulated)
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
                // After the "sign-up" process is complete, show success message
                JOptionPane.showMessageDialog(mainPanel, "Sign Up Successful! Welcome, " + username + "!");
                cardLayout.show(mainPanel, "ProgressReport"); // Switch to the next screen (e.g., ProgressReport)
            }
        };

        worker.execute();
    }
}
