package thefinal.guipart;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;

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

                // TODO: Modify a new FontSelector.
                theFontSelector = new JComboBox<String>();
                theFontSelector.addItem("Yahei");
                theFontSelector.addItem("Song");
                addNewComponent(theFontSelector,
                                GridBagConstraints.NORTHEAST,
                                5, 0, 1, 1);

                theShapeSelector = new JComboBox<String>();
                theShapeSelector.addItem("Circle");
                theShapeSelector.addItem("Square");
                theShapeSelector.addItem("Line");
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
                        Color res = JColorChooser.showDialog(colorPickerBtn, "Color Picker", Color.white);
                        colorPickerBtn.setBackground(res);
                        thefuck.setBackground(res);
                }
        }
}
