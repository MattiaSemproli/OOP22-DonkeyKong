package it.unibo.donkeykong.utilities;

import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;
import it.unibo.donkeykong.view.ViewConstants.Window;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Constants class.
 */
public class Constants {

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
         * FPS capslock;
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

    public static final class PowerupAssets {
        public static final int spawnOpPowerUpDelay = 120 * 15;
        public static final int moveOpPowerUpDelay = 180;
        public static final int minSpawn = (int) (Monkey.levelOneStartingMonkeyY / Window.SCALED_TILES_SIZE) + 2;
        public static final int maxSpawn = (int) (Player.levelOneStartingPlayerY / Window.SCALED_TILES_SIZE) - 1;
        public static final int heartWidth = (int) (36 * Window.SCALE);
        public static final int heartHeight = (int) (33 * Window.SCALE);
        public static final int heartYpadding = (int) (8 * Window.SCALE);
        public static final int heartXpadding = (int) (6 * Window.SCALE);
        public static final int shieldWidth = (int) (36 * Window.SCALE);
        public static final int shieldHeight = (int) (40 * Window.SCALE);
        public static final int shieldXpadding = (int) (6 * Window.SCALE);
        public static final int shieldYpadding = (int) (4 * Window.SCALE);
        public static final int freezeDimension = (int) (46 * Window.SCALE);
        public static final int freezePadding = (int) (1 * Window.SCALE);
        public static final int starDimension = (int) (40 * Window.SCALE);
        public static final int starPadding = (int) (4 * Window.SCALE);
        public static final int lifeDimension = (int) (28 * Window.SCALE);
        public static final int lifePadding = (int) (10 * Window.SCALE);
        public static final int powerupBorderDimension = (int) ((Window.SCALED_TILES_SIZE - 8) * Window.SCALE);
        public static final int powerupBorderPadding = (int) (4 * Window.SCALE);
        public static final int powerupActiveDimension = (int) ((Window.SCALED_TILES_SIZE - 20) * Window.SCALE);
        public static final int powerupActivePadding = (int) (((float) (powerupBorderDimension - powerupActiveDimension) / 2) * Window.SCALE);
        public static final String heart = "heart";
        public static final String shield = "shield";
        public static final String freeze = "snowflake";
        public static final String star = "star";
        public static final String life = "playerlife";
        public static final String noLife = "nolife";
        public static final String emptyBorder = "empty_border";

        private static final Map<String, BufferedImage> powerupsources = new HashMap<>();

        private PowerupAssets() {}

        public static final Map<String, BufferedImage> getPowerupSources() {
            return Collections.unmodifiableMap(powerupsources);
        }

        public static final void loadPowerupSources() {
            powerupsources.put(heart, loadSources(heart));
            powerupsources.put(shield, loadSources(shield));
            powerupsources.put(freeze, loadSources(freeze));
            powerupsources.put(star, loadSources(star));
            powerupsources.put(life, loadSources(life));
            powerupsources.put(noLife, loadSources(noLife));
            powerupsources.put(emptyBorder, loadSources(emptyBorder));
        }
    }

    public static final class Level {
        public static final String levelOne = "one";
        public static final String levelTwo = "two";
        public static final String levelThree = "three";
        public static final String levelFour = "four";
        public static final int defaultSpriteSize = 16;
        public static final int blackBlock = 0;
        public static final int platformBlock = 1;
        public static final int coloredLadder = 2;
        public static final int whiteLadder = 3;
        public static final int blockWithUpperLadder = 4;
        public static final int blockWithLowerLadder = 5;
        public static final int blockWithDoubleLadder = 6;
        public static final int levelSpritesLength = 7;
        public static final int numLevelsButtons = 4;
        public static final int platformBlockPadding = Math.round(12 * Window.SCALE);
        public static final int ladderPadding = Math.round(9 * Window.SCALE);
        public static final int princessPadding = 6;
    }

    public static final class Audio {
        public static final String menuMusic0 = "soundtrack.wav";
        public static final String menuMusic1 = "soundtrack1.wav";
        public static final String gameMusic0 = "game.wav";
        public static final String gameMusic1 = "game1.wav";
        public static final int numThemesButtons = 2;
        public static final int numGameThemesButtons = 2;
        public static final float baseVolume = 0.03f;
        public static final int gainMultiplier = 20;

        private static final Map<String, Integer> themeSources = new HashMap<>();
        private static final Map<String, Integer> gameSources = new HashMap<>();

        public static final Map<String, Integer> getThemeSources() {
            return Collections.unmodifiableMap(themeSources);
        }

        public static final Map<String, Integer> getGameSources() {
            return Collections.unmodifiableMap(gameSources);
        }

        public static final void loadThemes() {
            themeSources.put(menuMusic0, 0);
            themeSources.put(menuMusic1, 1);
            
            gameSources.put(gameMusic0, 0);
            gameSources.put(gameMusic1, 1);
        }
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
         * Barrel dimension.
         */
        public static final int BARREL_DIMENSION = (int) (48 * Window.SCALE);
        /**
         * Barrel width.
         */
        public static final int BARREL_WIDTH = (int) (36 * Window.SCALE);
        /**
         * Barrel height.
         */
        public static final int BARREL_HEIGHT = (int) (30 * Window.SCALE);
        public static final int barrelBoxWidth = (int) (56 * Window.SCALE);
        public static final int barrelBoxHeight = (int) (96 * Window.SCALE);
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
        public static final int barrelAni = 0;
        public static final int ddBarrelAni = 1;
        public static final int barrelAniSprites = 4;
        public static final int numBarrel = 2;
        public static final int spriteWidth = 12;
        public static final int spriteHeight = 10;
        public static final int aniBarrelSpeed = 15;

