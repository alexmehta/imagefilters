package filters;

import interfaces.PixelFilter;
import processing.DImage;

public class Tile implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        int width = (img.getWidth()/2)*3;
        int height = img.getHeight();
        DImage downsampled= downSample(img);
        short[][] r = downsampled.getRedChannel();
        short[][] g = downsampled.getGreenChannel();
        short[][] b = downsampled.getBlueChannel();
        short[][] or = new short[height][width];
        short[][] og = new short[height][width];
        short[][] ob = new short[height][width];
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
               or[i][j]= r[i%(img.getHeight()/2)][i%(img.getWidth()/3)];
                og[i][j]= g[i%(img.getHeight()/2)][i%(img.getWidth()/3)];
                ob[i][j]= b[i%(img.getHeight()/2)][i%(img.getWidth()/3)];
            }
        }
        DImage output = new DImage(width,height);
        output.setColorChannels(or,og,ob);
        return downsampled;
    }

    private DImage downSample(DImage img) {
        int width = img.getWidth()/2;
        int height = img.getHeight()/2;
       DImage d = new DImage(width,height);
        short[][] r = img.getRedChannel();
        short[][] g = img.getGreenChannel();
        short[][] b = img.getBlueChannel();
        short[][] or = new short[height][width];
        short[][] og = new short[height][width];
        short[][] ob = new short[height][width];
        for (int i = 0; i < r.length; i+=2) {
            for (int j = 0; j < r[0].length; j+=2){
               or[i/2][j/2] =  r[i][j];
               og[i/2][j/2] =  g[i][j];
               ob[i/2][j/2] =  b[i][j];
            }
        }
        d.setColorChannels(or,og,ob);
        return d;
    }
}
