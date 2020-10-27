package Characters.Apples;

import Characters.Player.Player;
import Entity.Animation;
import Entity.MapObject;
import ImagesResourses.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Apple extends MapObject {

    //animation
    protected BufferedImage[] sprites;

    public Apple(TileMap tileMap) {
        super(tileMap);

        width = 30;
        width = 31;
        cWidth = 30;
        cHeight = 30;

        movSpeed = maxSpeed = 0.01;
        stopSpeed = 0.4;
    }

    public void update() {

        getNextPostion();
        objectMove();
        setPosition(xTemp, yTemp);
        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        g.drawImage(animation.getImage(), (int) (x - width / 2), (int) (y - height / 2), null);
    }

    private void getNextPostion() {
        //MOVEMENT
        this.setVector(dx, dy + movSpeed);
        if (dx > maxSpeed) {
            this.setVector(dx, maxSpeed);
        }
    }
    
    public void setMaxSpeed(double speed){
        this.maxSpeed = speed;
    }

    @Override
    public void calculateLimits() {
    }

    @Override
    public void objectMove() {

        xDest = x + dx;
        yDest = y + dy;

        xTemp = x;
        yTemp = yDest;

        calculateLimits();
    }

    public abstract void loadSprites();

    public abstract void action(Player worm);
}
