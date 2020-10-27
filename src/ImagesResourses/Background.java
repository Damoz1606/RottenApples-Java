package ImagesResourses;

import GameManager.GamePanel;
import Main.ReziseIcons;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import javax.imageio.ImageIO;

public class Background {

    private BufferedImage image;
    private BufferedImage resized;
    private static Background instance;
    private Random random;
    private int imgSelection;
    private int setMode;
    String[] mode = {"Arcade", "Classic"};

    private double x;
    private double y;
    private double dx;
    private double dy;

    private Background() {
        random = new Random();
        imgSelection = random.nextInt(3) + 1;
        setBackground(1);
    }

    public static Background getInstace() {
        if (instance == null) {
            instance = new Background();
        }
        return instance;
    }

    public void setBackground(int mode) {
        setMode = mode;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../assets/Background/background_" + imgSelection + "_" + this.mode[setMode] + ".png"));
            resized = ReziseIcons.resize(image, 240, 320);
        } catch (Exception e) {
        }
    }

    public void setPosition(double x, double y) {
        this.x = (x) % GamePanel.WIDTH;
        this.y = (y) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public String getModeString() {
        return mode[setMode];
    }

    public int getModeInt() {
        return setMode;
    }

    public String getStringMode() {
        return this.mode[setMode];
    }

    public void draw(Graphics2D g) {
        g.drawImage(resized, null, (int) x, (int) y);
    }

}
