package thefinal.guipart;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thefinal.GlobalModel;

public class InfoGUI extends JPanel {
    JTextField posX, posY, scaleX, scaleY, rotation;
    JButton updateBtn;
    JLabel objectName;

    InfoGUI() {
        setSize(100, 500);
        setLayout(new GridBagLayout());
        objectName = new JLabel("Press enter to update shape");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        add(objectName, c);
        posX = makeNewTextArea("PosX", posX, 0, 1, new IntAreaListener());
        posY = makeNewTextArea("PosY", posY, 1, 1, new IntAreaListener());
        scaleX = makeNewTextArea("ScaleX", scaleX, 0, 2, new DoubleAreaListener());
        scaleY = makeNewTextArea("ScaleY", scaleY, 1, 2, new DoubleAreaListener());
        rotation = makeNewTextArea("Rotation", rotation, 3, 1, new DoubleAreaListener());
        updateBtn = new JButton("UpdateModel");
        // setPreferredSize(new Dimension(120, 500));
        setBorder(BorderFactory.createTitledBorder("Configure"));

        InfoGUIUpdater.setInfoGUI(this);
    }

    private JTextField makeNewTextArea(String name, JTextField textfield, int gridPosX, int gridPosY,
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
        textfield.addFocusListener(keyAdapter);
        add(textfield, c);
        return textfield;
    }

    void updateGUI() {
        System.out.println("Updating");
        if (GlobalModel.currentActor != null) {
            posX.setText(Integer.toString(GlobalModel.currentActor.transform.x));
            posY.setText(Integer.toString(GlobalModel.currentActor.transform.y));
            scaleX.setText(Double.toString(GlobalModel.currentActor.scale.x));
            scaleY.setText(Double.toString(GlobalModel.currentActor.scale.y));
            rotation.setText(Double.toString(GlobalModel.currentActor.rotation));
        } else {
            posX.setText("0");
            posY.setText("0");
            scaleX.setText("0");
            scaleY.setText("0");
            rotation.setText("0");
        }
    }

    void updateModel() {
        if (GlobalModel.currentActor != null) {
            GlobalModel.currentActor.transform.x = Integer.parseInt(posX.getText());
            GlobalModel.currentActor.transform.y = Integer.parseInt(posY.getText());
            GlobalModel.currentActor.scale.x = Double.parseDouble(scaleX.getText());
            GlobalModel.currentActor.scale.y = Double.parseDouble(scaleY.getText());
            GlobalModel.currentActor.rotation = Double.parseDouble(rotation.getText());
        }
        GlobalModel.currentStage.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        updateModel();
        super.paint(graphics);
        updateGUI();
    }

}
