public class LeftHalf implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels = new short[pixels.length][pixels.length/2];
        for (int i = 0; i < outputPixels.length; i++) {
            for (int j = 0; j < outputPixels[i].length; j++) {
               outputPixels[i][j]=  pixels[i][j];
            }
        }
        img.setPixels(outputPixels);

        return img;
    }
}

