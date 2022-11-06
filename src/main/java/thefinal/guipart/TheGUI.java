package thefinal.guipart;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import thefinal.GlobalModel;
import thefinal.SceneSystem.StageObject;

public class TheGUI {
    private final class PageSelectActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int pageNum = Integer.parseInt(e.getActionCommand());
            GlobalModel.currentStage.setVisible(false);
            GlobalModel.currentStage = GlobalModel.stageList.get(pageNum);
            GlobalModel.currentStage.setVisible(true);
            // TODO:这里需要一个设置模型然后更新视图的功能
            // 部分已经绑定到全局模型上去了。还有什么东西要上去的？
        }
    }

    JFrame mainFrame;

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
        // TODO:select stage should be a single part.
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
        for (int i = 0; i < 2; ++i) {
            JButton btn = new JButton("Page" + i);
            btn.setActionCommand(Integer.toString(i));
            btn.addActionListener(new PageSelectActionListener());
            selectStage.add(btn);
        }
        // display part.

        GlobalModel.stageList.add(new StageObject());
        GlobalModel.stageList.add(new StageObject());
        GridBagConstraints ss = getStageContrast();
        mainFrame.add(GlobalModel.stageList.get(0), ss);
        mainFrame.add(GlobalModel.stageList.get(1), ss);

        // GlobalModel.stageList.get(0).setVisible(false);
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

    private GridBagConstraints getStageContrast() {
        GridBagConstraints ss = new GridBagConstraints();
        ss.gridheight = 7;
        ss.gridwidth = 8;
        ss.gridx = 1;
        ss.gridy = 1;
        return ss;
    }
}
