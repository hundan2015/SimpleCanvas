package thefinal.SceneSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import thefinal.GlobalModel;

public class StageObject extends JPanel {
    ArrayList<ActorObject> actorList;

    public StageObject() {
        actorList = new ArrayList<>();
        setBackground(Color.white);
        setPreferredSize(new Dimension(200, 200));
        StageMouseListener listener = new StageMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);

        // test part
        addActor(new ActorObject(new Rectangle(10, 10), new Point(0, 0), new Point(10, 10)));
        // GlobalTick.registerComponent(this);
    }

    public void addActor(ActorObject actor) {
        actorList.add(actor);
    }

    public void removeActor(Point mousePositon) {
        getActor(mousePositon);
        if (GlobalModel.currentActor != null) {
            actorList.remove(GlobalModel.currentActor);
            GlobalModel.currentActor = null;
        }
    }

    public void removeCurrentActor() {
        if (GlobalModel.currentActor != null) {
            actorList.remove(GlobalModel.currentActor);
            GlobalModel.currentActor = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        setBackground(Color.white);
        Graphics2D newG = (Graphics2D) g;
        newG.clearRect(0, 0, getWidth(), getHeight());
        AffineTransform tempTransform = newG.getTransform();
        for (ActorObject actorObject : actorList) {
            newG.transform(actorObject.getActorTransform());
            newG.draw(actorObject.getShape());
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
