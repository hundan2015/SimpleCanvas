package thefinal.guipart;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

public class ActorListGUI {
    private static final class ActorListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            JList<ActorObject> tempList = (JList<ActorObject>) e.getSource();
            ActorObject selected = tempList.getSelectedValue();
            if (selected != null) {
                GlobalModel.setCurrentActor(selected);
                Point tempTransform = new Point();
                tempTransform.x = (int) (selected.transform.x + selected.size.x * selected.scale.x / 2);
                tempTransform.y = (int) (selected.transform.y + selected.size.y * selected.scale.y / 2);
                StageViewport.currentStage.anchor = new ActorObject(new Point(tempTransform), new Point(10, 10),
                        new Ellipse2D.Double(tempTransform.x, tempTransform.y, 10, 10), new Color(255, 0, 0));
                tempList.clearSelection();
            }

        }
    }

    public static JFrame actorListFrame = new JFrame();
    public static JList<ActorObject> actorListDisplay;
    static JLabel shits = new JLabel("Actors");
    static JPanel rectaglePanel = new JPanel();
    static JPanel textPanel = new JPanel();
    static JPanel ellipsePanel = new JPanel();
    static JPanel linePanel = new JPanel();

    static public void initActorListGUI() {
        actorListFrame.setLayout(new BoxLayout(actorListFrame.getContentPane(), BoxLayout.Y_AXIS));
        actorListFrame.add(shits);
        update();
        actorListFrame.setVisible(true);
    }

    static Vector<ActorObject> circleList = new Vector<>();
    static Vector<ActorObject> lineList = new Vector<>();
    static Vector<ActorObject> squareList = new Vector<>();
    static Vector<ActorObject> pathList = new Vector<>();
    static Vector<ActorObject> textList = new Vector<>();

    static JList<ActorObject> circleListDisplay;
    static JList<ActorObject> lineListDisplay;
    static JList<ActorObject> squareListDisplay;
    static JList<ActorObject> pathListDisplay;
    static JList<ActorObject> textListDisplay;

    static public void update() {

        if (circleListDisplay != null) {
            actorListFrame.remove(circleListDisplay);
            actorListFrame.remove(lineListDisplay);
            actorListFrame.remove(squareListDisplay);
            actorListFrame.remove(pathListDisplay);
            actorListFrame.remove(textListDisplay);
        }
        gVector();
        // actorListDisplay = new JList<>(gVector());

        circleListDisplay = makeListAppend("Circle", circleList);
        squareListDisplay = makeListAppend("Rectangle", squareList);
        lineListDisplay = makeListAppend("Line", lineList);
        textListDisplay = makeListAppend("Text", textList);
        pathListDisplay = makeListAppend("Path", pathList);

        actorListFrame.pack();
        actorListFrame.repaint();
    }

    private static JList<ActorObject> makeListAppend(String text, Vector<ActorObject> listmodel) {
        JList<ActorObject> list;

        list = new JList<>(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.addListSelectionListener(new ActorListSelectionListener());
        list.setBorder(BorderFactory.createTitledBorder(text));
        actorListFrame.add(list);
        return list;
    }

    /**
     * Maintaince the lower data model.
     */
    static void gVector() {
        circleList.clear();
        squareList.clear();
        textList.clear();
        pathList.clear();
        lineList.clear();
        if (StageViewport.currentStage != null)
            for (ActorObject actor : StageViewport.currentStage.actorList) {
                String type = actor.getShape().getClass().toString();
                switch (type) {
                    case "class java.awt.geom.Ellipse2D$Double": {
                        circleList.add(actor);
                        break;
                    }
                    case "class java.awt.geom.Rectangle2D$Double": {
                        squareList.add(actor);
                        break;
                    }
                    case "class java.awt.geom.Line2D$Double": {
                        lineList.add(actor);
                        break;
                    }
                    case "class java.awt.geom.GeneralPath": {
                        if (actor.text == null || actor.text == "") {
                            pathList.add(actor);
                        } else {
                            textList.add(actor);
                        }
                        break;
                    }
                }
            }
    }
}
