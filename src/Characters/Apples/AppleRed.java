package Characters.Apples;

import Characters.Player.Player;
import Entity.Animation;
import ImagesResourses.Background;
import ImagesResourses.TileMap;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AppleRed extends Apple {

    public AppleRed(TileMap tileMap) {
        super(tileMap);
        loadSprites();
    }

    @Override
    public void loadSprites() {
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/assets/ApplesFinal/AppleRed-"+ Background.getInstace().getStringMode() +".gif"));
            sprites = new BufferedImage[4];
            for (int i = 0; i < 4; i++) {
                sprites[i] = spriteSheet.getSubimage(i * width, 0, width, height);
            }
        } catch (IOException ex) {
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100);
    }

    @Override
    public void action(Player worm) {
        worm.setScore(worm.getScore()+100);
    }
}
