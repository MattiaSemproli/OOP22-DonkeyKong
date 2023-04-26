package it.unibo.donkeykong.view;

import javax.swing.JFrame;

/**
 * Application window.
 */
public class ApplicationWindow {
  /**
   * The window represents "the box" of the game application.
   * It contains a single panel that displays the game world and other graphical elements.
   * The settings below configure the appearance and behavior of the window.
   * 
   * @param dkPanel The panel containing the content to be displayed in the window.
   */
  public ApplicationWindow(final ApplicationPanel dkPanel) {
    final JFrame jframe = new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(dkPanel);
    jframe.setTitle("Donkey Kong");
    jframe.setLocationRelativeTo(null);
    jframe.setResizable(false);
    jframe.pack();
    jframe.setVisible(true);
    jframe.setFocusable(true);
    jframe.requestFocus();
    jframe.setLocationRelativeTo(null);
  }
}