package it.unibo.donkeykong.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    public static BufferedImage getBufferedSources(final String string) {
        File file = new File(string);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
