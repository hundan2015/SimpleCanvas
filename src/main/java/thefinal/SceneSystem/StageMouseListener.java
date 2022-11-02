package thefinal.SceneSystem;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class StageMouseListener implements MouseInputListener {

    int actionType = 0;
    StageObject stageObject;
    Point rootPoint;
    
    public StageMouseListener(StageObject stageObject) {
        this.stageObject = stageObject;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        stageObject.getActor(e.getPoint());
        if (stageObject.currentActor != null) {
            System.out.println("StageMouseListener::Get the actor");
            ActorObject temp = stageObject.getCurrentActor();
            temp.transform.x = e.getPoint().x - 2;
            temp.transform.y = e.getY() - 2;
        } else {
            
            actionType = 0;
        }
        stageObject.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        stageObject.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
