package Practices.Practice8.Classes.Task3.Components;

import Practices.Practice8.Task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    public BackButton(Task3 parentFrame) {
        setText("← Назад к меню");
        setFont(new Font("Arial", Font.BOLD, 12));
        setBackground(new Color(108, 117, 125));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 90, 100), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Эффект при наведении
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(130, 140, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(108, 117, 125));
            }
        });

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parentFrame != null) {
                    // Вызываем метод возврата в главное меню
                    parentFrame.returnToMainMenu();
                }
            }
        });
    }
}