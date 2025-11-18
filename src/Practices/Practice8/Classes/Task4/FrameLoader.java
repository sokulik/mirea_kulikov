package Practices.Practice8.Classes.Task4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class FrameLoader {
    private List<BufferedImage> frames;

    public FrameLoader() {
        frames = new ArrayList<>();
    }

    public List<BufferedImage> loadFrames() throws IOException {
        System.out.println("Загрузка анимационных кадров...");
        frames.clear();

        try {
            // Загружаем кадры по абсолютному пути
            BufferedImage frame1 = loadImage("Anim1.png");
            BufferedImage frame2 = loadImage("Anim2.png");
            BufferedImage frame3 = loadImage("Anim3.png");
            BufferedImage frame4 = loadImage("Anim4.png");
            BufferedImage frame5 = loadImage("Anim5.png");
            BufferedImage frame6 = loadImage("Anim6.png");

            if (frame1 == null || frame2 == null || frame3 == null ||
                    frame4 == null || frame5 == null || frame6 == null) {
                throw new IOException("Один или несколько кадров не загружены");
            }

            frames.add(frame1);
            frames.add(frame2);
            frames.add(frame3);
            frames.add(frame4);
            frames.add(frame5);
            frames.add(frame6);

            System.out.println("Успешно загружено кадров: " + frames.size());

        } catch (IOException e) {
            System.err.println("Ошибка загрузки кадров: " + e.getMessage());
            throw e;
        }

        return frames;
    }

    private BufferedImage loadImage(String filename) throws IOException {
        // Основной путь к изображениям
        String basePath = "src/Practices/Practice8/Classes/images/";

        // Пробуем разные варианты путей
        String[] possiblePaths = {
                basePath + filename,  // основной путь
                "src/Practices/Practice8/Classes/Task4/images/" + filename,
                "Practices/Practice8/Classes/images/" + filename,
                "images/" + filename,
                filename
        };

        System.out.println("Пытаемся загрузить: " + filename);

        for (String path : possiblePaths) {
            try {
                File file = new File(path);
                System.out.println("Проверяем путь: " + file.getAbsolutePath());

                if (file.exists()) {
                    System.out.println("Файл найден: " + file.getAbsolutePath());
                    BufferedImage image = ImageIO.read(file);
                    if (image != null) {
                        System.out.println("Успешно загружено: " + filename + " (" + image.getWidth() + "x" + image.getHeight() + ")");
                        return image;
                    }
                } else {
                    System.out.println("Файл не существует: " + path);
                }
            } catch (Exception e) {
                System.out.println("Ошибка при загрузке из " + path + ": " + e.getMessage());
            }
        }

        // Пробуем загрузить из ресурсов (на случай если файлы в classpath)
        try {
            java.net.URL resource = getClass().getResource("/images/" + filename);
            if (resource != null) {
                System.out.println("Загружаем из ресурсов: " + resource);
                return ImageIO.read(resource);
            }
        } catch (Exception e) {
            System.out.println("Не удалось загрузить из ресурсов: " + e.getMessage());
        }

        throw new IOException("Не удалось загрузить изображение: " + filename + " ни по одному из путей");
    }

    public int getFrameCount() {
        return frames.size();
    }

    public BufferedImage getFrame(int index) {
        if (index >= 0 && index < frames.size()) {
            return frames.get(index);
        }
        return null;
    }
}