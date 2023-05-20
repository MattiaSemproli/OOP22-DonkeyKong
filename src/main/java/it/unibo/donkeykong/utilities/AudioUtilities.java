package it.unibo.donkeykong.utilities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.donkeykong.utilities.Constants.Audio;

/**
 * Static class that manages the audio.
 */
public final class AudioUtilities {
    private AudioUtilities() {
    }

    private static Clip clip;
    private static String filePlaying;
    private static boolean isMuted;

    /**
     * Start playing a soundtrack.
     * 
     * @param fileName the name of the file to play.
     */
    public static void playSoundtrack(final String fileName) {
        if (filePlaying != null) {
            if (filePlaying.equals(fileName)) {
                stopSoundtrack();
                startSong(fileName);
            }
        } else {
            try {
                startSong(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Start a song.
     * 
     * @param fileName song to be played.
     */
    private static void startSong(final String fileName) {
        try {
            File soundtrackFile = new File("src/main/resources/" + fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundtrackFile);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (Math.log10(Audio.baseVolume) * Audio.gainMultiplier));

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

            filePlaying = fileName;
            isMuted = false;
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the soundtrack.
     */
    public static void stopSoundtrack() {
        if (clip != null) {
            clip.stop();
            clip.close();
            filePlaying = "";
        }
    }

    /**
     * Get the name of the soundtrack playing.
     * 
     * @return the name of the soundtrack playing.
     */
    public static String getMusicPlaying() {
        return filePlaying;
    }

    /**
     * Check if a soundtrack is playing.
     * 
     * @return true if a soundtrack is playing, false otherwise.
     */
    public static boolean isPlaying() {
        return clip != null ? clip.isRunning() : false;
    }

    /**
     * Check if the soundtrack is muted.
     * 
     * @return true if the soundtrack is muted, false otherwise.
     */
    public static boolean isMuted() {
        return isMuted;
    }

    /**
     * Update the volume of the soundtrack.
     */
    public static void updateVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(((gainControl.getMaximum() - gainControl.getMinimum()) 
                             * Constants.Audio.baseVolume) 
                             + gainControl.getMinimum());
    }

    /**
     * Mute or unmute the soundtrack.
     * 
     * @param mute true to mute the soundtrack, false to unmute it.
     */
    public static void setMuted(final boolean mute) {
        if (mute != isMuted) {
            isMuted = mute;
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(isMuted);
        }
    }
}
