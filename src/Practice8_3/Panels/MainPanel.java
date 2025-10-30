package Practice8_3.Panels;

import Practice8_3.Components.BackButton;
import Practice8_3.Components.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ImageDisplay imageDisplay;

    public MainPanel(JFrame parentFrame, String imagePath) {
        setLayout(new BorderLayout());
        setupComponents(parentFrame, imagePath);
    }

    private void setupComponents(JFrame parentFrame, String imagePath) {
        JPanel topPanel = createTopPanel(parentFrame);
        add(topPanel, BorderLayout.NORTH);

        imageDisplay = new ImageDisplay();
        add(imageDisplay, BorderLayout.CENTER);

        if (imagePath != null) {
            imageDisplay.loadImage(imagePath);
        }
    }

    private JPanel createTopPanel(JFrame parentFrame) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        BackButton backButton = new BackButton(parentFrame);
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        topPanel.add(leftPanel, BorderLayout.WEST);
        return topPanel;
    }
}