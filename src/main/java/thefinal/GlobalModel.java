package thefinal;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

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
    // TODO: StageList should be a new component.
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
            /**
             * HACK: A Fucking bug of swing.
             * When the scroll panel updated and it only get one smallport,
             * if we need to add new stage, the scroll panel it self would get blank.
             * But when the window get longer, the small port would display agian.
             * So we use the main frame, and update its size, the pack it.
             * Maybe it is a feature of mutithread system of swing,
             * IT ALSO A PIECE OF FUCKING SHIT!!!
             */
            mainFrame.setSize(mainFrame.getWidth(), mainFrame.getHeight() + 1);
            mainFrame.pack();
        }
        if (selectScenePanel != null) {
            selectScenePanel.updateList();
            selectScenePanel.repaint();
        }

    }

    public static void delCurrentStageObject() {
        if (stageList.size() <= 1) {
            System.out.println("Del Stage failed.Must have one stage!");
            return;
        }
        currentStage.setVisible(false);
        mainFrame.remove(currentStage);
        stageList.remove(currentStage);
        selectScenePanel.updateList();
        /*
         * selectScenePanel.getHorizontalScrollBar().repaint();
         * JScrollBar bar = selectScenePanel.getHorizontalScrollBar();
         * bar.setValue(bar.getMaximum());
         */

        currentStage = stageList.get(0);
        currentStage.setVisible(true);
        // mainFrame.repaint();

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
