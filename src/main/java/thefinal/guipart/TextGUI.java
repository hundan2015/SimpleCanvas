package thefinal.guipart;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import thefinal.StageViewport;
import thefinal.SceneSystem.ActorObject;

public class TextGUI extends JFrame {
    private final class TextGUIActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "C": {
                    dispose();
                    break;
                }
                case "S": {

                    Font f = new Font("Microsoft Yahei", Font.BOLD, 30);
                    String text = textField.getText();
                    GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), text);
                    Shape shape = v.getOutline();
                    Rectangle2D shit = shape.getBounds2D();

                    StageViewport.currentStage.addActor(new ActorObject(shape, new Point(10, 10),
                            new Point((int) shit.getWidth(), (int) shit.getHeight()), shit.getHeight(), text,
                            "Microsoft Yahei"));
                    dispose();
                    break;
                }
            }
        }
    }

    JTextField textField;
    JButton canncelBtn;
    JButton submitBtn;
    JComboBox<String> fontSelecter;

    /**
     * TextGUI is a additional GUI to create new TEXT Actor object.
     */
    TextGUI() {
        textField = new JTextField();
        GridBagLayout gridBagLayout = new GridBagLayout();
        QuickConstraintFactory qFac = new QuickConstraintFactory();
        setLayout(gridBagLayout);
        fontSelecter = new JComboBox<String>();
        GridBagConstraints c = qFac.getConstraints(2);
        qFac.nextRow();
        fontSelecter.addItem("Song");
        fontSelecter.addItem("Ya Hei");
        add(fontSelecter);
        textField.setBorder(BorderFactory.createTitledBorder("What to say:"));
        textField.setPreferredSize(new Dimension(100, 50));
        c = qFac.getConstraints(2);
        add(textField, c);

        canncelBtn = new JButton("Cancel");
        canncelBtn.setActionCommand("C");
        TextGUIActionListener textGUIActionListener = new TextGUIActionListener();
        canncelBtn.addActionListener(textGUIActionListener);
        submitBtn = new JButton("Submit");
        submitBtn.setActionCommand("S");
        submitBtn.addActionListener(textGUIActionListener);

        qFac.nextRow();
        c = qFac.getConstraints(1);
        add(canncelBtn, c);
        c = qFac.getConstraints(1);
        add(submitBtn, c);
        setEnabled(true);
        pack();
    }
}
