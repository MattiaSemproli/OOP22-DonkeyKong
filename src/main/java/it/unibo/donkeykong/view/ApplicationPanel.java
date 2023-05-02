package it.unibo.donkeykong.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

import it.unibo.donkeykong.controller.impl.ApplicationImpl;
import it.unibo.donkeykong.inputs.KeyboardInputs;
import it.unibo.donkeykong.inputs.MouseInputs;
import static it.unibo.donkeykong.utilities.Constants.Window.GAME_HEIGHT;
import static it.unibo.donkeykong.utilities.Constants.Window.GAME_WIDTH;

/**
 * Application panel.
 */
public final class ApplicationPanel extends JPanel {

  private final transient ApplicationImpl application;

  /**
   * Application constructor.
   * 
   * @param application application.
   */
  public ApplicationPanel(final ApplicationImpl application) {
    this.application = application;
    setSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(new MouseInputs(this));
  }

  private void setSize() {
    setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
  }

  /**
   * @return application.
   */
  public ApplicationImpl getApplication() {
    return this.application;
  }

  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    this.application.getGameEngine().draw(g);
  }
}
