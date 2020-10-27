package GameState;

import Audio.AudioPlayer;
import GameManager.GamePanel;
import ImagesResourses.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MusicState extends GameState {

    private Background bg;
    private AudioPlayer audio;

    private int currentChoice = 0;
    private String[] options = {"HIGH HOPES", "INFERNO", "RISE", "SHELTER", "THE PHOENIX", "THUNDERSTRUCK", "MUTE"};
    private String selector = ">";

    private Font font;

    public MusicState(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = Background.getInstace();

            bg.setVector(-0.1, 0);

            font = new Font("Century Gothic", Font.BOLD, 9);

        } catch (Exception e) {
        }
    }

    public void init() {
    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        g.setFont(new Font("Century Gothic", Font.BOLD, 13));
        g.setColor(Color.RED);
        g.drawString("SOUND SETTINGS", 100, 50);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
                g.drawString(selector + "   " + options[i], 100, 100 + i * 15);
            } else {
                g.setColor(Color.BLACK);
                g.drawString(options[i], 105, 100 + i * 15);
            }
        }

        g.setFont(new Font("Century Gothic", Font.BOLD, 9));
        g.setColor(Color.RED);
        g.drawString("PRESS BACK ESPACE", 10, GamePanel.HEIGHT - 15);
    }

    private void select() {
        if (currentChoice == 0) {
            AudioPlayer.getInstance().setMusic("/assets/Music/High-Hopes.mp3");
        }
        if (currentChoice == 1) {
            AudioPlayer.getInstance().setMusic("/assets/Music/Inferno.mp3");
        }
        if (currentChoice == 2) {
            AudioPlayer.getInstance().setMusic("/assets/Music/Rise.mp3");
        }
        if (currentChoice == 3) {
            AudioPlayer.getInstance().setMusic("/assets/Music/Shelter.mp3");
        }
        if (currentChoice == 4) {
            AudioPlayer.getInstance().setMusic("/assets/Music/The-Phoenix.mp3");
        }
        if (currentChoice == 5) {
            AudioPlayer.getInstance().setMusic("/assets/Music/Thunderstruck.mp3");
        }
        if (currentChoice == 6) {
            AudioPlayer.getInstance().stop();
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
        if (k == KeyEvent.VK_BACK_SPACE) {
            this.gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    public void keyReleased(int k) {
    }

    @Override
    public void update() {

    }

}
