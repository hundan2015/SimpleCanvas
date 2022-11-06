package thefinal.guipart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import thefinal.GlobalModel;

public class EditorGUI extends JPanel {
        JButton newPageBtn;
        JButton delPageBtn;
        JButton colorPickerBtn;
        JButton createObjectBtn;
        JButton delObjectBtn;
        JComboBox<String> theFontSelector;
        JComboBox<String> theShapeSelector;
        GridBagLayout theLayout;

        EditorGUI() {
                setBorder(BorderFactory.createTitledBorder("ToolSet"));
                theLayout = new GridBagLayout();
                setLayout(theLayout);
                // New page BTN
                newPageBtn = new JButton("New Page");
                addNewComponent(newPageBtn,
                                GridBagConstraints.NORTHEAST,
                                0, 0, 1, 1);
                // Delete page BTN
                delPageBtn = new JButton("Delete Page");
                addNewComponent(delPageBtn,
                                GridBagConstraints.NORTHEAST,
                                1, 0, 1, 1);
                // Color Picker Btn
                colorPickerBtn = new JButton("Pick Color");
                EditorGUI self = this;

                colorPickerBtn.addActionListener(
                                new TempShit(self));

                addNewComponent(colorPickerBtn,
                                GridBagConstraints.NORTHEAST,
                                2, 0, 1, 1);

                EditorGUIBtnActionListener editorGUIBtnActionListener = new EditorGUIBtnActionListener();
                // Create object BTN
                createObjectBtn = new JButton("Create Shape");
                createObjectBtn.setActionCommand("CREATEO");
                createObjectBtn.addActionListener(editorGUIBtnActionListener);
                addNewComponent(createObjectBtn,
                                GridBagConstraints.NORTHEAST,
                                3, 0, 1, 1);
                // Delete object BTN
                delObjectBtn = new JButton("Del Shape");
                delObjectBtn.setActionCommand("DELO");
                delObjectBtn.addActionListener(editorGUIBtnActionListener);
                addNewComponent(delObjectBtn,
                                GridBagConstraints.NORTHEAST,
                                4, 0, 1, 1);

                theShapeSelector = new JComboBox<String>();
                theShapeSelector.addItem("Circle");
                theShapeSelector.addItem("Square");
                theShapeSelector.addItem("Line");
                theShapeSelector.addItem("Text");

                theShapeSelector.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int shit = theShapeSelector.getSelectedIndex();
                                GlobalModel.selectShape = shit;
                        }

                });
                addNewComponent(theShapeSelector,
                                GridBagConstraints.NORTHEAST,
                                6, 0, 1, 1);

                JTextField heighTextField = new JTextField();
                heighTextField.setBorder(BorderFactory.createBevelBorder(1));
                IntAreaListener listener = new IntAreaListener();
                listener.bindTextField(heighTextField);
                heighTextField.addKeyListener(listener);
                heighTextField.setPreferredSize(new Dimension(40, 30));
                addNewComponent(new JLabel("height"), GridBagConstraints.NORTHEAST,7, 0, 1, 1);
                addNewComponent(heighTextField, GridBagConstraints.NORTHEAST, 8, 0, 1, 1);

        }

        void addNewComponent(Component component, int anchor, int gridx, int gridy, int gridheight, int gridwidth) {
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.anchor = anchor;
                c.gridx = gridx;
                c.gridy = gridy;
                c.gridheight = gridheight;
                c.gridwidth = gridwidth;
                add(component, c);
        }

        class TempShit implements ActionListener {
                EditorGUI thefuck;

                TempShit(EditorGUI theGui) {
                        thefuck = theGui;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                        GlobalModel.shapeColor = JColorChooser.showDialog(colorPickerBtn, "Color Picker", Color.white);
                        colorPickerBtn.setBackground(GlobalModel.shapeColor);
                        //thefuck.setBackground(GlobalModel.shapeColor);
                }
        }
}
