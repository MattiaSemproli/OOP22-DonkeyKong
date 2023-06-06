package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.repeatButtonLeftDistance;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.impl.EndPauseController;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * Endgame or pause view, manages endgame or pause graphics.
 */
public final class EndPauseView implements View {

    private final EndPauseController endPauseController;

    private final Rectangle[] volumeButtons = new Rectangle[SettingsAssets.numVolumeButtons];
    private final Rectangle[] gameThemesButtons = new Rectangle[Audio.numGameThemesButtons];
    private final Map<Button, BufferedImage> buttons = new HashMap<>();
    private final Map<Rectangle, BufferedImage> alternativeButtons = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param endPauseController the linked controller.
     */
    public EndPauseView(final EndPauseController endPauseController) {
        this.endPauseController = endPauseController;

        final Button backHome = new ButtonImpl(MenuAssets.rightMenuBorder - SettingsAssets.homeButtonRightDistance, 
                                               MenuAssets.bottomMenuBorder - SettingsAssets.homeButtonBottomDistance, 
                                               SettingsAssets.squareButtonSize, 
                                               SettingsAssets.squareButtonSize, Gamestate.MENU);
        final Button backToPlay = new ButtonImpl(menuX + repeatButtonLeftDistance - SettingsAssets.squareButtonSize, 
                                                 MenuAssets.bottomMenuBorder - SettingsAssets.repeatButtonBottomDistance, 
                                                 SettingsAssets.squareButtonSize, 
                                                 SettingsAssets.squareButtonSize, Gamestate.PLAYING);
        this.volumeButtons[SettingsAssets.volOnB] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                  SettingsAssets.muteButtonY, 
                                                                  SettingsAssets.squareButtonSize, 
                                                                  SettingsAssets.squareButtonSize);
        this.volumeButtons[SettingsAssets.volOffB] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                   SettingsAssets.muteButtonY, 
                                                                   SettingsAssets.squareButtonSize, 
                                                                   SettingsAssets.squareButtonSize);
        this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic0)] = new Rectangle(SettingsAssets.leftSettingsButtonX, 
                                                                                             SettingsAssets.musicsButtonY, 
                                                                                             SettingsAssets.themesButtonWidth, 
                                                                                             SettingsAssets.squareButtonSize);
        this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic1)] = new Rectangle(SettingsAssets.rightSettingsButtonX, 
                                                                                             SettingsAssets.musicsButtonY, 
                                                                                             SettingsAssets.themesButtonWidth, 
                                                                                             SettingsAssets.squareButtonSize);

        this.buttons.put(backHome, getSettingsSources().get(SettingsAssets.homeButton));
        this.buttons.put(backToPlay, getSettingsSources().get(SettingsAssets.backToPlayButton));

        alternativeButtons.put(volumeButtons[SettingsAssets.volOnB], 
                               getSettingsSources().get(SettingsAssets.roundedVolumeOn));
        alternativeButtons.put(volumeButtons[SettingsAssets.volOffB], 
                               getSettingsSources().get(SettingsAssets.roundedVolumeOff));
        alternativeButtons.put(gameThemesButtons[Audio.getThemeSources().get(Audio.menuMusic0)],
                               ResourceFuncUtilities.loadSources("1"));
        alternativeButtons.put(gameThemesButtons[Audio.getThemeSources().get(Audio.menuMusic1)],
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
            pause.setColor(new Color(0, 0, 0, SettingsAssets.pauseBgOpacity));
            pause.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                            menuX, 
                            menuY, 
                            MenuAssets.menuTextureBox, 
                            MenuAssets.menuTextureBox, null);
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
                final Font font = new Font("Arial", Font.BOLD, MenuAssets.fontSize);
                g.setColor(Color.WHITE);
                g.setFont(font);
                final String txt = "Time: " + this.endPauseController.getSecondsFromGameController();
                final int textWidth = g.getFontMetrics(font).stringWidth(txt);
                g.drawString(txt,
                             menuX + (MenuAssets.menuTextureBox - textWidth) / 2,
                             menuY + MenuAssets.menuTextureBox / 2);
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
                        this.endPauseController.startTimer();
                    } else if (Gamestate.getGamestate() == Gamestate.WIN 
                               || Gamestate.getGamestate() == Gamestate.DEATH) {
                        AudioUtilities.playSoundtrack(Audio.gameMusic1);
                        this.endPauseController.startGameController();
                    }
                }
                this.endPauseController.applyGamestate(b.getButtonGamestate());
            }
        });
        if (Gamestate.getGamestate().equals(Gamestate.MENU)) {
            AudioUtilities.playSoundtrack(Audio.menuMusic0);
            this.endPauseController.stopTimer();
        }
        this.mute(e);
        this.themeChange(e);
    }

    private void mute(final Point point) {
        if (this.volumeButtons[SettingsAssets.volOnB].contains(point)) {
            AudioUtilities.setMuted(false);
        } else if (this.volumeButtons[SettingsAssets.volOffB].contains(point)) {
            AudioUtilities.setMuted(true);
        }
    }

    private void themeChange(final Point point) {
        if (this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic0)].contains(point)) {
            AudioUtilities.playSoundtrack(Audio.gameMusic0);
        } else if (this.gameThemesButtons[Audio.getGameSources().get(Audio.gameMusic1)].contains(point)) {
            AudioUtilities.playSoundtrack(Audio.gameMusic1);
        }
    }

    private void drawText(final Graphics g) {
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        menuX + (MenuAssets.menuTextureBox - SettingsAssets.pauseTextWidth) / 2,
                        menuY - (SettingsAssets.pauseTextHeight / 2),
                        SettingsAssets.pauseTextWidth,
                        SettingsAssets.pauseTextHeight, null);
        } else if (Gamestate.getGamestate().equals(Gamestate.DEATH)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        menuX + (MenuAssets.menuTextureBox - SettingsAssets.loseTextWidth) / 2,
                        menuY - (SettingsAssets.loseTextHeight / 2),
                        SettingsAssets.loseTextWidth,
                        SettingsAssets.loseTextHeight, null);
        } else if (Gamestate.getGamestate().equals(Gamestate.WIN)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                        menuX + (MenuAssets.menuTextureBox - SettingsAssets.winTextWidth) / 2,
                        menuY - (SettingsAssets.winTextHeight / 2),
                        SettingsAssets.winTextWidth,
                        SettingsAssets.winTextHeight, null);
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
