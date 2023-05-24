package it.unibo.donkeykong.view;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
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
        this.gameController.getDataLevelFromModel()
                           .forEach((tile, sprite) -> g.drawImage(sprite, 
                                                                  tile.x, 
                                                                  tile.y, 
                                                                  tile.width, 
                                                                  tile.height, null));
        this.gameController.getButtonsFromModel()
                           .forEach((b, i) -> g.drawImage(i, 
                                                     b.getButtonPos().getX(),
                                                     b.getButtonPos().getY(), 
                                                     b.getButtonDim().getX(), 
                                                     b.getButtonDim().getY(), null));
        this.gameController.getEntitiesFromGameplay().forEach(entity -> {
            switch (entity.getEntityType()) {
                case BARREL:
                    g.drawImage(ResourceFuncUtilities.loadSources("barrelsingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Barrel.barrelWidth, 
                                Constants.Barrel.barrelHeight,  null);
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
                                Constants.Monkey.monkeyWidth, 
                                Constants.Monkey.monkeyHeight,  null);
                    break;
                case PLAYER:
                    g.drawImage(ResourceFuncUtilities.loadSources("mariosingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Player.playerDimension, 
                                Constants.Player.playerDimension, null);
                    break;
                case POWER_UP:
                    break;
                case PRINCESS:
                    g.drawImage(ResourceFuncUtilities.loadSources("peachessingletry"), 
                                Math.round(entity.getPosition().getX()), 
                                Math.round(entity.getPosition().getY()), 
                                Constants.Princess.princessWidth, 
                                Constants.Princess.princessHeight, null);
                    break;
                default:
                    break;
            }
        });

        /**
         * Draw hitboxes.
         */
        this.gameController.getEntitiesFromGameplay().forEach(e -> {
            final Rectangle2D r = e.getComponent(CollisionComponent.class).get().getHitbox();
            g.setColor(java.awt.Color.GREEN);
            g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        });
    }
}
