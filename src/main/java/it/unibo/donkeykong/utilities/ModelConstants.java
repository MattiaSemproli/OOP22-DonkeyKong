package it.unibo.donkeykong.utilities;

import it.unibo.donkeykong.utilities.ViewConstants.Window;

/**
 * Model constants class.
 */
public class ModelConstants {

    /**
     * Application constants.
     */
    public static class Application {
        /**
         * Number of tiles in width.
         */
        public static final int TILES_IN_WIDTH = 14;
        /**
         * Number of tiles in height.
         */
        public static final int TILES_IN_HEIGHT = 14;
        /**
         * Scaled tile size in pixels.
         */
        public static final int SCALED_TILES_SIZE = Math.round(48 * Window.SCALE);
        /**
         * Game width.
         */
        public static final int GAME_WIDTH = TILES_IN_WIDTH * SCALED_TILES_SIZE;
        /**
         * Game height.
         */
        public static final int GAME_HEIGHT = TILES_IN_HEIGHT * SCALED_TILES_SIZE;
    }

    /**
     * GameLoop constants.
     */
    public static class GameLoop {
        /**
         * FPS capslock.
         */
        public static final int FPS_SET = 120;
        /**
         * Nanoseconds in a second.
         */
        public static final double NANOSECOND = 1_000_000_000.0;
        /**
         * Starting frame value.
         */
        public static final int FRAME_DEFAULT = 0;
        /**
         * Starting delta value.
         */
        public static final int DELTAF_DEFAULT = 0;
    }

    /**
     * Powerup constants.
     */
    public static final class Powerup {
        /**
         * Powerful powerup spawning delay.
         */
        public static final int PUPS_SPAWN_DELAY = 120 * 15;
        /**
         * Powerful powerup moving delay.
         */
        public static final int PUPS_MOVE_DELAY = 180;
        /**
         * Powerup minimum y position to spawn.
         */
        public static final int MIN_SPAWN = (int) (Monkey.LEVEL_ONE_STARTING_Y / Window.SCALED_TILES_SIZE) + 2;
        /**
         * Powerup maximum y position to spawn.
         */
        public static final int MAX_SPAWN = (int) (Player.LEVEL_ONE_STARTING_Y / Window.SCALED_TILES_SIZE) - 1;
        /**
         * Heart powerup width.
         */
        public static final int HEART_WIDTH = (int) (36 * Window.SCALE);
        /**
         * Heart powerup height.
         */
        public static final int HEART_HEIGHT = (int) (33 * Window.SCALE);
        /**
         * Heart powerup y padding.
         */
        public static final int HEART_Y_PADDING = (int) (8 * Window.SCALE);
        /**
         * Heart powerup x padding.
         */
        public static final int HEART_X_PADDING = (int) (6 * Window.SCALE);
        /**
         * Shield powerup width.
         */
        public static final int SHIELD_WIDTH = (int) (36 * Window.SCALE);
        /**
         * Shield powerup height.
         */
        public static final int SHIELD_HEIGHT = (int) (40 * Window.SCALE);
        /**
         * Shield powerup y padding.
         */
        public static final int SHIELD_Y_PADDING = (int) (4 * Window.SCALE);
        /**
         * Shield powerup x padding.
         */
        public static final int SHIELD_X_PADDING = (int) (6 * Window.SCALE);
        /**
         * Freeze powerup dimension.
         */
        public static final int FREEZE_DIMENSION = (int) (46 * Window.SCALE);
        /**
         * Freeze powerup padding.
         */
        public static final int FREEZE_PADDING = (int) (1 * Window.SCALE);
        /**
         * Star powerup dimension.
         */
        public static final int STAR_DIMENSION = (int) (40 * Window.SCALE);
        /**
         * Star powerup padding.
         */
        public static final int STAR_PADDING = (int) (4 * Window.SCALE);
    }