        private static final Map<Integer, BufferedImage> barrelSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getBarrelSources() {
            return Collections.unmodifiableMap(barrelSources);
        }

        public static final void loadBarrelSources() {
            barrelSources.put(barrelAni, loadSources("barrel"));
            barrelSources.put(ddBarrelAni, loadSources("barrel_dd"));
        }
    }

    public static final class Player {
        public static final float levelOneStartingPlayerX = Window.SCALED_TILES_SIZE;
        public static final float levelOneStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelTwoStartingPlayerX = Window.SCALED_TILES_SIZE;
        public static final float levelTwoStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelThreeStartingPlayerX = Window.SCALED_TILES_SIZE * 2;
        public static final float levelThreeStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelFourStartingPlayerX = Window.SCALED_TILES_SIZE * (Window.TILES_IN_WIDTH / 2);
        public static final float levelFourStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final int playerDimension = (int) (48 * Window.SCALE);
        public static final int ladderErrorPercentage = 40;
        public static final float canLadderError = playerDimension * ladderErrorPercentage / 100;
        public static final float velocity = 2f * Window.SCALE;
        public static final int numLives = 3;
        public static final int damageTaken = -1;
        public static final int doubleDamage = 2 * damageTaken;
        public static final int extraLife = 1;
        public static final int starDuration = 120 * 3;
        public static final int freezeDuration = 120 * 3;
        public static final int movementAni = 0;
        public static final int climbingAni = 1;
        public static final int spriteDimension = 16;
        public static final int movementAniSprites = 3;
        public static final int numMovementAni = 5;
        public static final int runAni = 0;
        public static final int jumpAni = 2;
        public static final int leftAni = 0;
        public static final int rightAni = 1;
        public static final int midAirAni = 1;
        public static final int climbingAniSprites = 2;
        public static final int climbAni = 4;
        public static final int aniPlayerSpeed = 15;
        public static final int aniClimbSpeed = 15;
        
        private static final Map<Integer, BufferedImage> playerSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getPlayerSources() {
            return Collections.unmodifiableMap(playerSources);
        }

        public static final void loadPlayerSources() {
            playerSources.put(movementAni, loadSources("mario_movement"));
            playerSources.put(climbingAni, loadSources("climbingplayer"));
        }
    }
    
    public static final class Monkey {
        public static final float levelOneStartingMonkeyX = Window.SCALED_TILES_SIZE / 2f;
        public static final float levelOneStartingMonkeyY = Window.SCALED_TILES_SIZE * 2 + Level.platformBlockPadding;
        public static final int monkeyWidth = (int) (120 * Window.SCALE);
        public static final int monkeyHeight = (int) (96 * Window.SCALE);
        public static final int monkeyAni = 0;
        public static final int monkeyAniSprites = 4;
        public static final int spriteWidth = 40;
        public static final int spriteHeight = 32;
        public static final int aniMonkeySpeed = 25;
        public static final int throwAnimationTime = aniMonkeySpeed * (monkeyAniSprites - 1);

        private static final Map<Integer, BufferedImage> monkeySources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getMonkeySources() {
            return Collections.unmodifiableMap(monkeySources);
        }

        public static final void loadMonkeySources() {
            monkeySources.put(monkeyAni, loadSources("donkeykong"));
        }
    }

    public static final class Princess {
        public static final float levelOneStartingPrincessX = Window.SCALED_TILES_SIZE * 5;
        public static final float levelOneStartingPrincessY = Window.SCALED_TILES_SIZE - Level.princessPadding;
        public static final int princessWidth = (int) (48 * Window.SCALE);
        public static final int princessHeight = (int) (66 * Window.SCALE);
        public static final float velocity = 0.5f * Window.SCALE;
        public static final int nextRandomMoveTime = 24;
        public static final int totalProbability = 10;
        public static final int sameDirProb = 4;
        public static final int changeDirProb = 7;
        public static final int noMoveProb = 9;
        public static final int noMoveAddictionalTime = 24;
        public static final int spriteWidth = 16;
        public static final int spriteHeight = 22;
        public static final int princessAni = 0;
        public static final int leftAni = 0;
        public static final int rightAni = 1;
        public static final int princessAniSprites = 3;
        public static final int numPrincessAni = 2; 
        public static final int aniPrincessSpeed = 15;

        private static final Map<Integer, BufferedImage> princessSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getPrincessSources() {
            return Collections.unmodifiableMap(princessSources);
        }

        public static final void loadPrincessSources() {
            princessSources.put(princessAni, loadSources("princess"));
        }
    }

    public static final class Action {
        public static final int MOVE_LEFT = 65;
        public static final int MOVE_LEFT_ARROW = 37;
        public static final int MOVE_RIGHT = 68;
        public static final int MOVE_RIGHT_ARROW = 39;
        public static final int MOVE_UP = 87;
        public static final int MOVE_UP_ARROW = 38;
        public static final int MOVE_DOWN = 83;
        public static final int MOVE_DOWN_ARROW = 40;
        public static final int JUMP = 32;
        public static final int ESCAPE = 27;

        public static boolean isMovementCode(final int code) {
            return code == MOVE_LEFT || code == MOVE_LEFT_ARROW 
                || code == MOVE_RIGHT || code == MOVE_RIGHT_ARROW
                || code == MOVE_UP || code == MOVE_UP_ARROW 
                || code == MOVE_DOWN || code == MOVE_DOWN_ARROW
                || code == JUMP;
        }
    }
}
