package thefinal.SceneSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.guipart.ActorListGUI;

public class StageObject extends JPanel {
    public ArrayList<ActorObject> actorList;

    public ActorObject anchor;

    public StageObject() {
        actorList = new ArrayList<>();
        setBackground(Color.white);
        setPreferredSize(new Dimension(200, 200));
        StageMouseListener listener = new StageMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);

        // test part
        // addActor(new ActorObject(new Rectangle(10, 10), new Point(0, 0), new
        // Point(10, 10)));
        // GlobalTick.registerComponent(this);
        StageObject shit = this;
        Runnable ano = () -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                shit.repaint();
            }
        };
        new Thread(ano).start();
    }

    public void addActor(ActorObject actor) {
        // actorList.add(actor);
        actorList.add(0, actor);
        // update list part
        ActorListGUI.update();
    }

    public void removeActor(Point mousePositon) {
        getActor(mousePositon);
        if (StageViewport.currentActor != null) {
            actorList.remove(StageViewport.currentActor);
            StageViewport.currentActor = null;
        }
        // update list part
        ActorListGUI.update();
    }

    public void removeCurrentActor() {
        if (StageViewport.currentActor != null) {
            actorList.remove(StageViewport.currentActor);
            StageViewport.currentActor = null;
        }
        // update list part
        ActorListGUI.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.white);
        Graphics2D newG = (Graphics2D) g;
        newG.clearRect(0, 0, getWidth(), getHeight());
        AffineTransform tempTransform = newG.getTransform();
        if (GlobalModel.tempPath != null) {
            newG.draw(GlobalModel.tempPath);
        }
        if (anchor != null) {
            newG.setColor(anchor.color);
            //newG.setTransform(anchor.getActorTransform());
            newG.fill(anchor.getShape());
            newG.draw(anchor.getShape());
            newG.setTransform(tempTransform);
        }
        for (ActorObject actorObject : actorList) {
            newG.setColor(actorObject.color);
            newG.transform(actorObject.getActorTransform());
            newG.draw(actorObject.getShape());
            if (actorObject.isFilled)
                newG.fill(actorObject.getShape());
            newG.setTransform(tempTransform);
        }
    }

    void getActor(Point mousePosition) {
        for (ActorObject actorObject : actorList) {
            if (actorObject.isContain(mousePosition)) {
                // currentActor = actorObject;
                GlobalModel.setCurrentActor(actorObject);
                return;
            }
        }
        // currentActor = null;
        GlobalModel.setCurrentActor(null);
    }
}
