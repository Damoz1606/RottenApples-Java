package GameState;

import Audio.AudioPlayer;
import Characters.Apples.*;
import Characters.Player.Player;
import GameManager.GamePanel;
import GameManager.GameTime;
import GameManager.SingletonGameData;
import ImagesResourses.Background;
import ImagesResourses.MainLogo;
import ImagesResourses.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RAState extends GameState {

    private TileMap tileMap;
    private Background bg;
    private Player worm;
    private List<Apple> enemies;
    private Apple apple;
    private MainLogo scoreBar;
    private AudioPlayer bgAudio;

    private int appleCon = 0;

    public RAState(GameStateManager gsm) {
        this.gsm = gsm;
        GameTime.getInstance().resetTimer();
        init();
    }

    @Override
    public void init() {

        tileMap = new TileMap(30);
        tileMap.setPosition(0, 0);

        try {
            bg = Background.getInstace();
        } catch (Exception e) {
        }

        scoreBar = new MainLogo("/assets/TileSet/PointsBar.png", 56, 240);
        scoreBar.setPosition(264, 0);

        worm = new Player(tileMap);
        worm.setPosition(242 / 2, GamePanel.HEIGHT - 15);

        enemies = new ArrayList<Apple>();
    }

    @Override
    public void update() {
        worm.update();
        addApple();
        for (Apple aux : enemies) {
            aux.update();
            if (checkCollision(aux, worm)) {
                aux.action(worm);
                enemies.remove(aux);
                break;
            }
        }

        if (worm.isDead()) {
            GameTime.getInstance().stopTimer();
            SingletonGameData.getInstance().setTimer(GameTime.getInstance().getTimer());
            SingletonGameData.getInstance().setScore(worm.getScore());
            this.gsm.setState(GameStateManager.SETNICKNAME);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        scoreBar.draw(g);

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
        g.setColor(Color.WHITE);

        g.drawString("" + worm.getHealth() + "/" + worm.getMaxHealth(), 270, 30);
        g.drawString("" + worm.getScore(), 270, 30 * 2);
        g.drawString(GameTime.getInstance().getTimer(), 270, 30 * 3);
        //g.drawString(list.get(i).getScore(), 250, 60 + 30 * (i));
        for (Apple aux : enemies) {
            aux.draw(g);
        }
        worm.draw(g);

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            worm.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            worm.setRight(true);
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            worm.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            worm.setRight(false);
        }
    }

    public void addApple() {
        Apple apple;
        if ((new Random().nextInt(100) + 1) % spawnRate() == 0) {
            switch (appleCon) {
                case 3:
                    apple = new ApplePoisoned(tileMap);
                    apple.setPosition(randomPositionX(), -22);
                    apple.setMaxSpeed(maxSpeed());
                    enemies.add(apple);
                    appleCon++;
                    break;
                case 5:
                    apple = new ApplePoisoned(tileMap);
                    apple.setPosition(randomPositionX(), -22);
                    apple.setMaxSpeed(maxSpeed());
                    enemies.add(apple);
                    appleCon++;
                    break;
                case 7:
                    apple = new AppleLife(tileMap);
                    apple.setPosition(randomPositionX(), -22);
                    apple.setMaxSpeed(maxSpeed());
                    enemies.add(apple);
                    appleCon = 0;
                    break;
                default:
                    apple = new AppleRed(tileMap);
                    apple.setPosition(randomPositionX(), -22);
                    apple.setMaxSpeed(maxSpeed());
                    enemies.add(apple);
                    appleCon++;
                    break;
            }
        }
    }

    private int randomPositionX() {
        int auxPos = 22, originalPos = 44;
        switch ((new Random()).nextInt(6)) {
            case 1:
                auxPos += originalPos;
                break;
            case 2:
                auxPos += originalPos * 2;
                break;
            case 3:
                auxPos += originalPos * 3;
                break;
            case 4:
                auxPos += originalPos * 4;
                break;
            case 5:
                auxPos += originalPos * 5;
                break;
        }
        return auxPos;
    }

    private int spawnRate() {
        int flag = 100;
        if (worm.getScore() >= 500) {
            flag = 10;
        } else if (worm.getScore() >= 1500) {
            flag = 2;
        }
        return flag;
    }

    private double maxSpeed() {
        double flag = 0.01;
        if (worm.getScore() >= 500) {
            flag = 0.25;
        } else if (worm.getScore() >= 1500) {
            flag = 0.5;
        }
        return flag;
    }

    public boolean checkCollision(Apple aux, Player auxP) {
        boolean flag = false;
        if (aux.getyTemp() - aux.getHeight() / 2 < auxP.getyTemp() && auxP.getyTemp() < aux.getyTemp() + aux.getHeight() / 2) {
            if (aux.getxTemp() - aux.getWidth() / 2 < auxP.getxTemp() && auxP.getxTemp() < aux.getxTemp() + aux.getWidth() / 2) {
                flag = true;
            }
        }
        return flag;
    }
}
