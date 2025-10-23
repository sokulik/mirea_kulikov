package Practice8_2.Classes;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ShapesFrame extends JFrame{
    private List<Shape> shapes;

    public ShapesFrame(){
        shapes = new ArrayList<>();
        setTitle("Случайные фигуры");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(){
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

    private void generateShapes(int count, int width, int length){
        for (int i=0; i < count; i++){
            shapes.add(ShapeFactory.createRandomShape(width, length));
        }
    }
}
