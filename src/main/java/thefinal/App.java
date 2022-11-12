package thefinal;

import thefinal.SceneSystem.SequenceGenerator;
import thefinal.guipart.TheGUI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        GlobalModel.initGlobalModel();
        TheGUI theGUI = new TheGUI();
        // Thread tickThread = new Thread(new GlobalTick());
        // tickThread.start();
        theGUI.InitGUI();
        // tickThread.run();
        System.out.println("Hello World!");
        System.out.println(SequenceGenerator.getFileJSON().toJSONString());
    }
}
