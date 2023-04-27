package it.unibo.donkeykong.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Dimension;

import it.unibo.donkeykong.controller.impl.ApplicationImpl;
import it.unibo.donkeykong.inputs.KeyboardInputs;
import it.unibo.donkeykong.inputs.MouseInputs;
import static it.unibo.donkeykong.utilities.Constants.Window.*;

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
    //this.application.getGameEngine().draw(g);
    g.drawImage(new ImageIcon("src/main/res/menu_layout.png").getImage(), GAME_WIDTH/2-300, GAME_HEIGHT/2-300, 600, 600, null);
    g.drawImage(new ImageIcon("src/main/res/play_button.png").getImage(), GAME_WIDTH/4 + 150, GAME_HEIGHT/4 + 50, 150, 100, null);
    g.drawImage(new ImageIcon("src/main/res/settings_button.png").getImage(), GAME_WIDTH/4 + 150, GAME_HEIGHT/4 + 150, 150, 100, null);
  }
}
