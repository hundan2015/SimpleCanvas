package thefinal;

import thefinal.guipart.TheGUI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        TheGUI theGUI = new TheGUI();
        Thread tickThread = new Thread(new GlobalTick());
        tickThread.start();
        theGUI.InitGUI();
        tickThread.run();
        System.out.println("Hello World!");
    }
}
