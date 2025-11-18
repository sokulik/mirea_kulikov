package Practices.Practice8.Classes.Task3.Services;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static ImageIcon loadAndScaleImage(String imagePath, int maxWidth, int maxHeight) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            if (imageIcon.getIconWidth() == -1) {
                return null;
            }

            Image image = imageIcon.getImage();
            Image scaledImage = scaleImage(image, maxWidth, maxHeight);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            return null;
        }
    }

    private static Image scaleImage(Image image, int maxWidth, int maxHeight) {
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

    public static boolean isSupportedImageFormat(String fileName) {
        if (fileName == null) return false;
        String lowerFileName = fileName.toLowerCase();
        return lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") ||
                lowerFileName.endsWith(".png") || lowerFileName.endsWith(".gif") ||
                lowerFileName.endsWith(".bmp") || lowerFileName.endsWith(".webp");
    }
}