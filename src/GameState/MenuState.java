package GameState;

import ImagesResourses.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;
    private MainLogo mLogo;

    private int currentChoice = 0;
    private String[] options = {"START", "HOW TO PLAY", "SCORE", "SOUND", "CREDITS", "EXIT"};
    private String selector = ">";

    private Font font;

    public MenuState(GameStateManager gsm) {

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

        // draw bg
        bg.draw(g);

        // draw title
        mLogo.draw(g);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
                g.drawString(selector + "   " + options[i], 140, 140 + i * 15);
            } else {
                g.setColor(Color.BLACK);
                g.drawString(options[i], 145, 140 + i * 15);
            }
        }
    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVELSTATE);
        }
        if (currentChoice == 1) {
            // HELP
        }
        if (currentChoice == 2) {
            this.gsm.setState(GameStateManager.SCORESTATE);
        }
        if (currentChoice == 3) {
            this.gsm.setState(GameStateManager.MUSICSTATE);
        }
        if (currentChoice == 4) {
            //CREDITS
        }
        if (currentChoice == 5) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {
    }

    @Override
    public void update() {

    }

}
