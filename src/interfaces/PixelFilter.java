package interfaces;

import processing.DImage;
import processing.core.PApplet;

public interface PixelFilter {
    /***
     * apply a filter to an input image and return the filtered image as output
     * @param img the image to filter
     * @return the filtered image
     */
    public DImage processImage(DImage img) throws Exception;

}