package GameManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameTime {

    private static GameTime instance;

    private int miliseconds;
    private int seconds;
    private int minutes;

    private Timer timer;
    private ActionListener action = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            ++miliseconds;
            if (miliseconds == 100) {
                miliseconds = 0;
                ++seconds;
            }
            if (seconds == 60) {
                seconds = 0;
                ++minutes;
            }

            updateLabel();
        }
    };

    private GameTime() {
        timer = new Timer(10, action);
        timer.start();
        updateLabel();
    }

    public static GameTime getInstance() {
        if (instance == null) {
            instance = new GameTime();
        }
        return instance;
    }

    public String getTimer() {
        String sMinutes = "" + minutes, sSeconds = "" + seconds;
        if (seconds < 10) {
            sSeconds = "" + 0 + seconds;
        }

        if (minutes < 10) {
            sMinutes = "" + 0 + minutes;
        }
        return sMinutes + ":" + sSeconds;
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
        seconds = 0;
        minutes = 0;
        updateLabel();
        timer.restart();
    }

    public void updateLabel() {
        String minute, second;
        if (minutes < 10) {
            minute = "0" + minutes;
        } else {
            minute = "" + minutes;
        }

        if (seconds < 10) {
            second = "0" + seconds;
        } else {
            second = "" + seconds;
        }
    }
}
