package filters;

import interfaces.PixelFilter;
import processing.DImage;

import javax.swing.*;
import java.util.Arrays;

public class Border implements PixelFilter {
    public Border(){
        this.border= Integer.parseInt(JOptionPane.showInputDialog("filters.Border Size"));
    }
    short color = 0;
    int border= 0;
    @Override
    public DImage processImage(DImage img) {
       short[][] pixels = img.getBWPixelGrid();
       short[][] output = new short[pixels.length+border*2][pixels[0].length+border*2];
       for (short[] row: output) {
            Arrays.fill(row, color);
       }
        for (int i = border; i < pixels.length+border; i++) {
            for (int j = border; j < pixels[i-border].length+border; j++) {
               output[i][j] = pixels[i-border][j-border];
            }
        }
        img.setPixels(output);
        return img;
    }
}
