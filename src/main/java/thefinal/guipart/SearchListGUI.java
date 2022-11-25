package thefinal.guipart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

public class SearchListGUI {
    public static JFrame searchListFrame = new JFrame();
    public static JTextField theField = new JTextField();
    public static JButton subButton = new JButton("Submit");
    public static JList<ActorObject> actorDisplay = new JList<>();

    static public void initSearchListGUI() {
        theField.setBorder(BorderFactory.createTitledBorder("Search"));
        searchListFrame.setLayout(new BoxLayout(searchListFrame.getContentPane(), BoxLayout.Y_AXIS));
        searchListFrame.add(theField);
        searchListFrame.add(subButton);
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchListGUI.update();
            }
        });
        actorDisplay.addListSelectionListener(new ActorListSelectionListener());
        searchListFrame.add(actorDisplay);
    }

    static public void openGUI() {
        update();
        searchListFrame.setVisible(true);
    }

    static public void update() {
        searchListFrame.remove(actorDisplay);
        String name = theField.getText();
        Vector<ActorObject> shit = new Vector<>();
        if (StageViewport.currentStage != null) {
            for (ActorObject actorObject : StageViewport.currentStage.actorList) {
                if (actorObject.name.contains(name)) {
                    shit.add(actorObject);
                }
            }
            actorDisplay = new JList<>(shit);
            actorDisplay.addListSelectionListener(new ActorListSelectionListener());
            searchListFrame.add(actorDisplay);
        }
        searchListFrame.pack();
    }
}
