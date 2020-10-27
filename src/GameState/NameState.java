package GameState;

import GameManager.GamePanel;
import GameManager.SingletonGameData;
import ImagesResourses.Background;
import ImagesResourses.MainLogo;
import Model.ScoreDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class NameState extends GameState {

    private Background bg;
    private MainLogo writeBox;
    private String nickName = "";
    private boolean length = false;

    public NameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {

        try {
            bg = Background.getInstace();
        } catch (Exception e) {
        }

        writeBox = new MainLogo("/assets/TileSet/WriteLine.png", 320, 81);
        writeBox.setPosition(0, GamePanel.HEIGHT / 2 - 45);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        writeBox.draw(g);
        g.setFont(new Font("Century Gothic", Font.BOLD, 9));
        g.setColor(Color.BLACK);
        g.drawString(nickName, GamePanel.WIDTH / 2 - 3 * nickName.length(), GamePanel.HEIGHT / 2);
    }

    @Override
    public void keyPressed(int k) {
        if (nickName.length() < 5) {
            switch (k) {
                case KeyEvent.VK_A:
                    nickName += "A";
                    break;
                case KeyEvent.VK_B:
                    nickName += "B";
                    break;
                case KeyEvent.VK_C:
                    nickName += "C";
                    break;
                case KeyEvent.VK_D:
                    nickName += "D";
                    break;
                case KeyEvent.VK_E:
                    nickName += "E";
                    break;
                case KeyEvent.VK_F:
                    nickName += "F";
                    break;
                case KeyEvent.VK_G:
                    nickName += "G";
                    break;
                case KeyEvent.VK_H:
                    nickName += "H";
                    break;
                case KeyEvent.VK_I:
                    nickName += "I";
                    break;
                case KeyEvent.VK_J:
                    nickName += "J";
                    break;
                case KeyEvent.VK_K:
                    nickName += "K";
                    break;
                case KeyEvent.VK_L:
                    nickName += "L";
                    break;
                case KeyEvent.VK_M:
                    nickName += "M";
                    break;
                case KeyEvent.VK_N:
                    nickName += "N";
                    break;
                case KeyEvent.VK_O:
                    nickName += "O";
                    break;
                case KeyEvent.VK_P:
                    nickName += "P";
                    break;
                case KeyEvent.VK_Q:
                    nickName += "Q";
                    break;
                case KeyEvent.VK_R:
                    nickName += "R";
                    break;
                case KeyEvent.VK_S:
                    nickName += "S";
                    break;
                case KeyEvent.VK_T:
                    nickName += "T";
                    break;
                case KeyEvent.VK_U:
                    nickName += "U";
                    break;
                case KeyEvent.VK_V:
                    nickName += "V";
                    break;
                case KeyEvent.VK_W:
                    nickName += "W";
                    break;
                case KeyEvent.VK_X:
                    nickName += "X";
                    break;
                case KeyEvent.VK_Y:
                    nickName += "Y";
                    break;
                case KeyEvent.VK_Z:
                    nickName += "Z";
                    break;
                case KeyEvent.VK_AT:
                    nickName += "@";
                    break;
                case KeyEvent.VK_1:
                    nickName += "1";
                    break;
                case KeyEvent.VK_2:
                    nickName += "2";
                    break;
                case KeyEvent.VK_3:
                    nickName += "3";
                    break;
                case KeyEvent.VK_4:
                    nickName += "4";
                    break;
                case KeyEvent.VK_5:
                    nickName += "5";
                    break;
                case KeyEvent.VK_6:
                    nickName += "6";
                    break;
                case KeyEvent.VK_7:
                    nickName += "7";
                    break;
                case KeyEvent.VK_8:
                    nickName += "8";
                    break;
                case KeyEvent.VK_9:
                    nickName += "9";
                    break;
            }
        }

        if (k == KeyEvent.VK_BACK_SPACE) {
            nickName = erase(nickName);
        }

        if (k == KeyEvent.VK_ENTER) {
            SingletonGameData.getInstance().setNickName(nickName);
            (new ScoreDAO()).postScore(nickName, SingletonGameData.getInstance().getScore(), SingletonGameData.getInstance().getTimer());
            this.gsm.setState(GameStateManager.SCORESTATE);
        }

    }

    @Override
    public void keyReleased(int k) {
    }

    private String erase(String string) {
        char[] aux = string.toCharArray();
        String aux2 = "";
        for (int i = 0; i < aux.length - 1; i++) {
            aux2 += aux[i];
        }
        return aux2;
    }
}
