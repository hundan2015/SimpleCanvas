package thefinal.guipart;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thefinal.SceneSystem.ActorObject;

public class InfoGUI extends JPanel {
    JTextField posX, posY, scaleX, scaleY;
    ActorObject currentObject;

    JLabel objectName;

    InfoGUI() {
        setSize(100, 500);
        setLayout(new GridBagLayout());
        objectName = new JLabel("Empty");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        add(objectName, c);
        makeNewTextArea("PosX", posX, 0, 1, new IntAreaListener());
        makeNewTextArea("PosY", posY, 1, 1, new IntAreaListener());
        makeNewTextArea("ScaleX", scaleX, 0, 2, new DoubleAreaListener());
        makeNewTextArea("ScaleY", scaleY, 1, 2, new DoubleAreaListener());
        // setPreferredSize(new Dimension(120, 500));
        setBorder(BorderFactory.createTitledBorder("Configure"));

    }

    private void makeNewTextArea(String name, JTextField textfield, int gridPosX, int gridPosY,
            InputNumArea keyAdapter) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = gridPosX;
        c.gridy = gridPosY;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;

        textfield = new JTextField(1);
        textfield.setBorder(BorderFactory.createTitledBorder(name));
        textfield.setFont(new Font(null, Font.PLAIN, 10));
        textfield.setPreferredSize(new Dimension(100, 50));

        keyAdapter.bindTextField(textfield);
        textfield.addKeyListener((KeyAdapter) keyAdapter);
        add(textfield, c);
    }

    public void setCurrentObject(ActorObject currentObject) {
        this.currentObject = currentObject;
    }

    public ActorObject getCurrentObject() {
        return currentObject;
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
