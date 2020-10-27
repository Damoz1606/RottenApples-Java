package GameState;

import ImagesResourses.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StartState extends GameState {

    private Background bg;
    private MainLogo mLogo;

    private int currentChoice = 0;
    private String startGame = "PRESS ENTER";
    private int startGameCon = 0;
    private boolean rotten;

    private Graphics2D graphics;

    private Font font;

    public StartState(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = Background.getInstace();
            mLogo = new MainLogo("../assets/ApplesFinal/AppleRed001.png", 100, 100);

            bg.setVector(-0.1, 0);
            mLogo.setPosition(110, 30);

            font = new Font("Century Gothic", Font.BOLD, 9);

        } catch (Exception e) {
        }

    }

    public void init() {
    }

    public void draw(Graphics2D g) {
        graphics = g;
        // draw bg
        bg.draw(g);

        // draw title
        mLogo.draw(g);

        // draw menu options
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(startGame, 135, 200);

        if (rotten) {
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("Rooten Apples " + Background.getInstace().getModeString(), 10, 10);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }

        if (k == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (k == KeyEvent.VK_C) {
            chageBackground();
            bg.draw(graphics);
        }

        if (k == KeyEvent.VK_R) {
            rotten = true;
        }
    }

    public void chageBackground() {
        if (Background.getInstace().getModeInt() == 0) {
            Background.getInstace().setBackground(1);
        } else if (Background.getInstace().getModeInt() == 1) {
            Background.getInstace().setBackground(0);
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_R) {
            rotten = false;
        }
    }

    @Override
    public void update() {

    }
}
