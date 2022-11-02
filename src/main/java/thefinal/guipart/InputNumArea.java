package thefinal.guipart;

import java.awt.event.KeyAdapter;

import javax.swing.JTextField;

public abstract class InputNumArea extends KeyAdapter {
    abstract void bindTextField(JTextField textField);
}
