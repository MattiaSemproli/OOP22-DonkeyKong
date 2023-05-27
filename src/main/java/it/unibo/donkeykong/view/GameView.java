package it.unibo.donkeykong.view;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Optional;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.CollisionComponent;
import it.unibo.donkeykong.game.ecs.impl.DoubleDamageComponent;
import it.unibo.donkeykong.game.ecs.impl.HealthComponent;
import it.unibo.donkeykong.game.ecs.impl.MovementComponent;
import it.unibo.donkeykong.game.ecs.impl.ThrowComponent;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.utilities.Direction;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.PlayerIdle;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Princess;

/**
 * Game view.
 */
public class GameView implements GameEngine {

    private final GameController gameController;
    private int aniTick, aniIndex, aniSpeed = 15;

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
        this.gameController.getButtonsFromModel()
                           .forEach((b, i) -> g.drawImage(i, 
                                                          b.getButtonPos().getX(),
                                                          b.getButtonPos().getY(), 
                                                          b.getButtonDim().getX(), 
                                                          b.getButtonDim().getY(), null));
        this.gameController.getInteractableEntitiesFromGameplay()
                           .forEach(entity -> this.drawEntity(g, entity));

        /**
         * Draw hitboxes.
         */
        //this.drawHitboxes(g);
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
