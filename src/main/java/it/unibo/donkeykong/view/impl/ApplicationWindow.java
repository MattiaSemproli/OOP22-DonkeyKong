package it.unibo.donkeykong.view.impl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import it.unibo.donkeykong.controller.api.Application;

/**
 * Application window.
 */
public class ApplicationWindow {
    /**
     * The window represents "the box" of the game application.
     * It contains a single panel that displays the game world and other graphical
     * elements.
     * The settings below configure the appearance and behavior of the window.
     * 
     * @param dkPanel the panel containing the content to be displayed in the window.
     * @param application the linked application.
     */
    public ApplicationWindow(final ApplicationPanel dkPanel, final Application application) {
        final JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(dkPanel);
        jframe.setTitle("Donkey Kong");
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setFocusable(true);
        jframe.requestFocus();
        jframe.setLocationRelativeTo(null);
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(final WindowEvent e) {
            }

            @Override
            public void windowLostFocus(final WindowEvent e) {
                application.windowFocusLost();
            }

        });
    }
}
