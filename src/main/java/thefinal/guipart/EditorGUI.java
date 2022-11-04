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
                newPageBtn = new JButton("New Page");
                addNewComponent(newPageBtn,
                                GridBagConstraints.NORTHEAST,
                                0, 0, 1, 1);

                delPageBtn = new JButton("Delete Page");
                addNewComponent(delPageBtn,
                                GridBagConstraints.NORTHEAST,
                                1, 0, 1, 1);
                colorPickerBtn = new JButton("Pick Color");
                EditorGUI self = this;

                colorPickerBtn.addActionListener(
                                new TempShit(self));

                addNewComponent(colorPickerBtn,
                                GridBagConstraints.NORTHEAST,
                                2, 0, 1, 1);
                createObjectBtn = new JButton("Create Shape");
                addNewComponent(createObjectBtn,
                                GridBagConstraints.NORTHEAST,
                                3, 0, 1, 1);
                delObjectBtn = new JButton("Del Shape");
                delObjectBtn.setActionCommand("DEL");
                delObjectBtn.addActionListener(new EditorGUIBtnActionListener());

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
                theShapeSelector.addItem("Colom");
                theShapeSelector.addItem("Rectangle");
                theShapeSelector.addItem("Triangle");
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
