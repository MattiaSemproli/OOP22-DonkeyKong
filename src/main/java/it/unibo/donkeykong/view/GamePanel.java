package it.unibo.donkeykong.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

import it.unibo.donkeykong.utilities.Constants;

/**
 * Game panel.
 */
public final class GamePanel extends JPanel {

  public GamePanel() {
    setSize();
  }

  private void setSize() {
    setPreferredSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
  }

  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);
  }

}
