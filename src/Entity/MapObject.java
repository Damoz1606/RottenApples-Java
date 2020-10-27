package Entity;

import GameManager.GamePanel;
import ImagesResourses.TileMap;
import java.awt.Rectangle;

public abstract class MapObject {

    //Tile Stuff
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;
    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    //dimensions
    protected int width;
    protected int height;
    //collision box
    protected int cWidth;
    protected int cHeight;
    //collision
    protected int currRow;
    protected int currCol;
    protected double xDest;
    protected double yDest;
    protected double xTemp;
    protected double yTemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;
    //Animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;
    //Movement
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    //Movement Attributes
    protected double movSpeed;
    protected double maxSpeed;
    protected double stopSpeed;

    public MapObject(TileMap tileMap) {
        this.tileMap = tileMap;
        tileSize = tileMap.getTileSize();
    }

    public boolean intersects(MapObject o) {
        Rectangle r1 = new Rectangle((int) x - cWidth, (int) y - cHeight, cWidth, cHeight);
        Rectangle r2 = new Rectangle();
        return r1.intersects(r2);
    }

    public Rectangle getRectangle() {
        return (new Rectangle((int) x - cWidth, (int) y - cHeight, cWidth, cHeight));
    }

    public abstract void objectMove();

    public abstract void calculateLimits();

    public void setLeft(boolean flag) {
        left = flag;
    }

    public void setRight(boolean flag) {
        right = flag;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        xmap = tileMap.getx();
        ymap = tileMap.gety();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getcWidth() {
        return cWidth;
    }

    public int getcHeight() {
        return cHeight;
    }

    public double getxTemp() {
        return xTemp;
    }

    public double getyTemp() {
        return yTemp;
    }

    public boolean notOnScreen() {
        return (x + xmap + width) < 0 || (x + xmap - width > GamePanel.WIDTH) || (y + ymap + height < 0) || (y + ymap - height > 0);
    }
}
