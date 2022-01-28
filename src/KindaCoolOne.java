public class KindaCoolOne implements PixelFilter {

    @Override
    public DImage processImage(DImage img) {
        final short originalimage[][] = img.getBWPixelGrid();
        int[][] average = new int[(originalimage.length)][(originalimage[0].length)];
        int r = 0;
        int c = 0;
        for (int i = 0; i < originalimage.length-2; i+=2) {
            for (int j = 0; j < originalimage[i].length-2; j+=2) {
                int amt = 0;
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                      amt+=originalimage[i+k][j+l];
                    }
                }
                average[i/2][j/2]= amt/4;
            }
        }
        img.setPixels(average);
        return img;
    }
}
