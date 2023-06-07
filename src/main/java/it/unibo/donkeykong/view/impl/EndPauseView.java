package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_X;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_Y;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets.REPEAT_BUTTON_LEFT_DISTANCE;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets.getSettingsSources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.controller.impl.EndPauseController;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ViewConstants.AudioAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets;
import it.unibo.donkeykong.utilities.ViewConstants.Window;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * Endgame or pause view, manages endgame or pause graphics.
 */
public final class EndPauseView implements View {

    private final EndPauseController endPauseController;

    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.NUM_VOLUME_BUTTONS];
    private final Rectangle[] gameThemesButtons = new Rectangle[AudioAssets.NUM_GAME_THEMES_BUTTON];
    private final Map<Button, BufferedImage> buttons = new HashMap<>();
    private final Map<Rectangle, BufferedImage> alternativeButtons = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param endPauseController the linked controller.
     */
    public EndPauseView(final EndPauseController endPauseController) {
        this.endPauseController = endPauseController;

        final Button backHome = new ButtonImpl(MenuAssets.RIGHT_MENU_BORDER - SettingsAssets.HOME_BUTTON_RIGHT_DISTANCE, 
                                               MenuAssets.BOTTOM_MENU_BORDER - SettingsAssets.HOME_BUTTON_BOTTOM_DISTANCE,
                                               SettingsAssets.SQUARE_BUTTON_SIZE, 
                                               SettingsAssets.SQUARE_BUTTON_SIZE, Gamestate.MENU);
        final Button backToPlay = new ButtonImpl(MENU_X + REPEAT_BUTTON_LEFT_DISTANCE - SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                 MenuAssets.BOTTOM_MENU_BORDER - SettingsAssets.REPEAT_BUTTON_BOTTOM_DISTANCE,
                                                 SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                 SettingsAssets.SQUARE_BUTTON_SIZE, Gamestate.PLAYING);
        this.volumeButtons[SettingsAssets.VOL_ON_B] = new Rectangle(SettingsAssets.LEFT_SETTINGS_BUTTON_X, 
                                                                    SettingsAssets.MUTE_BUTTON_Y, 
                                                                    SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                                    SettingsAssets.SQUARE_BUTTON_SIZE);
        this.volumeButtons[SettingsAssets.VOL_OFF_B] = new Rectangle(SettingsAssets.RIGHT_SETTINGS_BUTTON_X, 
                                                                     SettingsAssets.MUTE_BUTTON_Y, 
                                                                     SettingsAssets.SQUARE_BUTTON_SIZE, 
                                                                     SettingsAssets.SQUARE_BUTTON_SIZE);
        this.gameThemesButtons[AudioAssets.getGameSources()
                                    .get(AudioAssets.GAME_MUSIC_0)] = new Rectangle(SettingsAssets.LEFT_SETTINGS_BUTTON_X, 
                                                                                    SettingsAssets.MUSIC_BUTTON_Y, 
                                                                                    SettingsAssets.THEMES_BUTTON_WIDTH, 
                                                                                    SettingsAssets.SQUARE_BUTTON_SIZE);
        this.gameThemesButtons[AudioAssets.getGameSources()
                                    .get(AudioAssets.GAME_MUSIC_1)] = new Rectangle(SettingsAssets.RIGHT_SETTINGS_BUTTON_X, 
                                                                                    SettingsAssets.MUSIC_BUTTON_Y, 
                                                                                    SettingsAssets.THEMES_BUTTON_WIDTH, 
                                                                                    SettingsAssets.SQUARE_BUTTON_SIZE);

        this.buttons.put(backHome, getSettingsSources().get(SettingsAssets.HOME_BUTTON));
        this.buttons.put(backToPlay, getSettingsSources().get(SettingsAssets.AGAIN_BUTTON));

        alternativeButtons.put(volumeButtons[SettingsAssets.VOL_ON_B], 
                               getSettingsSources().get(SettingsAssets.ROUNDED_VOLUME_ON));
        alternativeButtons.put(volumeButtons[SettingsAssets.VOL_OFF_B], 
                               getSettingsSources().get(SettingsAssets.ROUNDED_VOLUME_OFF));
        alternativeButtons.put(gameThemesButtons[AudioAssets.getThemeSources().get(AudioAssets.MENU_MUSIC_0)],
                               ResourceFuncUtilities.loadSources("1"));
        alternativeButtons.put(gameThemesButtons[AudioAssets.getThemeSources().get(AudioAssets.MENU_MUSIC_1)],
                               ResourceFuncUtilities.loadSources("2"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics g) {
        final Graphics2D pause;
        if (g instanceof Graphics2D) {
            pause = (Graphics2D) g;
            pause.setColor(new Color(0, 0, 0, SettingsAssets.PAUSE_BG_OPACITY));
            pause.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.drawImage(getMenuSources().get(MenuAssets.MENU_TEXTURE), 
                            MENU_X, 
                            MENU_Y, 
                            MenuAssets.MENU_TEXTURE_BOX, 
                            MenuAssets.MENU_TEXTURE_BOX, null);
            this.buttons.forEach((b, bi) -> g.drawImage(bi, 
                                                        b.getButtonPos().getX(), 
                                                        b.getButtonPos().getY(),
                                                        b.getButtonDim().getX(),
                                                        b.getButtonDim().getY(), null));
            this.drawText(g);
            if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
                this.alternativeButtons.forEach((rectangle, image) -> g.drawImage(image, 
                                                                                  rectangle.x, 
                                                                                  rectangle.y, 
                                                                                  rectangle.width,
                                                                                  rectangle.height, null));
            } else {
                final Font font = new Font("Arial", Font.BOLD, MenuAssets.FONTSIZE);
                g.setColor(Color.WHITE);
                g.setFont(font);
                final String txt = "Time: " + new DecimalFormat("0.00")
                                                .format(this.endPauseController.getSecondsFromGameController());
                final int textWidth = g.getFontMetrics(font).stringWidth(txt);
                g.drawString(txt,
                             MENU_X + (MenuAssets.MENU_TEXTURE_BOX - textWidth) / 2,
                             MENU_Y + MenuAssets.MENU_TEXTURE_BOX / 2);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer, Integer> point) {
        final Point e = new Point(point.getX(), point.getY());
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(e)) {
                if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                    if (Gamestate.getGamestate() == Gamestate.PAUSE) {
                        this.endPauseController.resumeTimer();
                    } else if (Gamestate.getGamestate() == Gamestate.WIN 
                               || Gamestate.getGamestate() == Gamestate.DEATH) {
                        AudioUtilities.playSoundtrack(AudioAssets.GAME_MUSIC_1);
                        this.endPauseController.startGameController();
                    }
                } else if (b.getButtonGamestate().equals(Gamestate.MENU)) {
                    AudioUtilities.playSoundtrack(AudioAssets.MENU_MUSIC_0);
                    if (Gamestate.getGamestate() == Gamestate.PAUSE) {
                        this.endPauseController.stopTimer();
                    }
                }
                this.endPauseController.applyGamestate(b.getButtonGamestate());
            }
        });
        this.mute(e);
        this.themeChange(e);
    }

    private void mute(final Point point) {
        if (this.volumeButtons[SettingsAssets.VOL_ON_B].contains(point)) {
            AudioUtilities.setMuted(false);
        } else if (this.volumeButtons[SettingsAssets.VOL_OFF_B].contains(point)) {
            AudioUtilities.setMuted(true);
        }
    }

    private void themeChange(final Point point) {
        if (this.gameThemesButtons[AudioAssets.getGameSources().get(AudioAssets.GAME_MUSIC_0)].contains(point)) {
            AudioUtilities.playSoundtrack(AudioAssets.GAME_MUSIC_0);
        } else if (this.gameThemesButtons[AudioAssets.getGameSources().get(AudioAssets.GAME_MUSIC_1)].contains(point)) {
            AudioUtilities.playSoundtrack(AudioAssets.GAME_MUSIC_1);
        }
    }

    private void drawText(final Graphics g) {
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        MENU_X + (MenuAssets.MENU_TEXTURE_BOX - SettingsAssets.PAUSE_TEXT_WIDTH) / 2,
                        MENU_Y - (SettingsAssets.PAUSE_TEXT_HEIGHT / 2),
                        SettingsAssets.PAUSE_TEXT_WIDTH,
                        SettingsAssets.PAUSE_TEXT_HEIGHT, null);
        } else if (Gamestate.getGamestate().equals(Gamestate.DEATH)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        MENU_X + (MenuAssets.MENU_TEXTURE_BOX - SettingsAssets.LOSE_TEXT_WIDTH) / 2,
                        MENU_Y - (SettingsAssets.LOSE_TEXT_HEIGHT / 2),
                        SettingsAssets.LOSE_TEXT_WIDTH,
                        SettingsAssets.LOSE_TEXT_HEIGHT, null);
        } else if (Gamestate.getGamestate().equals(Gamestate.WIN)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        MENU_X + (MenuAssets.MENU_TEXTURE_BOX - SettingsAssets.LOSE_TEXT_WIDTH) / 2,
                        MENU_Y - (SettingsAssets.LOSE_TEXT_HEIGHT / 2),
                        SettingsAssets.LOSE_TEXT_WIDTH,
                        SettingsAssets.LOSE_TEXT_HEIGHT, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final int keyCode) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final int keyCode) {
    }
}
