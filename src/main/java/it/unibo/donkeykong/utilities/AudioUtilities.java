package it.unibo.donkeykong.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.donkeykong.utilities.ViewConstants.AudioAssets;

/**
 * Static class Audioutilities, manages the audio.
 */
public final class AudioUtilities {

    private static Clip clip;
    private static String filePlaying;
    private static boolean isMuted;

    private AudioUtilities() {
    }

    /**
     * Start playing a soundtrack.
     * 
     * @param fileName the file to be played.
     */
    public static void playSoundtrack(final String fileName) {
        if (filePlaying != null) {
            if (!filePlaying.equals(fileName)) {
                stopSoundtrack();
                startSong(fileName);
            }
        } else {
            startSong(fileName);
        }
    }

    /**
     * Start playing a song.
     * 
     * @param fileName the song to be played.
     */
    private static void startSong(final String fileName) {
        try {
            final BufferedInputStream is = new BufferedInputStream(AudioUtilities.class.getResourceAsStream("/" + fileName));
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (Math.log10(AudioAssets.BASE_VOLUME) * AudioAssets.GAIN_MULTIPLIER));

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

            filePlaying = fileName;
            isMuted = false;
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            final Logger logger = Logger.getLogger(AudioUtilities.class.getName());
            logger.severe(e.getMessage());
        }
    }

    /**
     * Stop playing the soundtrack.
     */
    public static void stopSoundtrack() {
        clip.stop();
        clip.close();
        filePlaying = "";
    }

    /**
     * Get the name of playing soundtrack.
     * 
     * @return the name of playing soundtrack.
     */
    public static String getMusicPlaying() {
        return filePlaying;
    }

    /**
     * Check if a soundtrack is playing.
     * 
     * @return true if a soundtrack is playing.
     */
    public static boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    /**
     * Update soundtrack's volume.
     */
    public static void updateVolume() {
        final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue((gainControl.getMaximum() - gainControl.getMinimum()) 
                             * AudioAssets.BASE_VOLUME
                             + gainControl.getMinimum());
    }

    /**
     * Check if the soundtrack is muted.
     * 
     * @return true if the soundtrack is muted.
     */
    public static boolean isMuted() {
        return isMuted;
    }

    /**
     * Mute or unmute the soundtrack.
     * 
     * @param mute the new mute state.
     */
    public static void setMuted(final boolean mute) {
        if (mute != isMuted) {
            isMuted = mute;
            final BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(isMuted);
        }
    }
}
