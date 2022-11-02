package thefinal.guipart;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import thefinal.SceneSystem.StageObject;
import thefinal.pagepart.PageObject;

public class TheGUI {
    JFrame mainFrame;
    ArrayList<StageObject> pageList;

    JPanel currentPanel;
    GridBagLayout theLayout;
    JPanel selectStage;

    public void InitGUI() {
        mainFrame = new JFrame();
        Container theContainer = mainFrame.getContentPane();
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theLayout = new GridBagLayout();
        theContainer.setLayout(theLayout);

        GridBagConstraints c = new GridBagConstraints();
        // Editor part.
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 12;
        c.gridx = 0;
        c.gridy = 0;
        EditorGUI theEditor = new EditorGUI();
        theContainer.add(theEditor, c);
        // shit Panel part.
        selectStage = new JPanel();
        selectStage.setBorder(BorderFactory.createTitledBorder("Pages"));
        c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        theContainer.add(selectStage, c);
        selectStage.setLayout(new BoxLayout(selectStage, BoxLayout.Y_AXIS));
        for (int i = 0; i < 3; ++i) {
            JButton btn = new JButton("Page" + i);
            selectStage.add(btn);
        }
        // display part.
        pageList = new ArrayList<>();
        pageList.add(new StageObject());
        GridBagConstraints ss = new GridBagConstraints();
        ss.gridheight = 7;
        ss.gridwidth = 8;
        ss.gridx = 1;
        ss.gridy = 1;
        mainFrame.add(pageList.get(0), ss);
        // Configure panel part.
        c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 3;
        c.gridx = 9;
        c.gridy = 1;
        InfoGUI infoGUI = new InfoGUI();
        theContainer.add(infoGUI, c);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
