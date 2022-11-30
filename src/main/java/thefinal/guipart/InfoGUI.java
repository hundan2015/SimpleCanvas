package thefinal.guipart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thefinal.GlobalModel;
import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;
import thefinal.guipart.NumberInput.DoubleAreaListener;
import thefinal.guipart.NumberInput.InputNumArea;
import thefinal.guipart.NumberInput.IntAreaListener;

public class InfoGUI extends JPanel {
    JTextField posX, posY, scaleX, scaleY, rotation;
    JButton updateBtn;
    JLabel objectName;
    JCheckBox filledBox;
    Color shapeColor;
    JTextField nameField;

    InfoGUI() {
        setSize(100, 500);
        setLayout(new GridBagLayout());
        QuickConstraintFactory qFactory = new QuickConstraintFactory();
        objectName = new JLabel("Press enter to update shape");
        GridBagConstraints c = qFactory.getConstraints(2);
        add(objectName, c);
        qFactory.nextRow();
        nameField = new JTextField();
        nameField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                InfoGUIUpdater.updateModel();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        add(nameField, qFactory.getConstraints(2));
        qFactory.nextRow();
        posX = makeNewTextArea("PosX", posX, new IntAreaListener(), qFactory.getConstraints(1));
        posY = makeNewTextArea("PosY", posY, new IntAreaListener(), qFactory.getConstraints(1));
        qFactory.nextRow();
        scaleX = makeNewTextArea("ScaleX", scaleX, new DoubleAreaListener(), qFactory.getConstraints(1));
        scaleY = makeNewTextArea("ScaleY", scaleY, new DoubleAreaListener(), qFactory.getConstraints(1));
        qFactory.nextRow();
        rotation = makeNewTextArea("Rotate", rotation, new DoubleAreaListener(), qFactory.getConstraints(1));

        filledBox = new JCheckBox();
        filledBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateModel();
            }

        });
        GridBagConstraints c1 = qFactory.getConstraints(1);

        filledBox.setBorderPainted(true);
        filledBox.setPreferredSize(new Dimension(100, 50));
        filledBox.setBorder(BorderFactory.createTitledBorder("Is Filled?"));
        add(filledBox, c1);
        // setPreferredSize(new Dimension(120, 500));
        setBorder(BorderFactory.createTitledBorder("Configure"));

        JButton colorButton = new JButton("set Color");
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (StageViewport.currentActor != null) {
                    ActorObject shit = StageViewport.currentActor;
                    shit.color = JColorChooser.showDialog(colorButton, TOOL_TIP_TEXT_KEY,
                            shapeColor);
                }

            }

        });
        qFactory.nextRow();
        c1 = qFactory.getConstraints(1);
        add(colorButton, c1);
        InfoGUIUpdater.setInfoGUI(this);
    }

    private JTextField makeNewTextArea(String name, JTextField textfield,
            InputNumArea keyAdapter, GridBagConstraints c) {

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
        if (StageViewport.currentActor != null) {
            posX.setText(Integer.toString(StageViewport.currentActor.transform.x));
            posY.setText(Integer.toString(StageViewport.currentActor.transform.y));
            scaleX.setText(Double.toString(StageViewport.currentActor.scale.x));
            scaleY.setText(Double.toString(StageViewport.currentActor.scale.y));
            rotation.setText(Double.toString(StageViewport.currentActor.rotation));
            filledBox.setSelected(StageViewport.currentActor.isFilled);
            // shapeColor = GlobalModel.currentActor.color;
            nameField.setText(StageViewport.currentActor.name);
        } else {
            posX.setText("0");
            posY.setText("0");
            scaleX.setText("0");
            scaleY.setText("0");
            rotation.setText("0");
            filledBox.setSelected(false);
            shapeColor = Color.white;
            nameField.setText("NO ACTOR");
        }
    }

    void updateModel() {
        if (StageViewport.currentActor != null) {
            StageViewport.currentActor.transform.x = Integer.parseInt(posX.getText());
            StageViewport.currentActor.transform.y = Integer.parseInt(posY.getText());
            StageViewport.currentActor.scale.x = Double.parseDouble(scaleX.getText());
            StageViewport.currentActor.scale.y = Double.parseDouble(scaleY.getText());
            StageViewport.currentActor.rotation = Double.parseDouble(rotation.getText());
            StageViewport.currentActor.isFilled = filledBox.isSelected();
            // GlobalModel.currentActor.color = shapeColor;
            StageViewport.currentActor.name = nameField.getText();
        }
        if (StageViewport.currentStage != null)
            StageViewport.currentStage.repaint();
        ActorListGUI.update();
    }

    @Override
    public void paint(Graphics graphics) {
        updateModel();
        super.paint(graphics);
        updateGUI();
        GlobalModel.mainFrame.pack();
    }

}
