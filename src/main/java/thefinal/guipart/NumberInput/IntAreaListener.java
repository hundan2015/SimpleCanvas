package thefinal.guipart.NumberInput;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import thefinal.guipart.InfoGUIUpdater;

public class IntAreaListener extends InputNumArea {
    JTextField textField;

    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
            // Pretty familier to the DoubleAreaListener hah?
            // But no the fucking dot problem.
            InfoGUIUpdater.updateModel();
        } else {
            e.consume();
        }
        if (textField.getText().isEmpty() == false) {
            InfoGUIUpdater.updateModel();
        }
    }

    @Override
    public void bindTextField(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        if (textField.getText().isEmpty()) {
            textField.setText("0");
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        InfoGUIUpdater.updateModel();
    }
}
