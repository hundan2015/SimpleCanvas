package thefinal.SceneSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import thefinal.GlobalModel;

public class SequenceGenerator {
    static String getActorString(ActorObject actorObject) {
        String res = "actorBegin\n";
        String shapeType = actorObject.getShape().getClass().toString() + "\n";
        String shapeText = actorObject.transform.x + " " + actorObject.transform.y + " " + actorObject.scale.x + " "
                + actorObject.scale.y + " " + actorObject.rotation + " " + actorObject.textShift + "\n";

        res += shapeType + shapeText;
        if (actorObject.text != null) {
            res += actorObject.text + "\n";
        }
        res += "actorEnd\n";
        return res;
    }

    // FIXME:Actor的序列化有很大的问题！
    static JSONObject getActorJson(ActorObject actorObject) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shape", actorObject.getShape().getClass().toString());
        jsonObject.put("posX", actorObject.transform.x);
        jsonObject.put("posY", actorObject.transform.y);
        jsonObject.put("scaleX", actorObject.scale.x);
        jsonObject.put("scaleY", actorObject.scale.y);
        jsonObject.put("rotation", actorObject.rotation);
        jsonObject.put("textShift", actorObject.textShift);
        jsonObject.put("text", actorObject.text != null ? actorObject.text : "");
        jsonObject.put("font", actorObject.font);
        jsonObject.put("isFilled", actorObject.isFilled);
        jsonObject.put("R", actorObject.color.getRed());
        jsonObject.put("G", actorObject.color.getGreen());
        jsonObject.put("B", actorObject.color.getBlue());
        return jsonObject;
    }

    static ActorObject getActorObjectFromJSON(JSONObject object) {
        String shapeType = object.getString("shape");
        int posX = object.getInteger("posX");
        int posY = object.getInteger("posY");
        double scaleX = object.getDoubleValue("scaleX");
        double scaleY = object.getDoubleValue("scaleY");
        double rotation = object.getDoubleValue("rotation");
        double textShift = object.getDoubleValue("textShift");
        String font = object.getString("font");
        String text = object.getString("text");
        int R = object.getIntValue("R");
        int G = object.getIntValue("G");
        int B = object.getIntValue("B");
        boolean isFilled = object.getBooleanValue("isFilled");
        Shape shape = getShapeType(shapeType, text);
        Rectangle2D shit = shape.getBounds2D();
        ActorObject res = new ActorObject(shape, new Point(posX, posY), new Point((int) shit.getWidth(), (int) shit
                .getHeight()),
                new Point.Double(scaleX, scaleY), rotation, textShift, text, font, new Color(R, G, B), isFilled);
        return res;
    }

    private static Shape getShapeType(String shapeType, String text) {
        Shape shape = new Rectangle();
        switch (shapeType) {
            case "class java.awt.geom.Ellipse2D$Double": {
                shape = new Ellipse2D.Double(0, 0, 10, 10);
                break;
            }
            case "class java.awt.geom.Rectangle2D$Double": {
                shape = new Rectangle.Double(0, 0, 10, 10);
                break;
            }
            case "class java.awt.geom.Line2D$Double": {
                shape = new Line2D.Double(0, 0, 10, 10);
                break;
            }
            case "class java.awt.geom.GeneralPath": {
                Font f = new Font("Microsoft Yahei", Font.BOLD, 30);
                GlyphVector v = f.createGlyphVector(new JPanel().getFontMetrics(f).getFontRenderContext(), text);
                shape = v.getOutline();
                break;
            }
            default:
                shape = new Rectangle();
        }
        return shape;
    }

    public static JSONObject getStageObjectJSON(StageObject stageObject) {
        JSONArray actorJsonList = new JSONArray();
        for (ActorObject actorObject : stageObject.actorList) {
            actorJsonList.add(getActorJson(actorObject));
        }
        JSONObject stageJsonObject = new JSONObject();
        stageJsonObject.put("actorList", actorJsonList);
        return stageJsonObject;
    }

    public static StageObject getStageObjectFromJSON(JSONObject stageJSON) {
        StageObject res = new StageObject();
        ArrayList<ActorObject> actorList = new ArrayList<>();
        JSONArray jsonList = stageJSON.getJSONArray("actorList");
        for (Object object : jsonList) {
            actorList.add(getActorObjectFromJSON((JSONObject) object));
        }
        res.actorList = actorList;
        return res;
    }

    public static JSONObject getFileJSON() {
        ArrayList<StageObject> stageList = GlobalModel.stageList;
        JSONArray stageJSONList = new JSONArray();
        for (StageObject stageObject : stageList) {
            stageJSONList.add(getStageObjectJSON(stageObject));
        }
        JSONObject res = new JSONObject();
        res.put("stageList", stageJSONList);
        return res;
    }

    public static ArrayList<StageObject> getStageList(String jsonString) {
        ArrayList<StageObject> res = new ArrayList<>();
        JSONObject fileJson = JSONObject.parseObject(jsonString);
        JSONArray stageJsons = fileJson.getJSONArray("stageList");
        for (Object object : stageJsons) {
            JSONObject stageJson = (JSONObject) object;
            res.add(getStageObjectFromJSON(stageJson));
        }
        return res;
    }
}
// Maybe need the json

// default use 10,10,10,10 to discribe a shape.