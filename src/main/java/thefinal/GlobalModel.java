package thefinal;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;

import thefinal.SceneSystem.ActorObject;
import thefinal.SceneSystem.StageObject;
import thefinal.guipart.PageSelectPanel;
import thefinal.guipart.TheGUI;

public class GlobalModel {
    static public int selectShape;
    static public JFrame mainFrame;
    static public ActorObject currentActor;
    static public StageObject currentStage;
    static double shapeHeight;
    static public Color shapeColor;
    public static ArrayList<StageObject> stageList;
    // A total failure design!!!!
    public static PageSelectPanel selectScenePanel;

    public static void addStageObject(StageObject stageObject) {
        // ArrayList<ViewConstract> tList = ViewMaintainer.preToUpdateView(stageObject);
        stageList.add(stageObject);
        stageObject.setVisible(false);
        if (mainFrame != null) {
            mainFrame.add(stageObject, TheGUI.getStageContrast());
            mainFrame.repaint();
        }
        if (selectScenePanel != null)
            selectScenePanel.updateList();

    }

    public static void delCurrentStageObject() {
        if (stageList.size() == 0) {
            System.out.println("Del Stage failed.Must have one stage!");
        }
        currentStage.setVisible(false);
        mainFrame.remove(currentStage);
        stageList.remove(currentStage);
        selectScenePanel.updateList();
        currentStage = stageList.get(0);
        currentStage.setVisible(true);
    }

    static public ActorObject getCurrentActor() {
        return currentActor;
    }

    static public void setCurrentActor(ActorObject currentActor) {
        GlobalModel.currentActor = currentActor;
    }

    /**
     * 当前Page所选中的Actor。这个玩意应该是单例的。全局应该只有一个当前Actor。
     */
    static void initGlobalModel() {
        GlobalModel.currentActor = null;
        stageList = new ArrayList<>();
        addStageObject(new StageObject());
        currentStage = stageList.get(0);
        selectShape = 0;
    }
}
