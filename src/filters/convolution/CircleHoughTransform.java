package filters.convolution;

import processing.DImage;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.HashMap;

public class CircleHoughTransform extends Convolution {
    private static final double threshold = 0.4;
    private static final int steps = 100;
    ArrayList<Pair> points = new ArrayList<>();

    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        CannyOperator c = new CannyOperator();
        pixels = c.canny(pixels);
        pixels = applyCircle(pixels, 0, 1000);
        img.setPixels(pixels);
        return img;
    }

    private short[][] applyCircle(short[][] pixels, int minR, int maxR) {
        for (int i = minR; i <= maxR; i++) {
            for (int j = 0; j < steps; j++) {
                points.add(new Pair(i, (int) (i * Math.cos(2 * Math.PI * j / steps)), (int) (i * Math.sin(2 * Math.PI * j / steps))));
            }
        }
        HashMap<Pair,Integer>
        return pixels;
    }

    static class Pair {
        private int X;
        private int Y;
        private int Z;

        public Pair(int x, int y, int z) {
            X = x;
            Y = y;
            Z = z;
        }

        public Pair(int x, int y) {
            X = x;
            Y = y;
        }

        public int getX() {
            return X;
        }

        public void setX(int x) {
            X = x;
        }

        public int getY() {
            return Y;
        }

        public void setY(int y) {
            Y = y;
        }
    }
}
