package thefinal;

import thefinal.SceneSystem.ActorObject;

public class GlobalModel {
    /**
     * 当前Page所选中的Actor。这个玩意应该是单例的。全局应该只有一个当前Actor。
     */
    static public ActorObject currentActor;

    static public ActorObject getCurrentActor() {
        return currentActor;
    }

    static public void setCurrentActor(ActorObject currentActor) {
        GlobalModel.currentActor = currentActor;
    }
}
