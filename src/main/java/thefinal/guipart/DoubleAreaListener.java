package thefinal.guipart;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class DoubleAreaListener extends InputNumArea {
    JTextField textField;

    public void bindTextField(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar == '.') {
            // Kill the Fucking Wrong from the input source.
            // Double . is not permitted.
            // First dot is not permitted too.
            if (keyChar == '.' && (textField.getText().contains(".") || textField.getText().isEmpty())) {
                e.consume();
            }
        } else {
            e.consume();
        }
    }
}
