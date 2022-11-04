package thefinal;

import java.util.ArrayList;

import javax.swing.JComponent;

import thefinal.guipart.TickAble;

public class GlobalTick implements Runnable {
    static ArrayList<JComponent> tickList;
    static ArrayList<TickAble> anoList;

    GlobalTick() {
        tickList = new ArrayList<>();
        anoList = new ArrayList<>();
    }

    public static void registerComponent(JComponent component) {
        tickList.add(component);
    }

    public static void registerComponent(TickAble tickAble) {
        anoList.add(tickAble);
    }

    static void tick() {
        while (true) {
            for (JComponent jComponent : tickList) {
                jComponent.repaint();
            }
            for (TickAble tickAble : anoList) {
                tickAble.tick();
            }
        }
    }

    @Override
    public void run() {
        tick();
    }
}
