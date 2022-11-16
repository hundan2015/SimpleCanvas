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
import thefinal.SceneSystem.StageObject;

public class EditorGUI extends JPanel {
        JButton newPageBtn;
        JButton delPageBtn;
        JButton paintPathBtn;
        JButton colorPickerBtn;
        JButton createObjectBtn;
        JButton delObjectBtn;
        JComboBox<String> theFontSelector;
        JComboBox<String> theShapeSelector;
        GridBagLayout theLayout;

        /**
         * A set of panel to do something to the current stage.
         */
        EditorGUI() {
                setBorder(BorderFactory.createTitledBorder("ToolSet"));
                theLayout = new GridBagLayout();
                QuickConstraintFactory gridBagConstraintFactory = new QuickConstraintFactory();
                setLayout(theLayout);
                // New page BTN
                newPageBtn = new JButton("New Page");
                newPageBtn.addActionListener(new NewPageActionListener());
                add(newPageBtn, gridBagConstraintFactory.getConstraints(1));

                // Delete page BTN
                delPageBtn = new JButton("Delete Page");
                delPageBtn.addActionListener(new DelPageActionListener());
                add(delPageBtn, gridBagConstraintFactory.getConstraints(1));

                // Paint path BTN
                paintPathBtn = new JButton("Paint Path");
                paintPathBtn.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                GlobalModel.pathMode = true;
                        }

                });
                add(paintPathBtn, gridBagConstraintFactory.getConstraints(1));

                // Color Picker Btn
                colorPickerBtn = new JButton("Pick Color");
                colorPickerBtn.addActionListener(new ColorPickerActionListener(this));
                add(colorPickerBtn, gridBagConstraintFactory.getConstraints(1));

                EditorGUIBtnActionListener editorGUIBtnActionListener = new EditorGUIBtnActionListener();
                // Create object BTN
                createObjectBtn = new JButton("Create Shape");
                createObjectBtn.setActionCommand("CREATEO");
                createObjectBtn.addActionListener(editorGUIBtnActionListener);
                add(createObjectBtn, gridBagConstraintFactory.getConstraints(1));
                // Delete object BTN
                delObjectBtn = new JButton("Del Shape");
                delObjectBtn.setActionCommand("DELO");
                delObjectBtn.addActionListener(editorGUIBtnActionListener);
                add(delObjectBtn, gridBagConstraintFactory.getConstraints(1));

                theShapeSelector = new JComboBox<String>();
                theShapeSelector.addItem("Circle");
                theShapeSelector.addItem("Square");
                theShapeSelector.addItem("Line");
                theShapeSelector.addItem("Text");
                theShapeSelector.addActionListener(new shapeSelectActionListener());
                add(theShapeSelector, gridBagConstraintFactory.getConstraints(1));

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

        private final class shapeSelectActionListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        int shit = theShapeSelector.getSelectedIndex();
                        GlobalModel.selectShape = shit;
                }
        }

        private final class DelPageActionListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        GlobalModel.delCurrentStageObject();
                }
        }

        private final class NewPageActionListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        GlobalModel.addStageObject(new StageObject());
                }
        }

        class ColorPickerActionListener implements ActionListener {
                EditorGUI thefuck;

                ColorPickerActionListener(EditorGUI theGui) {
                        thefuck = theGui;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                        GlobalModel.shapeColor = JColorChooser.showDialog(colorPickerBtn, "Color Picker", Color.white);
                        colorPickerBtn.setBackground(GlobalModel.shapeColor);
                        // thefuck.setBackground(GlobalModel.shapeColor);
                }
        }
}
