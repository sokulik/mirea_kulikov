package Practice8_3.Components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageDisplay extends JPanel {
    private JLabel imageLabel;
    private JLabel infoLabel;

    public ImageDisplay() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setupComponents();
    }

    private void setupComponents() {
        imageLabel = new JLabel("", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        imageLabel.setVerticalTextPosition(SwingConstants.CENTER);
        imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoLabel = new JLabel("", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        add(imageLabel, BorderLayout.CENTER);
        add(infoLabel, BorderLayout.SOUTH);
    }

    public void loadImage(String imagePath) {
        if (!isValidImagePath(imagePath)) {
            return;
        }

        File imageFile = new File(imagePath);
        if (!validateImageFile(imageFile)) {
            return;
        }

        displayImage(imageFile);
    }

    private boolean isValidImagePath(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            showNoImageMessage("Путь к изображению не указан!");
            return false;
        }
        return true;
    }

    private boolean validateImageFile(File imageFile) {
        if (!imageFile.exists()) {
            showNoImageMessage("Файл не найден: " + imageFile.getPath());
            return false;
        }

        if (!imageFile.isFile()) {
            showNoImageMessage("Указанный путь не является файлом: " + imageFile.getPath());
            return false;
        }

        String fileName = imageFile.getName().toLowerCase();
        if (!isSupportedFormat(fileName)) {
            showNoImageMessage("Неподдерживаемый формат изображения: " + fileName);
            return false;
        }

        return true;
    }

    private boolean isSupportedFormat(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                fileName.endsWith(".png") || fileName.endsWith(".gif") ||
                fileName.endsWith(".bmp");
    }

    private void displayImage(File imageFile) {
        try {
            ImageIcon imageIcon = new ImageIcon(imageFile.getPath());

            if (imageIcon.getIconWidth() == -1) {
                showNoImageMessage("Не удалось загрузить изображение: " + imageFile.getPath());
                return;
            }

            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            imageLabel.setIcon(scaledIcon);
            imageLabel.setText("");

            String fileInfo = String.format("Файл: %s | Размер: %d KB | Размеры: %dx%d",
                    imageFile.getName(),
                    imageFile.length() / 1024,
                    imageIcon.getIconWidth(),
                    imageIcon.getIconHeight());
            infoLabel.setText(fileInfo);

        } catch (Exception e) {
            showNoImageMessage("Ошибка при загрузке изображения: " + e.getMessage());
        }
    }

    private void showNoImageMessage(String message) {
        imageLabel.setIcon(null);

        String text = "Изображение не загружено\n\n" +
                message + "\n\n" +
                "Использование: java Main путь_к_изображению\n" +
                "Поддерживаемые форматы: JPG, PNG, GIF, BMP";

        imageLabel.setText(text);
        infoLabel.setText("");
    }
}