    /**
     * Level constants.
     */
    public static final class Level {
        /**
         * Black block index.
         */
        public static final int BLACK_BLOCK = 0;
        /**
         * Platform block index.
         */
        public static final int PLATFORM_BLOCK = 1;
        /**
         * Ladder index.
         */
        public static final int COLORED_LADDER = 2;
        /**
         * White ladder index.
         */
        public static final int WHITE_LADDER = 3;
        /**
         * Block with up ladder index.
         */
        public static final int BLOCK_UP_LADDER = 4;
        /**
         * Block with down ladder index.
         */
        public static final int BLOCK_DOWN_LADDER = 5;
        /**
         * Block with both ladder down and up index.
         */
        public static final int BLOCK_BOTH_LADDER = 6;
        /**
         * Padding of a platform block.
         */
        public static final int PLATFORM_BLOCK_PADDING = Math.round(12 * Window.SCALE);
        /**
         * Padding of a ladder.
         */
        public static final int LADDER_PADDING = Math.round(9 * Window.SCALE);
        /**
         * Princess padding.
         */
        public static final int PRINCESS_PADDING = 6;
    }

    /**
     * Physics constants.
     */
    public static final class Physics {
        /**
         * Jump speed.
         */
        public static final float JUMP_SPEED = -2.4f * Window.SCALE;
        /**
         * Gravity.
         */
        public static final float GRAVITY = 0.04f * Window.SCALE;
        /**
         * Player's speed in air multiplier.
         */
        public static final float PLAYER_AIR_MULTIPLIER = 0.6f;
        /**
         * Barrel's speed in air multiplier.
         */
        public static final float BARREL_AIR_MULTIPLIER = 0.4f;
        /**
         * Gravity while jumping.
         */
        public static final float JUMP_GRAVITY_MULTIPLIER = 1.3f;
    }

    /**
     * Barrel constants.
     */
    public static final class Barrel {
        /**
         * Barrel width.
         */
        public static final int BARREL_WIDTH = (int) (36 * Window.SCALE);
        /**
         * Barrel height.
         */
        public static final int BARREL_HEIGHT = (int) (30 * Window.SCALE);
        /**
         * Barrel floor error tolerance.
         */
        public static final int BARREL_FLOOR_ERROR = 12;
        /**
         * Barrel velocity.
         */
        public static final float VELOCITY = 2f * Window.SCALE;
        /**
         * Barrel spawning delay.
         */
        public static final int SPAWN_DELAY = 130;
        /**
         * INDEX that spawning barrel is a double damage type.
         */
        public static final int DOUBLE_DAMAGE_INDEX = 3;
        /**
         * Probability that spawning barrel is double damage type.
         */
        public static final int TOTAL_PU_PROBABILITY = 10;
        /**
         * Probability that barrel change direction or not.
         */
        public static final int TOTAL_DIR_PROBABILITY = 2;
        /**
         * Index that barrel change direction.
         */
        public static final int CHANGE_DIR_INDEX = 1;
        /**
         * Barrel animation speed.
         */
        public static final int ANI_BARREL_SPEED = 15;
        /**
         * Barrel animation index.
         */
        public static final int BARREL_ANI = 0;
        /**
         * Double damage barrel animation index.
         */
        public static final int DD_BARREL_ANI = 1;
        /**
         * Number of barrel animation sprites.
         */
        public static final int BARREL_ANI_SPRITES = 4;
    }

