package thefinal.guipart.NumberInput;

import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;

import javax.swing.JTextField;

public abstract class InputNumArea extends KeyAdapter implements FocusListener {
    public abstract void bindTextField(JTextField textField);
}
