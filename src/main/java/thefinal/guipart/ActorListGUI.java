package thefinal.guipart;

import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListModel;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

public class ActorListGUI {
    public static JFrame actorListFrame = new JFrame();
    public static JList<String> actorListDisplay;
    static JLabel shits = new JLabel("No actor.");
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

    static public void update() {
        if (actorListDisplay != null)
            actorListFrame.remove(actorListDisplay);
        Vector<String> shit = gVector();
        actorListDisplay = new JList<>(shit);
        if (shit.size() == 0) {
            shits.setText("null");
        } else {
            shits.setText("Fellows");
        }
        actorListDisplay.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<String> tempList = (JList<String>) e.getSource();
                int shit = tempList.getSelectedIndex();
                GlobalModel.setCurrentActor(StageViewport.currentStage.actorList.get(shit));
            }

        });
        actorListFrame.add(actorListDisplay);
        actorListFrame.pack();
        actorListFrame.repaint();
    }

    static Vector<String> gVector()

    {
        Vector<String> actorObjects = new Vector<>();
        for (ActorObject actor : StageViewport.currentStage.actorList) {
            actorObjects.add(actor.name);
        }
        return actorObjects;
    }
}
