package filters;

import interfaces.PixelFilter;
import processing.DImage;
import processing.core.PApplet;

public class BasicColorFilter implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        // Do stuff with color channels here

        img.setColorChannels(red, green, blue);
        return img;
    }
}