    /**
     * Player constants.
     */
    public static final class Player {
        /**
         * Player level one starting x position.
         */
        public static final float LEVEL_ONE_STARTING_X = Window.SCALED_TILES_SIZE;
        /**
         * Player level one starting y position.
         */
        public static final float LEVEL_ONE_STARTING_Y = Window.SCALED_TILES_SIZE * 12 + Level.PLATFORM_BLOCK_PADDING;
        /**
         * Player level two starting x position.
         */
        public static final float LEVEL_TWO_STARTING_X = Window.SCALED_TILES_SIZE;
        /**
         * Player level two starting y position.
         */
        public static final float LEVEL_TWO_STARTING_Y = Window.SCALED_TILES_SIZE * 12 + Level.PLATFORM_BLOCK_PADDING;
        /**
         * Player level three starting x position.
         */
        public static final float LEVEL_THREE_STARTING_X = Window.SCALED_TILES_SIZE * 2;
        /**
         * Player level three starting y position.
         */
        public static final float LEVEL_THREE_STARTING_Y = Window.SCALED_TILES_SIZE * 12 + Level.PLATFORM_BLOCK_PADDING;
        /**
         * Player level four starting x position.
         */
        public static final float LEVEL_FOUR_STARTING_X = Window.SCALED_TILES_SIZE * (Window.TILES_IN_WIDTH / 2);
        /**
         * Player level four starting y position.
         */
        public static final float LEVEL_FOUR_STARTING_Y = Window.SCALED_TILES_SIZE * 12 + Level.PLATFORM_BLOCK_PADDING;
        /**
         * Player dimension.
         */
        public static final int PLAYER_DIMENSION = (int) (48 * Window.SCALE);
        /**
         * Player speed.
         */
        public static final float VELOCITY = 2f * Window.SCALE;
        /**
         * Number of lives.
         */
        public static final int NUM_LIVES = 3;
        /**
         * Damage taken from barrel.
         */
        public static final int DAMAGE_TAKEN = -1;
        /**
         * Double damage.
         */
        public static final int DOUBLE_DAMAGE = 2 * DAMAGE_TAKEN;
        /**
         * Extra life.
         */
        public static final int EXTRA_LIFE = 1;
        /**
         * Star powerup duration.
         */
        public static final int STAR_DURATION = 120 * 3;
        /**
         * Freeze powerup duration.
         */
        public static final int FREEZE_DURATION = 120 * 3;
        /**
         * Run animation index.
         */
        public static final int RUN_ANI = 0;
        /**
         * Jump animation index.
         */
        public static final int JUMP_ANI = 2;
        /**
         * Player facing left animation index.
         */
        public static final int LEFT_ANI = 0;
        /**
         * Player facing right animation index.
         */
        public static final int RIGHT_ANI = 1;
        /**
         * Player when in mid air animation index.
         */
        public static final int MID_AIR_ANI = 1;
        /**
         * Player animation speed.
         */
        public static final int ANI_PLAYER_SPEED = 15;
        /**
         * Player climbing animation speed.
         */
        public static final int ANI_CLIMB_SPEED = 15;
        /**
         * Climbing animation index.
         */
        public static final int CLIMB_ANI = 4;
        /**
         * Number of climbing ani sprites.
         */
        public static final int CLIMB_ANI_SPRITES = 2;
        /**
         * Movement animation index.
         */
        public static final int MOVEMENT_ANI = 0;
        /**
         * Number of movement ani sprites.
         */
        public static final int MOVEMENT_ANI_SPRITES = 3;
    }

    /**
     * Monkey constants.
     */
    public static final class Monkey {
        /**
         * Monkey starting x position.
         */
        public static final float LEVEL_ONE_STARTING_X = Window.SCALED_TILES_SIZE / 2f;
        /**
         * Monkey starting y position.
         */
        public static final float LEVEL_ONE_STARTING_Y = Window.SCALED_TILES_SIZE * 2 + Level.PLATFORM_BLOCK_PADDING;
        /**
         * Monkey width.
         */
        public static final int MONKEY_WIDTH = (int) (120 * Window.SCALE);
        /**
         * Monkey height.
         */
        public static final int MONKEY_HEIGHT = (int) (96 * Window.SCALE);
        /**
         * Monkey ani index.
         */
        public static final int MONKEY_ANI = 0;
        /**
         * Number of monkey ani sprites.
         */
        public static final int MONKEY_ANI_SPRITES = 4;
        /**
         * Monkey animation speed.
         */
        public static final int ANI_MONKEY_SPEED = 25;
        /**
         * Monkey throw animation time.
         */
        public static final int THROW_ANIMATION_TIME = ANI_MONKEY_SPEED * (MONKEY_ANI_SPRITES - 1);
    }

