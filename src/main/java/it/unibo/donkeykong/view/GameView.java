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
        this.updateAnimations();
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
                           .forEach((b, i) -> {
                                if(this.gameController.getEntitiesFromGameplay().stream().filter(e -> e.getEntityType() == Type.PLAYER).findAny().isPresent())
                                    for (int l = 0; l < this.gameController.getEntitiesFromGameplay().stream()
                                                   .filter(e -> e.getEntityType() == Type.PLAYER)
                                                   .map(p -> p.getComponent(HealthComponent.class)
                                                   .get().getLifes())
                                                   .findFirst()
                                                   .get(); 
                                                   l++) {
                                    g.drawImage(ResourceFuncUtilities.loadSources("playerlife"), 
                                                b.getButtonPos().getX() - 50 * (l + 1),
                                                b.getButtonPos().getY(), 
                                                b.getButtonDim().getX() / 3, 
                                                b.getButtonDim().getY() / 3, null);
                                    }
                                                   
                                                g.drawImage(i, 
                                                          b.getButtonPos().getX(),
                                                          b.getButtonPos().getY(), 
                                                          b.getButtonDim().getX(), 
                                                          b.getButtonDim().getY(), null);});
        this.gameController.getEntitiesFromGameplay()
                           .stream().filter(e -> e.getEntityType() == Type.PLAYER
                                                 || e.getEntityType() == Type.MONKEY
                                                 || e.getEntityType() == Type.BARREL
                                                 || e.getEntityType() == Type.PRINCESS)
                           .forEach(entity -> {
                                this.drawEntity(g, entity);
        });

        /**
         * Draw hitboxes.
         */
        this.drawHitboxes(g);
        
    }

    private void drawHitboxes(final Graphics g) {
        this.gameController.getEntitiesFromGameplay().forEach(e -> {
            final Rectangle2D r = e.getComponent(CollisionComponent.class).get().getHitbox();
            g.setColor(java.awt.Color.GREEN);
            g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        });
    }

    private void drawEntity(final Graphics g, final Entity entity) {
        g.drawImage(this.getSprite(entity), 
                    Math.round(entity.getPosition().getX()), 
                    Math.round(entity.getPosition().getY()), 
                    entity.getWidth(), 
                    entity.getHeight(), null);
    }

    private BufferedImage getSprite(final Entity entity) {
        Pair<Integer, Integer> anim = this.getIdle(entity);
        return this.gameController.getAnimationFromModel(entity.getEntityType(), anim.getX(), anim.getY());
    }

    private Pair<Integer, Integer> getIdle(final Entity entity) {
        if (entity.getEntityType() == Type.PLAYER) {
            final MovementComponent mc = entity.getComponent(MovementComponent.class).get();
            switch(PlayerIdle.getPlayerIdle()) {
                case RUN:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.leftAni 
                                                                             : Player.rightAni, 
                                      this.aniIndex); 
                case FALLING:
                case JUMP:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.jumpAni + Player.leftAni 
                                                                             : Player.jumpAni + Player.rightAni, 
                                      mc.isInAir() ? Player.midAirAni 
                                                         : Player.movementAni);
                case STOPCLIMBING:
                    return new Pair<>(Player.climbAni, Player.climb);
                case CLIMBING:
                    return new Pair<>(Player.climbAni, this.aniIndex > 1 ? this.aniIndex - 1 : this.aniIndex);
                case STOP:
                default:
                    return new Pair<>(mc.getFacing() == Direction.LEFT ? Player.leftAni 
                                                                             : Player.rightAni,
                                      Player.runAni);
            }
        }
        if (entity.getEntityType() == Type.BARREL) {
            return new Pair<>(entity.getComponent(DoubleDamageComponent.class)
                                    .get().getDoubleDamage() ? Barrel.ddBarrelAni : Barrel.barrelAni, 
                              this.aniIndex);    
        }
        if (entity.getEntityType() == Type.MONKEY) {
            if(entity.getComponent(ThrowComponent.class).get().isThrowing()) {
                return new Pair<>(Monkey.monkeyAni, this.aniIndex + 1);
            } else {
                return new Pair<>(Monkey.monkeyAni, Monkey.monkeyAni);
            }
        }
        return new Pair<>(entity.getComponent(MovementComponent.class)
                                .get().getFacing() == Direction.LEFT ? Princess.leftAni 
                                                                     : Princess.rightAni, 
                          this.aniIndex);
    }

    private void updateAnimations() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 3){
                aniIndex = 0;
            }
        }
    }
}
