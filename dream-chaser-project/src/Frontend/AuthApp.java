package Frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthApp {
    private JFrame frame;
    private JPanel panel;

    private CardLayout cardLayout;

    public AuthApp() {
        frame = new JFrame("Sign Up / Sign In");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        createSignUpPanel();
        createSignInPanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void createSignUpPanel() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(4, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton signUpButton = new JButton("Sign Up");
        JButton switchToSignInButton = new JButton("Already have an account? Sign In");

        signUpPanel.add(new JLabel("Username:"));
        signUpPanel.add(usernameField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(passwordField);
        signUpPanel.add(signUpButton);
        signUpPanel.add(switchToSignInButton);

        signUpButton.addActionListener(e -> {
            // Sign up logic here
            JOptionPane.showMessageDialog(frame, "Sign Up Successful!");
        });

        switchToSignInButton.addActionListener(e -> cardLayout.show(panel, "SignIn"));

        panel.add(signUpPanel, "SignUp");
    }

    private void createSignInPanel() {
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(new GridLayout(4, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton signInButton = new JButton("Sign In");
        JButton switchToSignUpButton = new JButton("Don't have an account? Sign Up");

        signInPanel.add(new JLabel("Username:"));
        signInPanel.add(usernameField);
        signInPanel.add(new JLabel("Password:"));
        signInPanel.add(passwordField);
        signInPanel.add(signInButton);
        signInPanel.add(switchToSignUpButton);

        signInButton.addActionListener(e -> {
            // Sign in logic here
            JOptionPane.showMessageDialog(frame, "Sign In Successful!");
        });

        switchToSignUpButton.addActionListener(e -> cardLayout.show(panel, "SignUp"));

        panel.add(signInPanel, "SignIn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthApp::new);
    }
}
