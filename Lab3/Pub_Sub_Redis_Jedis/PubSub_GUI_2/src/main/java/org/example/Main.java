package org.example;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}