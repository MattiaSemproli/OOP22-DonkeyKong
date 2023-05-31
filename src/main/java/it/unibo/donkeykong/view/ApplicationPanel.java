package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.Window.GAME_HEIGHT;
import static it.unibo.donkeykong.utilities.Constants.Window.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.inputs.KeyboardInputs;
import it.unibo.donkeykong.inputs.MouseInputs;

/**
 * Application panel.
 */
public final class ApplicationPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private final transient Application application;

  /**
   * Constructor.
   * 
   * @param application the linked application.
   */
  public ApplicationPanel(final Application application) {
    this.application = application;
    setSize();
    addKeyListener(new KeyboardInputs(this.application));
    addMouseListener(new MouseInputs(this.application));
  }

  private void setSize() {
    setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
  }

  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    this.application.getGameEngine().draw(g);
  }
}
