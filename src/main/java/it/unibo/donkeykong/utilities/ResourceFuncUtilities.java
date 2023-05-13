package it.unibo.donkeykong.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
    public static BufferedImage getSources(final String fileName){
        BufferedImage img = null;
        InputStream s = ResourceFuncUtilities.class.getResourceAsStream("src/main/res/" + fileName);
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
