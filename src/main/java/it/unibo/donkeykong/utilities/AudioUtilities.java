package it.unibo.donkeykong.utilities;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Static class that manages the audio.
 */
public final class AudioUtilities {
    private AudioUtilities() {
    }

    private static Clip clip;
    private static String filePlaying;
    private static boolean isMuted;

    public static void playSoundtrack(final String fileName) {
        if(true) return;
        if(filePlaying != null) {
            if(filePlaying != fileName){
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

    private static void startSong(final String fileName) {
        if(true) return;
        try {
            File soundtrackFile = new File("src/main/resources/" + fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundtrackFile);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

            filePlaying = fileName;
            isMuted = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSoundtrack() {
        if(true) return;
        if(clip != null) {
            clip.stop();
            clip.close();
            filePlaying = "";
        }
    }

    public static String getMusicPlaying() {
        return filePlaying;
    }

    public static boolean isPlaying() {
        return clip != null ? clip.isRunning() : false;
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public static void updateVolume() {
        if(true) return;
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(((gainControl.getMaximum() - gainControl.getMinimum()) * Constants.Audio.baseVolume) + gainControl.getMinimum());
    }

    public static void setMuted(final boolean mute) {
        if(true) return;
        if(mute != isMuted) {
            isMuted = mute;
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(isMuted);
        }
    }
}
