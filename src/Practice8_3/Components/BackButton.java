package Practice8_3.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    public BackButton(JFrame parentFrame) {
        setText("← Назад к меню");
        setFont(new Font("Arial", Font.PLAIN, 12));
        setBackground(new Color(200, 200, 200));
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parentFrame != null) {
                    parentFrame.dispose();
                }
            }
        });
    }
}