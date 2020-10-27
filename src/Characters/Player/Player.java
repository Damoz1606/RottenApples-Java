package Characters.Player;

import Entity.Animation;
import Entity.MapObject;
import ImagesResourses.Background;
import ImagesResourses.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Player extends MapObject {

    //player stuff
    private int health, maxHealth;
    private boolean dead;
    private int score;
    //animation
    private List<BufferedImage[]> sprites;
    private int[] numFrames = {8, 10};
    //animation action
    public static final int IDLE = 0;
    public static final int WALKING = 1;
//    public static final int MOVING_LEFT = 1;
//    public static final int MOVING_RIGHT = 2;

    //ACTIONS
    private boolean left;
    private boolean right;

    public Player(TileMap tileMap) {
        super(tileMap);

        width = 44;
        height = 27;
        cWidth = 44;
        cHeight = 44;

        movSpeed = maxSpeed = 0.5;
        stopSpeed = 0.4;

        facingRight = true;

        health = maxHealth = 3;
        //loadSprites        
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/assets/Worm/worm-" + Background.getInstace().getStringMode() + ".gif"));
            //BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/assets/Worm/worm-Classic.gif"));
            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 2; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    bi[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
                }
                sprites.add(bi);
            }
        } catch (IOException ex) {
        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(100);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int life) {
        this.health = life;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void update() {

        //SET ANIMATION
        if (this.isRight() || this.isLeft()) {
            //UPDATE POSITION
            getNextPostion();
            objectMove();
            setPosition(xTemp, yTemp);
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 44;
            }
        } else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(100);
                width = 44;
            }
        }

        animation.update();
        //SET DIRECTION
        if (this.isRight()) {
            facingRight = true;
        }
        if (this.isLeft()) {
            facingRight = false;
        }
    }

    public void draw(Graphics2D g) {
        setMapPosition();
        if (facingRight) {
            g.drawImage(animation.getImage(), (int) (x - width / 2), (int) (y - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) (x - width / 2 + width), (int) (y - height / 2), -width, height, null);
        }
    }

    private void getNextPostion() {
        //MOVEMENT
        if (this.isLeft()) {
            this.setVector(dx - movSpeed, dy);
            if (dx < -maxSpeed) {
                this.setVector(-maxSpeed, dy);
            }
        } else if (this.isRight()) {
            this.setVector(dx + maxSpeed, dy);
            if (dx > maxSpeed) {
                this.setVector(maxSpeed, dy);
            }
        }
    }

    @Override
    public void calculateLimits() {

        if (xTemp < 22) {
            this.xTemp = 22;
        } else if (xTemp > 242) {
            this.xTemp = 242;
        }
    }

    @Override
    public void objectMove() {

        xDest = x + dx;
        yDest = y + dy;

        xTemp = xDest;
        yTemp = y;

        calculateLimits();
    }
}
