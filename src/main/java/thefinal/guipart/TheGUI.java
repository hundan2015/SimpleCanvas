package thefinal.guipart;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import thefinal.pagepart.PageObject;

public class TheGUI {
    JFrame mainFrame;
    ArrayList<PageObject> pageList;
    
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
        pageList.add(new PageObject(theContainer));
        /* currentPanel = pageList.get(0).thePage;

        currentPanel.setPreferredSize(new Dimension(800,600));
        currentPanel.setBackground(Color.white);
        currentPanel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println(e.getX());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
            
        });
        c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 11;
        c.gridx = 1;
        c.gridy = 1;
        theContainer.add(currentPanel, c); */

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
