package thefinal.guipart;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

final class ActorListSelectionListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<ActorObject> tempList = (JList<ActorObject>) e.getSource();
        ActorObject selected = tempList.getSelectedValue();
        if (selected != null) {
            GlobalModel.setCurrentActor(selected);
            Point tempTransform = new Point();
            // tempTransform.x = (int) (selected.transform.x + selected.size.x *
            // selected.scale.x / 2);
            // tempTransform.y = (int) (selected.transform.y + selected.size.y *
            // selected.scale.y / 2);
            StageViewport.currentStage.anchor = new ActorObject(new Point(tempTransform), new Point(10, 10),
                    new Ellipse2D.Double(selected.transform.x, selected.transform.y, 10, 10), new Color(255, 0, 0));
            tempList.clearSelection();
        }

    }
}