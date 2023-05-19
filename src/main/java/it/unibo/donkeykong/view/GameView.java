package it.unibo.donkeykong.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Game view.
 */
public class GameView implements GameEngine {

    private final GameController gameController;

    /**
     * 
     * @param gameController set the controller to this view.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameController.getGame()
                           .getDataLevel()
                           .forEach((tile, sprite) -> g.drawImage(sprite, 
                                                                  tile.x, 
                                                                  tile.y, 
                                                                  tile.width, 
                                                                  tile.height, 
                                                                  null));
        this.gameController.getGame()
                           .getButtons()
                           .forEach(b -> g.drawImage(b.getButtonImage(), 
                                                     b.getButtonPos().getX(),
                                                     b.getButtonPos().getY(), 
                                                     b.getButtonDim().getX(), 
                                                     b.getButtonDim().getY(), 
                                                     null));
        this.gameController.getGameplay().getEntities().forEach(entity -> {
            switch (entity.getEntityType()) {
                case BARREL:
                    break;
                case BLOCK:
                    break;
                case BLOCK_LADDER_DOWN:
                    break;
                case BLOCK_LADDER_UP:
                    break;
                case BLOCK_LADDER_UPDOWN:
                    break;
                case LADDER:
                    break;
                case MONKEY:
                    g.drawImage(ResourceFuncUtilities.loadSources("dksingletry"), Math.round(entity.getPosition().getX()), Math.round(entity.getPosition().getY()), 120, 96,  null);
                    break;
                case PLAYER:
                    g.drawImage(ResourceFuncUtilities.loadSources("mariosingletry"), Math.round(entity.getPosition().getX()), Math.round(entity.getPosition().getY()), 48, 48, null);
                    break;
                case POWER_UP:
                    break;
                case PRINCESS:
                    g.drawImage(ResourceFuncUtilities.loadSources("peachessingletry"), Math.round(entity.getPosition().getX()), Math.round(entity.getPosition().getY()), 48, 66, null);
                    break;
                default:
                    break;
            }
        });
    }
}
