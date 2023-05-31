package it.unibo.donkeykong.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Static class ResourceFuncUtilities, manages utilities function for resources.
 */
public final class ResourceFuncUtilities {

    private static final Logger LOGGER = Logger.getLogger(AudioUtilities.class.getName());

    private ResourceFuncUtilities() {
    }

    /**
     * Get the image from the file name.
     * 
     * @param fileName name of the file.
     * @return the image.
     */
    public static Image getSources(final String fileName) {
        return new ImageIcon("src/main/resources/" + fileName + ".png").getImage();
    }

    /**
     * Get the buffered image from the file name.
     * 
     * @param fileName name of the file.
     * @return the buffered image.
     */
    public static BufferedImage getBufferedSources(final String fileName) {
        final File file = new File("src/main/resources/" + fileName + ".png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return img;
    }

    /**
     * Get the sources in alternative way.
     * 
     * @param fileName name of the file.
     * @return the buffered image.
     */
    public static BufferedImage loadSources(final String fileName) {
        BufferedImage img = null;
        try (InputStream s = ResourceFuncUtilities.class.getResourceAsStream("/" + fileName + ".png")) {
            img = ImageIO.read(s);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return img;
    }
}
