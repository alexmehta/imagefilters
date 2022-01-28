import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleMaker implements PixelFilter {
    private final int N;
    public PuzzleMaker() {
        this.N = Integer.parseInt(JOptionPane.showInputDialog("Enter a size"));
    }
    @Override
    public DImage processImage(DImage img) {
        final var red = img.getRedChannel();
        final var green = img.getGreenChannel();
        final var blue = img.getBlueChannel();
        ArrayList<ColorGrid> grids = new ArrayList<>();
        int height = img.getHeight() / N;
        int width = img.getWidth() / N;
        addToGrid(red, grids, height, width);
        Collections.shuffle(grids);
        short[][] newred = new short[red.length][red[0].length];
        short[][] newblue = new short[red.length][red[0].length];
        short[][] newgreen = new short[red.length][red[0].length];
        setboxes(grids, height, width, newred, newblue, newgreen, red, green, blue);
        img.setColorChannels(newred, newblue, newgreen);
        return img;
    }

    private void setboxes(ArrayList<ColorGrid> grids, int height, int width, short[][] newred, short[][] newblue, short[][] newgreen, short[][] red, short[][] blue, short[][] green) {
        for (int i = 0; i < grids.size(); i++) {
            ColorGrid rgb = grids.get(i);
            processGrid(height, width, newred, newblue, newgreen, red, blue, green, i, rgb);
        }
    }

    private void processGrid(int height, int width, short[][] newred, short[][] newblue, short[][] newgreen, short[][] red, short[][] blue, short[][] green, int i, ColorGrid rgb) {
        for (int i1 = rgb.starth; i1 < rgb.height; i1++) {
            for (int i2 = rgb.startw; i2 < rgb.width; i2++) {
                int t = (i1 - rgb.starth) + (Math.floorDiv(i, N)) * height;
                int b = (i2 - rgb.startw) + ((i % N) * width);
                newred[t][b] = red[i1][i2];
                newgreen[t][b] = green[i1][i2];
                newblue[t][b] = blue[i1][i2];
            }
        }
    }

    private void addToGrid(short[][] red, ArrayList<ColorGrid> grids, int height, int width) {
        for (int r = 0; r <= red.length - height; r += height) {
            for (int c = 0; c <= red[r].length - width; c += width) {
                grids.add(new ColorGrid(height + r, width + c, r, c));
            }
        }
    }


    private static class ColorGrid {
        public int height;
        public int width;
        public int starth;
        public int startw;

        public ColorGrid(int height, int width, int starth, int startw) {
            this.height = height;
            this.width = width;
            this.starth = starth;
            this.startw = startw;
        }
    }
}
