package Practice8_4.Classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class FrameLoader {
    private List<BufferedImage> frames;

    public FrameLoader(){
        frames =new ArrayList<>();
    }

    public List<BufferedImage> loadFrames() throws IOException{
            System.out.println("Загрузка анимационных кадров...");

            // Загружаем три кадра из resources
            BufferedImage frame1 = ImageIO.read(getClass().getResource("/resources/Anim1.png"));
            BufferedImage frame2 = ImageIO.read(getClass().getResource("/resources/Anim2.png"));
            BufferedImage frame3 = ImageIO.read(getClass().getResource("/resources/Anim3.png"));
            BufferedImage frame4 = ImageIO.read(getClass().getResource("/resources/Anim4.png"));
            BufferedImage frame5 = ImageIO.read(getClass().getResource("/resources/Anim5.png"));
            BufferedImage frame6 = ImageIO.read(getClass().getResource("/resources/Anim6.png"));

            frames.add(frame1);
            frames.add(frame2);
            frames.add(frame3);
            frames.add(frame4);
            frames.add(frame5);
            frames.add(frame6);

            System.out.println("Успешно загружено кадров: " + frames.size());


        return frames;
    }

    public int getFrameCount(){
        return frames.size();
    }

    public BufferedImage getFrame(int i){
        if (i >= 0 && i < frames.size()){
            return frames.get(i);
        }
    return null;
    }
}
