package GameManager;

public class SingletonGameData {

    private static SingletonGameData instance;
    
    private String nickName;
    private String timer;
    private double score;

    private SingletonGameData() {
    }

    public static SingletonGameData getInstance() {
        if (instance == null) {
            instance = new SingletonGameData();
        }
        
        return instance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
