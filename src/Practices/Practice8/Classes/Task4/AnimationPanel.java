package Practices.Practice8.Classes.Task4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class AnimationPanel extends JPanel implements Runnable {
    private List<BufferedImage> frames;
    private int currentFrame = 0;
    private int delay = 100;
    private Thread animationThread;
    private boolean isRunning = false;
    private int fps = 0;
    private long lastFpsTime = 0;
    private int frameCount = 0;

    public AnimationPanel() throws IOException {
        setPreferredSize(new Dimension(800, 600)); // Увеличил размер по умолчанию
        setBackground(new Color(240, 240, 240));

        FrameLoader loader = new FrameLoader();
        frames = loader.loadFrames();

        if (frames.isEmpty()) {
            throw new IOException("Не удалось загрузить ни одного кадра анимации");
        }
    }

    public void startAnimation() {
        if (!isRunning) {
            isRunning = true;
            animationThread = new Thread(this);
            animationThread.setDaemon(true);
            animationThread.start();
            System.out.println("Анимация запущена. Кадров: " + frames.size());
        }
    }

    public void stopAnimation() {
        isRunning = false;
        if (animationThread != null) {
            animationThread.interrupt();
            animationThread = null;
        }
        System.out.println("Анимация остановлена");
    }

    public void setDelay(int delay) {
        this.delay = Math.max(10, Math.min(2000, delay));
        System.out.println("Задержка установлена: " + delay + " мс");
    }

    public int getDelay() {
        return delay;
    }

    public int getCurrentFrameNumber() {
        return currentFrame + 1;
    }

    public int getTotalFrames() {
        return frames != null ? frames.size() : 0;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getFps() {
        return fps;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Градиентный фон
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(220, 220, 255),
                getWidth(), getHeight(), new Color(180, 180, 220)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Рисуем текущий кадр анимации с масштабированием
        if (frames != null && !frames.isEmpty() && currentFrame < frames.size()) {
            BufferedImage currentImage = frames.get(currentFrame);
            if (currentImage != null) {
                // Масштабируем изображение чтобы оно полностью помещалось в панель
                Image scaledImage = scaleImageToFit(currentImage, getWidth(), getHeight());

                // Центрируем изображение
                int x = (getWidth() - scaledImage.getWidth(null)) / 2;
                int y = (getHeight() - scaledImage.getHeight(null)) / 2;

                // Тень для изображения
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillRect(x + 5, y + 5, scaledImage.getWidth(null), scaledImage.getHeight(null));

                // Само изображение
                g2d.drawImage(scaledImage, x, y, this);

                // Рамка вокруг изображения
                g2d.setColor(Color.DARK_GRAY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(x, y, scaledImage.getWidth(null), scaledImage.getHeight(null));
            }
        }

        // Информационная панель
        drawInfoPanel(g2d);

        // Рамка вокруг всей панели
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(5, 5, getWidth() - 10, getHeight() - 10);
    }

    /**
     * Масштабирует изображение чтобы оно полностью помещалось в указанные размеры
     * с сохранением пропорций
     */
    private Image scaleImageToFit(BufferedImage image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();

        // Вычисляем коэффициенты масштабирования
        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;

        // Используем меньший коэффициент чтобы изображение полностью помещалось
        double scale = Math.min(widthRatio, heightRatio);

        // Вычисляем новые размеры
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        // Масштабируем изображение
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    private void drawInfoPanel(Graphics2D g2d) {
        // Полупрозрачный фон для информации
        g2d.setColor(new Color(255, 255, 255, 220));
        g2d.fillRoundRect(15, 15, 280, 130, 10, 10);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Анимация из " + getTotalFrames() + " кадров", 25, 35);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Текущий кадр: " + getCurrentFrameNumber() + " / " + getTotalFrames(), 25, 60);
        g2d.drawString("Задержка: " + delay + " мс", 25, 80);
        g2d.drawString("FPS: " + fps, 25, 100);

        // Статус с цветной индикацией
        String status = isRunning ? "▶ Запущена" : "⏹ Остановлена";
        g2d.setColor(isRunning ? new Color(0, 150, 0) : Color.RED);
        g2d.drawString("Статус: " + status, 25, 120);

        // Индикатор
        g2d.setColor(isRunning ? Color.GREEN : Color.RED);
        g2d.fillOval(150, 110, 10, 10);
    }

    @Override
    public void run() {
        long lastFrameTime = System.currentTimeMillis();
        lastFpsTime = lastFrameTime;

        while (isRunning && !Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - lastFrameTime;

            // Обновляем FPS каждую секунду
            frameCount++;
            if (currentTime - lastFpsTime >= 1000) {
                fps = frameCount;
                frameCount = 0;
                lastFpsTime = currentTime;
            }

            // Переходим к следующему кадру если прошло достаточно времени
            if (elapsed >= delay) {
                currentFrame = (currentFrame + 1) % frames.size();
                lastFrameTime = currentTime;

                // Обновляем интерфейс в EDT
                SwingUtilities.invokeLater(() -> repaint());
            }

            // Небольшая пауза чтобы не нагружать CPU
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        isRunning = false;
        System.out.println("Анимация завершена");
    }
}