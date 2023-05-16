package it.unibo.donkeykong.controller.impl;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Constants;
import it.unibo.donkeykong.view.ApplicationPanel;
import it.unibo.donkeykong.view.ApplicationWindow;
import it.unibo.donkeykong.game.model.impl.Game;

public class ApplicationImpl implements Application {

    private ApplicationPanel dkPanel;
    private GameEngineImpl gameEngine;
    private MainMenuController mainMenuController;
    private SettingsController settingsController;
    private GameController gameController;
    private PauseController pauseController;
    private LevelsMenuController levelsMenuController;
    private Game game;
    private Clip clip;

    public ApplicationImpl() {
        this.playSoundtrack();
        loadAllSources();
        initialize();
        this.dkPanel = new ApplicationPanel(this);
        new ApplicationWindow(dkPanel);
        dkPanel.requestFocus();
        this.gameEngine = new GameEngineImpl(dkPanel, this);
    }

    public void playSoundtrack() {
        try {
            // Load the soundtrack file
            File soundtrackFile = new File("src/main/resources/soundtrack.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundtrackFile);

            // Create a Clip and open the audio input stream
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Set the song to loop indefinitely
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Start playing the soundtrack
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSoundtrack() {
        clip.stop();
        clip.close();
    }

    @Override
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }

    public void startGame() {
        this.game = new Game();
    }

    private void loadAllSources() {
        Constants.MenuAssets.loadMenuSources();
        Constants.MenuAssets.SettingsAssets.loadSettingsSources();
    }

    public void initialize() {
        this.mainMenuController = new MainMenuController();
        this.settingsController = new SettingsController();
        this.gameController = new GameController();
        this.pauseController = new PauseController();
        this.levelsMenuController = new LevelsMenuController();
    }

    public MainMenuController getMainMenuController() {
        return this.mainMenuController;
    }

    public SettingsController getSettingsController() {
        return this.settingsController;
    }

    public GameController getGameController() {
        return this.gameController;
    }

    public PauseController getPauseController() {
        return this.pauseController;
    }

    public LevelsMenuController getLevelsMenuController() {
        return this.levelsMenuController;
    }

    public Game getGame() {
        return this.game;
    }
}