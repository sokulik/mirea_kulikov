package Practice8_3;

import Practice8_3.Panels.MainPanel;

import javax.swing.*;

public class Task3 extends JFrame {
    private static JFrame mainMenu;

    public Task3(JFrame mainMenu, String imagePath) {
        Task3.mainMenu = mainMenu;
        initializeFrame();
        setupComponents(imagePath);
    }

    private void initializeFrame() {
        setTitle("Задание 3: Картинка");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
    }

    private void setupComponents(String imagePath) {
        MainPanel mainPanel = new MainPanel(this, imagePath);
        add(mainPanel);
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        }
    }

    public static void start(JFrame mainMenu) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task3(mainMenu, null).setVisible(true);
            }
        });
    }

    public static void startWithImage(JFrame mainMenu, String imagePath) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task3(mainMenu, imagePath).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        String imagePath = args.length > 0 ? args[0] : null;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task3(null, imagePath).setVisible(true);
            }
        });
    }
}