    /**
     * Princess constants.
     */
    public static final class Princess {
        /**
         * Princess starting x position.
         */
        public static final float LEVEL_ONE_STARTING_X = Window.SCALED_TILES_SIZE * 5;
        /**
         * Princess starting y position.
         */
        public static final float LEVEL_ONE_STARTING_Y = Window.SCALED_TILES_SIZE - Level.PRINCESS_PADDING;
        /**
         * Princess width.
         */
        public static final int PRINCESS_WIDTH = (int) (48 * Window.SCALE);
        /**
         * Princess height.
         */
        public static final int PRINCESS_HEIGHT = (int) (66 * Window.SCALE);
        /**
         * Princess speed.
         */
        public static final float VELOCITY = 0.5f * Window.SCALE;
        /**
         * Princess animation speed.
         */
        public static final int ANI_PRINCESS_SPEED = 15;
        /**
         * Princess facing left index.
         */
        public static final int LEFT_ANI = 0;
        /**
         * Princess facing right index.
         */
        public static final int RIGHT_ANI = 1;
        /**
         * Time until princess makes another move.
         */
        public static final int NEXT_RANDOM_MOVE_TIME = 24;
        /**
         * Total chance.
         */
        public static final int TOTAL_PROBABILITY = 10;
        /**
         * Chance of moving in the same direction.
         */
        public static final int SAME_DIR_PROB = 4;
        /**
         * Chance of changing direction.
         */
        public static final int CHANGE_DIR_PROB = 7;
        /**
         * Chance of not moving.
         */
        public static final int NO_MOVE_PROB = 9;
        /**
         * Addictional time when princess didn't move in the previous move.
         */
        public static final int NO_MOVE_ADDICTIONAL_TIME = 24;
        /**
         * Princess ani index.
         */
        public static final int PRINCESS_ANI = 0;
        /**
         * Number of princess ani sprites.
         */
        public static final int PRINCESS_ANI_SPRITES = 3;
    }

    /**
     * Action constants.
     */
    public static final class Action {
        /**
         * Integer code for left action (Key A).
         */
        public static final int MOVE_LEFT = 65;
        /**
         * Integer code for left action (Left arrow).
         */
        public static final int MOVE_LEFT_ARROW = 37;
        /**
         * Integer code for right action (Key D).
         */
        public static final int MOVE_RIGHT = 68;
        /**
         * Integer code for right action (Right arrow).
         */
        public static final int MOVE_RIGHT_ARROW = 39;
        /**
         * Integer code for up action (Key W).
         */
        public static final int MOVE_UP = 87;
        /**
         * Integer code for up action (Up arrow).
         */
        public static final int MOVE_UP_ARROW = 38;
        /**
         * Integer code for down action (Key S).
         */
        public static final int MOVE_DOWN = 83;
        /**
         * Integer code for down action (Down arrow).
         */
        public static final int MOVE_DOWN_ARROW = 40;
        /**
         * Integer code for jump action (Space).
         */
        public static final int JUMP = 32;
        /**
         * Integer code for pause action (Esc).
         */
        public static final int ESCAPE = 27;

        private Action() { }

        /**
         * Check if a code is a movement code (no escape).
         * 
         * @param code the code to check.
         * @return true if a code is a movement code.
         */
        public static boolean isMovementCode(final int code) {
            return code == MOVE_LEFT || code == MOVE_LEFT_ARROW 
                || code == MOVE_RIGHT || code == MOVE_RIGHT_ARROW
                || code == MOVE_UP || code == MOVE_UP_ARROW 
                || code == MOVE_DOWN || code == MOVE_DOWN_ARROW
                || code == JUMP;
        }
    }
}
