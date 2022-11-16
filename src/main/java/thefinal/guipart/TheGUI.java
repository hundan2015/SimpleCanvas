package thefinal.guipart;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import thefinal.GlobalModel;
import thefinal.SceneSystem.CanvasFileSystem;
import thefinal.SceneSystem.StageObject;

public class TheGUI {
    JFrame mainFrame;

    JPanel currentPanel;
    GridBagLayout theLayout;
    JScrollPane selectStage;

    public void InitGUI() {
        mainFrame = new JFrame();
        // TODO: Move this frame to the global model.
        // Or this should be a sigliton.
        GlobalModel.mainFrame = mainFrame;
        Container theContainer = mainFrame.getContentPane();
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveBtn = new JMenuItem("save");
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CanvasFileSystem.saveFile();
            }

        });
        JMenuItem loadBtn = new JMenuItem("load");
        loadBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CanvasFileSystem.loadFile();
            }

        });
        fileMenu.add(saveBtn);
        fileMenu.add(loadBtn);
        menuBar.add(fileMenu);
        JMenu windowMenu = new JMenu("Windows");
        JMenuItem selectItem = new JMenuItem("Actor List Menu");
        selectItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ActorListGUI.initActorListGUI();
            }

        });
        windowMenu.add(selectItem);
        menuBar.add(windowMenu);
        mainFrame.setJMenuBar(menuBar);

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

        selectStage = new PageSelectPanel();
        c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        theContainer.add(selectStage, c);

        // display part.

        // GlobalModel.stageList.add(new StageObject());
        GlobalModel.addStageObject(new StageObject());

        GridBagConstraints ss = getStageContrast();
        mainFrame.add(GlobalModel.stageList.get(0), ss);
        JMenuBar anoBar = new JMenuBar() {
            @Override
            public void paint(Graphics g) {
                // TODO Auto-generated method stub
                super.paint(g);
                shit();
            }

            public void shit() {
                add(new JMenuItem("shi"));
            }
        };
        anoBar.setVisible(true);
        mainFrame.add(anoBar);
        // GlobalModel.stageList.get(0).setVisible(false);
        // Configure panel part.
        c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 3;
        c.gridx = 9;
        c.gridy = 1;
        InfoGUI infoGUI = new InfoGUI();
        theContainer.add(infoGUI, c);
        c = new GridBagConstraints();
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 10;
        c.fill = 2;
        // theContainer.add(new SmallPort(GlobalModel.stageList.get(0)), c);
        mainFrame.pack();
        mainFrame.setVisible(true);
        // ActorListGUI.initActorListGUI();
    }

    public static GridBagConstraints getStageContrast() {
        GridBagConstraints ss = new GridBagConstraints();
        ss.gridheight = 7;
        ss.gridwidth = 8;
        ss.gridx = 1;
        ss.gridy = 1;
        return ss;
    }
}
