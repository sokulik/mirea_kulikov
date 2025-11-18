package Practices.Practice8.Classes.Task3.Components;

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

            // Получаем размеры панели для масштабирования
            int panelWidth = getWidth() - 40;
            int panelHeight = getHeight() - 80;

            if (panelWidth <= 0) panelWidth = 600;
            if (panelHeight <= 0) panelHeight = 400;

            // Масштабируем изображение с сохранением пропорций
            Image image = imageIcon.getImage();
            Image scaledImage = scaleImage(image, panelWidth, panelHeight);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            imageLabel.setIcon(scaledIcon);
            imageLabel.setText("");

            String fileInfo = String.format("Файл: %s | Размер: %d KB | Размеры: %dx%d | Масштабировано: %dx%d",
                    imageFile.getName(),
                    imageFile.length() / 1024,
                    imageIcon.getIconWidth(),
                    imageIcon.getIconHeight(),
                    scaledIcon.getIconWidth(),
                    scaledIcon.getIconHeight());
            infoLabel.setText(fileInfo);

        } catch (Exception e) {
            showNoImageMessage("Ошибка при загрузке изображения: " + e.getMessage());
        }
    }

    private Image scaleImage(Image image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        // Вычисляем новые размеры с сохранением пропорций
        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double ratio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    public void showNoImageMessage(String message) {
        imageLabel.setIcon(null);

        String text = "<html><div style='text-align: center;'>" +
                "<b>Изображение не загружено</b><br><br>" +
                message + "<br><br>" +
                "Использование: java Main путь_к_изображению<br>" +
                "Поддерживаемые форматы: JPG, PNG, GIF, BMP</div></html>";

        imageLabel.setText(text);
        infoLabel.setText("");
    }

    @Override
    public void doLayout() {
        super.doLayout();
        // При изменении размера перезагружаем изображение для нового масштабирования
        if (imageLabel.getIcon() != null) {
            String currentText = infoLabel.getText();
            if (currentText != null && currentText.contains("Файл:")) {
                // Здесь можно добавить логику перезагрузки изображения при изменении размера
                // Пока оставим как есть для простоты
            }
        }
    }
}