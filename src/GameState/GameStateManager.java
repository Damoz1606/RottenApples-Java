package GameState;

import Audio.AudioPlayer;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    private final int NUMGAMESSTATES = 6;
    public static final int STARTSTATE = 0;
    public static final int MENUSTATE = 1;
    public static final int LEVELSTATE = 2;
    public static final int SETNICKNAME = 3;
    public static final int SCORESTATE = 4;
    public static final int MUSICSTATE = 5;

    public GameStateManager() {

        gameStates = new GameState[NUMGAMESSTATES];
        AudioPlayer bgAudio = AudioPlayer.getInstance();
        bgAudio.setMusic(getPath());
        currentState = STARTSTATE;
        loadState(currentState);
    }

    public void loadState(int state) {
        switch (state) {
            case STARTSTATE:
                gameStates[state] = new StartState(this);
                break;
            case MENUSTATE:
                gameStates[state] = new MenuState(this);
                break;
            case LEVELSTATE:
                gameStates[state] = new RAState(this);
                break;
            case SETNICKNAME:
                gameStates[state] = new NameState(this);
                break;
            case SCORESTATE:
                gameStates[state] = new ScoreState(this);
                break;
            case MUSICSTATE:
                gameStates[state] = new MusicState(this);
                break;
        }
    }

    public void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
        gameStates[currentState].init();
    }

    public void update() {
        try {
            gameStates[currentState].update();
        } catch (Exception ex) {
        }

    }

    public void draw(Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception ex) {
        }

    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }

    private String getPath() {
        String path = "/assets/Music/";
        int random = (new Random()).nextInt(6) + 1;
        switch (random) {
            case 1:
                path += "Inferno.mp3";
                break;
            case 2:
                path += "High-Hopes.mp3";
                break;
            case 3:
                path += "Rise.mp3";
                break;
            case 4:
                path += "Shelter.mp3";
                break;
            case 5:
                path += "The-Phoenix.mp3";
                break;
            case 6:
                path += "Thunderstruck.mp3";
                break;
        }
        return path;
    }

}
