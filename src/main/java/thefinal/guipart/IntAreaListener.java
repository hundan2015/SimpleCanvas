package thefinal.guipart;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class IntAreaListener extends InputNumArea {
    JTextField textField;

    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
            // Pretty familier to the DoubleAreaListener hah?
            // But no the fucking dot problem.
        } else {
            e.consume();
        }
    }

    @Override
    public void bindTextField(JTextField textField) {
        this.textField = textField;
    }
}
