package thefinal.guipart;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import thefinal.GlobalModel;
import thefinal.SceneSystem.SmallPort;

public class PageSelectPanel extends JScrollPane {
    // need to bind button to the stage.
    JPanel jPanel;

    public PageSelectPanel() {
        setBorder(BorderFactory.createTitledBorder("Pages"));
        setPreferredSize(new Dimension(150, 200));
        setLayout(new ScrollPaneLayout());
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GlobalModel.selectScenePanel = this;
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        setViewportView(jPanel);
    }

    public void updateList() {

        int sceneSize = GlobalModel.stageList.size();
        jPanel.repaint();
        jPanel.removeAll();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < sceneSize; ++i) {
            SmallPort smallPort = new SmallPort(GlobalModel.stageList.get(i));
            smallPort.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    GlobalModel.currentStage.setVisible(false);
                    GlobalModel.currentStage = smallPort.getTarget();
                    GlobalModel.currentStage.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
            jPanel.add(smallPort);
            jPanel.repaint();
        }
        JScrollBar bar = getHorizontalScrollBar();
        bar.setValue(bar.getMaximum());
        repaint();
    }
}