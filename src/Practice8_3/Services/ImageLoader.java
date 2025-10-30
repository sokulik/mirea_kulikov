package Practice8_3.Services;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static ImageIcon loadAndScaleImage(String imagePath, int width, int height) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            if (imageIcon.getIconWidth() == -1) {
                return null;
            }

            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isSupportedImageFormat(String fileName) {
        String lowerFileName = fileName.toLowerCase();
        return lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") ||
                lowerFileName.endsWith(".png") || lowerFileName.endsWith(".gif") ||
                lowerFileName.endsWith(".bmp");
    }
}