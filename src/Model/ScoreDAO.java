package Model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ScoreDAO {

    public List getScore() {
        List<ScoreVo> auxList = new ArrayList<ScoreVo>();
        MongoCollection<Document> collection = Connection.getInstance().getConnection();
        MongoCursor<Document> myDoc = collection.find().iterator();
        Document auxDoc = null;
        while (myDoc.hasNext()) {
            auxDoc = myDoc.next();
            auxList.add(new ScoreVo(auxDoc.getObjectId("_id"), auxDoc.get("name").toString(), Double.parseDouble(auxDoc.get("score").toString()), auxDoc.get("time").toString()));
        }
        return auxList;
    }

    public void deleteScore(ObjectId _id) {
        MongoCollection<Document> collection = Connection.getInstance().getConnection();
        collection.deleteOne(eq("_id", _id));
    }

    public void putScore(Object nameToChange, Object name) {
        MongoCollection<Document> collection = Connection.getInstance().getConnection();
        collection.updateOne(eq("name", nameToChange), new Document("$set", new Document("name", name)));
    }

    public void postScore(Object name, Object score, Object time) {
        MongoCollection<Document> collection = Connection.getInstance().getConnection();
        Document doc = new Document("name", name.toString()).append("score", score.toString()).append("time", time.toString());
        collection.insertOne(doc);
    }
}
