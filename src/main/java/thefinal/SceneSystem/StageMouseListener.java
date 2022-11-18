package thefinal.SceneSystem;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.guipart.InfoGUIUpdater;

public class StageMouseListener implements MouseInputListener {
    boolean firstPoint = true;
    ArrayList<Point> shitList = new ArrayList<>();
    int actionType = 0;
    StageObject stageObject;
    Point rootPoint;
    ActorObject previousObject = null;

    public StageMouseListener(StageObject stageObject) {
        this.stageObject = stageObject;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        StageViewport.currentStage.anchor = null;
        if (!GlobalModel.pathMode) {
            moveMethod(e);
        } else {
            shitList.add(e.getPoint());
            if (firstPoint) {
                GlobalModel.tempPath = new GeneralPath();
                GlobalModel.tempPath.moveTo(e.getX(), e.getY());
                firstPoint = false;
            } else {
                GlobalModel.tempPath.lineTo(e.getX(), e.getY());
            }
        }
    }

    private void moveMethod(MouseEvent e) {
        stageObject.getActor(e.getPoint());
        // Logic to deal with overlap problem.
        // Without the code below, if a selected shape get across another shape having a
        // higher level,then the control would be take over.
        if (StageViewport.currentActor != null && StageViewport.currentActor != previousObject
                && previousObject != null) {
            GlobalModel.setCurrentActor(previousObject);
        }
        if (GlobalModel.getCurrentActor() != null) {
            previousObject = GlobalModel.getCurrentActor();
            System.out.println("StageMouseListener::Get the actor");
            ActorObject temp = GlobalModel.getCurrentActor();
            // temp.transform.x = e.getPoint().x - 2;
            temp.transform.x = (int) (e.getPoint().x - temp.size.x * temp.scale.x / 2);
            // temp.transform.y = e.getY() - 2;
            temp.transform.y = (int) (e.getY() - temp.size.y * temp.scale.y / 2);
        } else {

            actionType = 0;
        }
        // InfoGUIUpdater.updateModel();
        stageObject.repaint();
        InfoGUIUpdater.updateGUI();
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
        // GlobalModel.currentActor = null;
        previousObject = null;
        GlobalModel.pathMode = false;

        if (GlobalModel.tempPath != null) {
            AffineTransform shiftTrans = new AffineTransform();
            shiftTrans.translate(-GlobalModel.tempPath
                    .getBounds2D().getMinX(),
                    -GlobalModel.tempPath
                            .getBounds2D()
                            .getMinY());
            StageViewport.currentStage.addActor(new ActorObject(GlobalModel.tempPath.createTransformedShape(shiftTrans),
                    new Point((int) GlobalModel.tempPath
                            .getBounds2D().getMinX(),
                            (int) GlobalModel.tempPath
                                    .getBounds2D()
                                    .getMinY()),
                    new Point((int) GlobalModel.tempPath.getBounds2D().getMinX(),
                            (int) GlobalModel.tempPath.getBounds2D().getMinY()),
                    shitList));
        }
        shitList = new ArrayList<>();
        GlobalModel.tempPath = null;
        firstPoint = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        stageObject.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
