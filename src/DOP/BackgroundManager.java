package DOP;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BackgroundManager {
    private static BufferedImage globalBackground;
    private static boolean backgroundLoaded = false;
    private static String backgroundPath;

    // Инициализация фона при загрузке класса
    static {
        loadGlobalBackground();
    }

    public static void setBackgroundPath(String path) {
        backgroundPath = path;
        loadGlobalBackground();
    }

    private static void loadGlobalBackground() {
        try {
            // Сначала пробуем путь из командной строки
            if (backgroundPath != null && !backgroundPath.isEmpty()) {
                try {
                    globalBackground = ImageIO.read(new File(backgroundPath));
                    backgroundLoaded = true;
                    System.out.println("Фон загружен из командной строки: " + backgroundPath);
                    return;
                } catch (Exception e) {
                    System.out.println("Не удалось загрузить фон из командной строки: " + backgroundPath);
                }
            }

            // Затем пробуем стандартные пути
            String[] possiblePaths = {
                    "background.jpg",
                    "background.png",
                    "src/resources/background.jpg",
                    "resources/background.jpg",
                    "images/background.jpg",
                    "/background.jpg"
            };

            for (String path : possiblePaths) {
                try {
                    globalBackground = ImageIO.read(new File(path));
                    backgroundLoaded = true;
                    System.out.println("Фон загружен: " + path);
                    break;
                } catch (Exception e) {
                    continue;
                }
            }

            // Если не нашли в файлах, пробуем из ресурсов
            if (!backgroundLoaded) {
                try {
                    java.io.InputStream is = BackgroundManager.class.getResourceAsStream("/background.jpg");
                    if (is != null) {
                        globalBackground = ImageIO.read(is);
                        backgroundLoaded = true;
                        System.out.println("Фон загружен из ресурсов");
                    }
                } catch (Exception e) {
                    // Игнорируем ошибку
                }
            }

        } catch (Exception e) {
            System.out.println("Не удалось загрузить фоновое изображение: " + e.getMessage());
            backgroundLoaded = false;
        }
    }

    public static BufferedImage getGlobalBackground() {
        return globalBackground;
    }

    public static boolean isBackgroundLoaded() {
        return backgroundLoaded;
    }

    // Метод для применения фона к любому JFrame
    public static void applyBackgroundToFrame(JFrame frame) {
        if (!backgroundLoaded) return;

        // Создаем кастомную панель контента с фоном
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (globalBackground != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(globalBackground, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);
    }

    // Метод для создания полупрозрачных компонентов
    public static JPanel createTransparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    public static JPanel createSemiTransparentPanel(Color color) {
        JPanel panel = new JPanel();
        if (color != null) {
            panel.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 180));
        } else {
            panel.setBackground(new Color(255, 255, 255, 180));
        }
        panel.setOpaque(true);
        return panel;
    }

    public static JTextArea createTransparentTextArea(int rows, int cols) {
        JTextArea textArea = new JTextArea(rows, cols);
        textArea.setBackground(new Color(255, 255, 255, 200));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 100)));
        return textArea;
    }

    public static JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setBackground(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 220));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 220));
            }
        });

        return button;
    }
}