package GameState;

import GameManager.GamePanel;
import GameManager.SingletonGameData;
import ImagesResourses.*;
import Model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreState extends GameState {

    private Background bg;
    private ScoreDAO dao;
    private List<ScoreVo> list;

    private Graphics2D graphics;

    private Font font;

    public ScoreState(GameStateManager gsm) {

        this.gsm = gsm;

        dao = new ScoreDAO();

        try {

            bg = Background.getInstace();

            bg.setVector(-0.1, 0);

            font = new Font("Century Gothic", Font.BOLD, 9);

        } catch (Exception e) {
        }
        init();
    }

    public void init() {
        list = dao.getScore();
        Collections.sort(list, new SortByScore());
    }

    public void draw(Graphics2D g) {
        // draw bg
        bg.draw(g);
        MainLogo score;
        for (int i = 0; i < list.size() && i < 5; i++) {

            if (list.get(i).getNickName().equals(SingletonGameData.getInstance().getNickName()) && list.get(i).getTime().equals(SingletonGameData.getInstance().getTimer()) && list.get(i).getScore() == SingletonGameData.getInstance().getScore()) {
                score = new MainLogo("/assets/TileSet/PlayerScoreBar.png", 320, 50);
            } else {
                score = new MainLogo("/assets/TileSet/ScoreBar.png", 320, 50);
            }

            score.setPosition(0, 30.5 * (1 + i));
            score.draw(g);

            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
            if (list.get(i).getNickName().equals(SingletonGameData.getInstance().getNickName()) && list.get(i).getTime().equals(SingletonGameData.getInstance().getTimer()) && list.get(i).getScore() == SingletonGameData.getInstance().getScore()) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString(list.get(i).getNickName(), 30, 60 + 30 * (i));
            g.drawString(list.get(i).getTime(), 150, 60 + 30 * (i));
            g.drawString(String.format("%.0f", list.get(i).getScore()), 250, 60 + 30 * (i));
        }
        g.setFont(new Font("Century Gothic", Font.BOLD, 9));
        g.setColor(Color.RED);
        g.drawString("PRESS ENTER", GamePanel.WIDTH / 2 - "PRESS ENTER".length()*2, 230);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            SingletonGameData.getInstance().setScore(0);
            SingletonGameData.getInstance().setNickName("");
            SingletonGameData.getInstance().setTimer("");
            this.gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void keyReleased(int k) {
    }

    public class SortByScore implements Comparator<ScoreVo> {

        @Override
        public int compare(ScoreVo a, ScoreVo b) {

            return (int) (b.getScore() - a.getScore());
        }
    }
}
