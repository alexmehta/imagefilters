import processing.core.PApplet;

import javax.swing.*;

public class Monochrome implements PixelFilter {
    public Monochrome(){
        this.threshold = Integer.parseInt(JOptionPane.showInputDialog("Threshold"));
    }
    int threshold = 0;
    @Override
    public DImage processImage(DImage img) {
       short[][] pixels = img.getBWPixelGrid();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j]>=threshold){
                   pixels[i][j] = 255;
                }else{
                    pixels[i][j] = 0;
                }
            }
        }
        img.setPixels(pixels);
        return img;
    }

}
