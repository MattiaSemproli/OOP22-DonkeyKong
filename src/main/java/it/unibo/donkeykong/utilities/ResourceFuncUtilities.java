package it.unibo.donkeykong.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Functional utilities for resources.
 */
public final class ResourceFuncUtilities {
    private ResourceFuncUtilities() {
    }

    /**
     * Get the image from the file name.
     * 
     * @param fileName name of the file.
     * @return         the image.
     */
    public static Image getSources(final String fileName) {
        return new ImageIcon(fileName).getImage();
    }

    /**
     * Get the buffered image from the file name.
     * 
     * @param fileName name of the file.
     * @return       the buffered image.
     */
    public static BufferedImage getBufferedSources(final String fileName) {
        File file = new File(fileName);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Get the sources in alternative way.
     * 
     * @param fileName name of the file.
     * @return         the buffered image.
     */
    public static BufferedImage loadSources(final String fileName){
        BufferedImage img = null;
        InputStream s = ResourceFuncUtilities.class.getResourceAsStream("/" + fileName +".png");
        try {
            img = ImageIO.read(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
