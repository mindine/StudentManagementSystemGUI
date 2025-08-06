package sms;

import javax.swing.text.*;

/**
 * A document filter that limits the maximum number of characters a user can input into a JTextField.
 */
public class LimitDocumentFilter extends DocumentFilter {
    private final int maxLength; // Optional improvement: make maxLength final for immutability

    /**
     * Constructor to set the maximum length allowed.
     * @param maxLength The maximum number of characters allowed.
     */
    public LimitDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Overrides insertion behavior to enforce character limit.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        if (fb.getDocument().getLength() + string.length() <= maxLength) {
            super.insertString(fb, offset, string, attr);
        } else {
            System.out.println("Insert rejected: length limit exceeded."); // Optional logging
        }
    }

    /**
     * Overrides replacement behavior to enforce character limit.
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        if (fb.getDocument().getLength() - length + text.length() <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            System.out.println("Replace rejected: length limit exceeded."); // Optional logging
        }
    }
}
