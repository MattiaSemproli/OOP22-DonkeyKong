package it.unibo.donkeykong.view;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;

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
                                                                  tile.height, null));
        this.gameController.getGame()
                           .getButtons()
                           .forEach((b, i) -> g.drawImage(i, 
                                                     b.getButtonPos().getX(),
                                                     b.getButtonPos().getY(), 
                                                     b.getButtonDim().getX(), 
                                                     b.getButtonDim().getY(), null));
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
                    g.drawImage(ResourceFuncUtilities.loadSources("dksingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Entity.monkeyWidth, 
                                Constants.Entity.monkeyHeight,  null);
                    break;
                case PLAYER:
                    g.drawImage(ResourceFuncUtilities.loadSources("mariosingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Entity.playerDimension, 
                                Constants.Entity.playerDimension, null);
                    break;
                case POWER_UP:
                    break;
                case PRINCESS:
                    g.drawImage(ResourceFuncUtilities.loadSources("peachessingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Entity.princessWidth, 
                                Constants.Entity.princessHeight, null);
                    break;
                default:
                    break;
            }
        });
    }
}
