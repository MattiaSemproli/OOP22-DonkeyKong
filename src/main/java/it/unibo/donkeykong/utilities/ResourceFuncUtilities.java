package it.unibo.donkeykong.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    public static BufferedImage getSources(final String fileName) {
        BufferedImage img = null;        
        try {
            img = ImageIO.read(new FileInputStream(new File("src/main/res/" + fileName)));
        } catch (IOException e) {
            throw new IllegalStateException("Error while reading the file: " + fileName);
        }
        return img;
    }
}
