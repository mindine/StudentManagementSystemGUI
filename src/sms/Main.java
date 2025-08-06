package sms;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SMS app = new SMS();
            app.setVisible(true);
        });
    }
}
