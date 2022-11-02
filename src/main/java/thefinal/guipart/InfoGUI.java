package thefinal.guipart;

import java.awt.Dimension;
import java.awt.Font;
import java.util.IllformedLocaleException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thefinal.SceneSystem.ActorObject;

public class InfoGUI extends JPanel {
    JTextField posX, posY, scaleX, scaleY;
    ActorObject currentObject;

    InfoGUI() {
        setSize(100, 500);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        posX = new JTextField(1);
        posX.setBorder(BorderFactory.createTitledBorder("PosX"));
        posX.setFont(new Font(null, Font.PLAIN, 10));
        posX.setPreferredSize(new Dimension(100, 50));
        add(posX);
        posY = new JTextField(1);
        posY.setBorder(BorderFactory.createTitledBorder("PosY"));
        posY.setFont(new Font(null, Font.PLAIN, 10));
        posY.setPreferredSize(new Dimension(100, 50));
        add(posY);
        scaleX = new JTextField(1);
        scaleX.setBorder(BorderFactory.createTitledBorder("ScaleX"));
        scaleX.setFont(new Font(null, Font.PLAIN, 10));
        scaleX.setPreferredSize(new Dimension(100, 50));
        add(scaleX);
        scaleY = new JTextField(1);
        scaleY.setBorder(BorderFactory.createTitledBorder("ScaleY"));
        scaleY.setFont(new Font(null, Font.PLAIN, 10));
        scaleY.setPreferredSize(new Dimension(100, 50));
        add(scaleY);
        setPreferredSize(new Dimension(120, 500));
        setBorder(BorderFactory.createTitledBorder("Configure"));

    }

    public void setCurrentObject(ActorObject currentObject) {
        this.currentObject = currentObject;
    }

    void updateGUI() {
        if (currentObject != null) {
            posX.setText(Integer.toString(currentObject.transform.x));
            posY.setText(Integer.toString(currentObject.transform.y));
            scaleX.setText(Integer.toString(currentObject.scale.x));
            scaleY.setText(Integer.toString(currentObject.scale.y));
        }
    }

    void updateModel() {
        if (currentObject != null) {
            currentObject.transform.x = Integer.parseInt(posX.getText());
            currentObject.transform.y = Integer.parseInt(posY.getText());
            currentObject.scale.x = Integer.parseInt(scaleX.getText());
            currentObject.scale.y = Integer.parseInt(scaleY.getText());
        }
    }
}
