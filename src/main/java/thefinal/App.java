package thefinal;

import thefinal.SceneSystem.SequenceGenerator;
import thefinal.guipart.TheGUI;

/**
 * Hello world!
 *
 */

/**
 * TODO:actor搜索功能，字体多样式修改
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
