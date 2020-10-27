package Model;

import org.bson.types.ObjectId;

public class ScoreVo {
    
    private ObjectId _id;
    private String nickName, time;
    private double score;

    public ScoreVo(ObjectId _id, String nickName, double score, String time) {
        this._id = _id;
        this.nickName = nickName;
        this.score = score;
        this.time = time;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String toString(){
        return "Nickname: " + nickName + "\nScore: " + score + "\nTime: " + time + "\n\n";
    }
    
    
    
}
