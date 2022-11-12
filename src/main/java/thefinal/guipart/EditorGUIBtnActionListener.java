package thefinal.guipart;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

class EditorGUIBtnActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String shit = e.getActionCommand();
        switch (shit) {
            case "CREATEO": {
                System.out.println("Create Object");
                switch (GlobalModel.selectShape) {
                    case 0:
                        StageViewport.currentStage
                                .addActor(
                                        new ActorObject(new Ellipse2D.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                        break;
                    case 1:
                        StageViewport.currentStage
                                .addActor(
                                        new ActorObject(new Rectangle.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                        break;
                    case 2:
                        StageViewport.currentStage
                                .addActor(
                                        new ActorObject(new Line2D.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                        break;
                    case 3:
                        TextGUI tempTextGUI = new TextGUI();
                        tempTextGUI.setVisible(true);
                        break;
                }
                StageViewport.currentStage.repaint();
                break;
            }
            case "DELO": {
                System.out.println("Delete Object");
                StageViewport.currentStage.removeCurrentActor();
                StageViewport.currentStage.repaint();
                break;
            }
        }
    }
}
