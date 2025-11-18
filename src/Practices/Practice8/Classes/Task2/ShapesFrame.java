package Practices.Practice8.Classes.Task2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapesFrame extends JFrame {
    private List<Shape> shapes; // Используем ваш Shape, а не java.awt.Shape

    public ShapesFrame() {
        shapes = new ArrayList<>();
        setTitle("Случайные фигуры");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (shapes.isEmpty()) {
                    generateShapes(20, getWidth(), getHeight());
                }
                for (Shape shape : shapes) {
                    shape.draw(g);
                }
            }
        };
        add(panel);
    }

    private void generateShapes(int count, int width, int height) { // исправлено length на height
        for (int i = 0; i < count; i++) {
            Shape shape = ShapeFactory.createRandomShape(width, height);
            if (shape != null) {
                shapes.add(shape);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapesFrame frame = new ShapesFrame();
            frame.setVisible(true);
        });
    }
}