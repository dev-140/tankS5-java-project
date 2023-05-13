package main;

import javax.swing.*;
import javax.swing.text.*;

public class NumberOnlyTextField extends JTextField {

    public NumberOnlyTextField(int columns) {
        super(columns);
        setDocument(new NumberOnlyDocument());
    }

    private static class NumberOnlyDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            boolean isNumber = true;
            for (char c : chars) {
                if (!Character.isDigit(c)) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                super.insertString(offs, new String(chars), a);
            }
        }
    }
}
