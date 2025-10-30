package Practice8_4.Classes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class AnimationPanel extends JPanel implements Runnable{
    private List<BufferedImage> frames;
    private int currentFrame = 0;
    private int delay = 100;
    private Thread animationThread;
    private boolean isRunning = false;

    public AnimationPanel() throws IOException {
        setPreferredSize(new Dimension(500, 400));
        setBackground(new Color(240, 240, 240));

        FrameLoader loader = new FrameLoader();
        frames = loader.loadFrames();
    }

    public void startAnimation() {
        if (animationThread == null || !isRunning) {
            isRunning = true;
            animationThread = new Thread(this);
            animationThread.setDaemon(true);
            animationThread.start();
            System.out.println("Анимация запущена");
        }
    }

    public void stopAnimation() {
        isRunning = false;
        if (animationThread != null) {
            animationThread.interrupt();
            animationThread = null;
            System.out.println("Анимация остановлена");
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(220, 220, 255),
                getWidth(), getHeight(), new Color(180, 180, 220)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (frames != null && !frames.isEmpty() && currentFrame < frames.size()) {
            BufferedImage currentImage = frames.get(currentFrame);
            int x = (getWidth() - currentImage.getWidth()) / 2;
            int y = (getHeight() - currentImage.getHeight()) / 2;
            g.drawImage(currentImage, x, y, this);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Анимация из 6 кадров", 20, 30);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Текущий кадр: " + getCurrentFrameNumber() + " / " + getTotalFrames(), 20, 60);
        g.drawString("Задержка: " + delay + " мс", 20, 85);
        g.drawString("Статус: " + (isRunning ? "Запущена" : "Остановлена"), 20, 110);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
    }

    @Override
    public void run() {
        while (isRunning && !Thread.currentThread().isInterrupted()) {
            currentFrame = (currentFrame + 1) % frames.size();

            SwingUtilities.invokeLater(() -> repaint());

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

