package Frontend;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SignInScreen extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Declare instance variables for the fields
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;  // Checkbox to toggle password visibility

    // Simulate a database of registered users (username -> password)
    private Map<String, String> registeredUsers;

    public SignInScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Initialize registered users
        registeredUsers = new HashMap<>();
        // Add some dummy users for simulation
        registeredUsers.put("user1", "password123");
        registeredUsers.put("user2", "securePass");

        // Use GridBagLayout for better control over component placement
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE); // Set a clean background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1; // Reset grid width

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        // Initialize the usernameField here
        usernameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        // Initialize the passwordField here
        passwordField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        showPasswordCheckBox.addActionListener(e -> togglePasswordVisibility());
        add(showPasswordCheckBox, gbc);

        // Sign In Button
        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setBackground(new Color(0, 123, 255));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        signInButton.addActionListener(e -> handleSignIn());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(signInButton, gbc);

        // Back to Sign Up Button
        JButton signUpButton = new JButton("Don't have an account? Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBackground(new Color(220, 53, 69));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(signUpButton, gbc);
    }

    // Method to toggle the visibility of the password field
    private void togglePasswordVisibility() {
        if (showPasswordCheckBox.isSelected()) {
            passwordField.setEchoChar((char) 0);  // Show the password as plain text
        } else {
            passwordField.setEchoChar('*');  // Hide the password
        }
    }

    private void handleSignIn() {
        // Retrieve user input
        String username = usernameField.getText();  // This will now work because usernameField is an instance variable
        String password = new String(passwordField.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the username exists and the password matches
        if (!registeredUsers.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Account does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!registeredUsers.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show loading screen
        cardLayout.show(mainPanel, "Loading");

        // Simulate the sign-in process (e.g., checking the username/password)
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                Thread.sleep(2000); // Simulate loading time
                return null;
            }

            @Override
            protected void done() {
                // If sign-in is successful, show the progress report (or main app screen)
                JOptionPane.showMessageDialog(mainPanel, "Sign In Successful! Welcome, " + username + "!");
                cardLayout.show(mainPanel, "ProgressReport"); // Switch to the next screen (e.g., ProgressReport)
            }
        };

        worker.execute();
    }
}
