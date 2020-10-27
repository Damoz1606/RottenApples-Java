package ImagesResourses;

import GameManager.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MainLogo {

    private BufferedImage image;
    private BufferedImage resized;

    private double x;
    private double y;
    private double dx;
    private double dy;

    public MainLogo(String s, int width, int height) {

        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            resized = resize(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public void setPosition(double x, double y) {
        this.x = (x) % GamePanel.WIDTH;
        this.y = (y) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(resized, null, (int) x, (int) y);
    }
}
