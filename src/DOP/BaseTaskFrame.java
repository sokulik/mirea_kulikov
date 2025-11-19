package DOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class BaseTaskFrame extends JFrame {
    protected JFrame mainMenuFrame;

    public BaseTaskFrame(JFrame mainMenuFrame, String title) {
        super(title);
        this.mainMenuFrame = mainMenuFrame;

        // АВТОМАТИЧЕСКИ ПРИМЕНЯЕМ ФОН КО ВСЕМ TASK-ОКНАМ
        BackgroundManager.applyBackgroundToFrame(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Добавляем слушатель для возврата в главное меню
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    // Общие методы для всех Task-окон
    protected void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    protected JButton createBackButton() {
        JButton backButton = BackgroundManager.createStyledButton("← Назад к меню", new Color(108, 117, 125));
        backButton.addActionListener(e -> returnToMainMenu());
        return backButton;
    }

    // Делегируем методы BackgroundManager для удобства
    protected JPanel createTransparentPanel() {
        return BackgroundManager.createTransparentPanel();
    }

    protected JPanel createSemiTransparentPanel(Color color) {
        return BackgroundManager.createSemiTransparentPanel(color);
    }

    protected JTextArea createTransparentTextArea(int rows, int cols) {
        return BackgroundManager.createTransparentTextArea(rows, cols);
    }

    protected JButton createStyledButton(String text, Color color) {
        return BackgroundManager.createStyledButton(text, color);
    }
}