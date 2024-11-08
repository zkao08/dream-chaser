package Frontend;

import java.awt.*;
import javax.swing.*;

public class LoadingScreen extends JPanel {
    public LoadingScreen() {
        setLayout(new BorderLayout());
        
        JLabel loadingLabel = new JLabel("Loading, please wait...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // You can add a loading icon or progress indicator here
        // For example, a spinning icon or indeterminate progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Indeterminate mode for loading animation

        add(loadingLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
    }
}
