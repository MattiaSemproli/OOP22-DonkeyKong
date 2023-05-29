package it.unibo.donkeykong.view;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Level;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;

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
        this.gameController.updateAniIndex();
    }

    @Override
    public final void draw(final Graphics g) {
        this.gameController.getDataLevelFromModel()
                           .forEach((tile, sprite) -> g.drawImage(sprite, 
                                                                  tile.x, 
                                                                  tile.y, 
                                                                  tile.width, 
                                                                  tile.height, null));
        g.drawImage(LevelAssets.barrelBox, 0, 108, 56, 96, null);
        this.gameController.getButtonsFromModel()
                           .forEach((b, i) -> g.drawImage(i, 
                                                          b.getButtonPos().getX(),
                                                          b.getButtonPos().getY(), 
                                                          b.getButtonDim().getX(), 
                                                          b.getButtonDim().getY(), null));
        this.gameController.getEntitiesFromGameplay()
                           .stream()
                           .filter(e -> e.getEntityType() == Type.STAR
                                        || e.getEntityType() == Type.HEART
                                        || e.getEntityType() == Type.SNOWFLAKE
                                        || e.getEntityType() == Type.SHIELD)
                           .forEach(e -> g.drawImage(ResourceFuncUtilities.loadSources("playerlife"),
                                                     Math.round(e.getPosition().getX()), 
                                                     Math.round(e.getPosition().getY()),
                                                     e.getWidth(),
                                                     e.getHeight(), null));
        this.gameController.getInteractableEntitiesFromGameplay()
                           .forEach(entity -> this.drawEntity(g, entity));

        /**
         * Draw hitboxes.
         */
        // this.gameController.getEntitiesFromGameplay().forEach(e -> {
        //     final Rectangle2D r = e.getComponent(CollisionComponent.class).get().getHitbox();
        //     g.setColor(java.awt.Color.GREEN);
        //     g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        // });
    }

    private void drawEntity(final Graphics g, final Entity entity) {
        g.drawImage(this.getSprite(entity), 
                    Math.round(entity.getPosition().getX()), 
                    Math.round(entity.getPosition().getY()), 
                    entity.getWidth(), 
                    entity.getHeight(), null);
    }

    private BufferedImage getSprite(final Entity entity) {
        Pair<Integer, Integer> anim = this.gameController.getIdleFromModel(entity);
        return this.gameController.getAnimationFromModel(entity.getEntityType(), anim.getX(), anim.getY());
    }
}
