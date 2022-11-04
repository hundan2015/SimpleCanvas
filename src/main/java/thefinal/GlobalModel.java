package thefinal;

import java.util.ArrayList;

import thefinal.SceneSystem.ActorObject;
import thefinal.SceneSystem.StageObject;

public class GlobalModel {
    /**
     * 当前Page所选中的Actor。这个玩意应该是单例的。全局应该只有一个当前Actor。
     */
    static void initGlobalModel() {
        GlobalModel.currentActor = null;
        stageList = new ArrayList<>();
        stageList.add(new StageObject());
        currentStage = stageList.get(0);
        selectShape = 0;
    }

    static public  int selectShape;
    static public ActorObject currentActor;
    static public StageObject currentStage;

    static public ActorObject getCurrentActor() {
        return currentActor;
    }

    static public void setCurrentActor(ActorObject currentActor) {
        GlobalModel.currentActor = currentActor;
    }

    public static ArrayList<StageObject> stageList;
}
