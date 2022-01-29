package filters;

import interfaces.PixelFilter;
import processing.DImage;

public class BetterDownsampling implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        final short originalimage[][] = img.getBWPixelGrid();
        short[][] average = new short[(originalimage.length)][(originalimage[0].length)];
        for (int i = 0; i < originalimage.length-2; i+=2) {
            for (int j = 0; j < originalimage[i].length-2; j+=2) {
                int amt = 0;
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        amt+=originalimage[i+k][j+l];
                    }
                }
                average[i/2][j/2]= (short) (amt/4);
            }
        }
        img.setPixels(average);
        return img;
    }
}
