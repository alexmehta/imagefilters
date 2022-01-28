import java.util.ArrayList;
import java.util.Collections;

public class Six implements PixelFilter {
    private static class ColorGrid {
        short[][] red;
        short[][] green;

        public ColorGrid(short[][] red, short[][] green, short[][] blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        short[][] blue;
    }

    @Override
    public DImage processImage(DImage img) {
        final var red = img.getRedChannel();
        final var green = img.getGreenChannel();
        final var blue = img.getBlueChannel();
        ArrayList<ColorGrid> grids = new ArrayList<>();
        int height = img.getHeight() / 3;
        int width = img.getWidth() / 3;
        for (int r= 0; r < red.length-height; r += height) {
            for (int c = 0; c < red[r].length-width-1; c += width) {
                short[][] redn = new short[height+1][width+1];
                short[][] greenn= new short[height][width];
                short[][] bluen= new short[height][width];
                int row =0;
                int col = 0;
                for (int i = r; i < r+height ; i++,row++) {
                    for (int j = c; j < c + width; j++,col++) {
                        redn[row][col]= red[i][j];
                        greenn[row][col]= green[i][j];
                        bluen[row][col]= blue[i][j];
                    }
                    col = 0;
                }
                grids.add(new ColorGrid(redn,greenn,bluen));
            }
        }
        Collections.shuffle(grids);
        short[][] newred = img.getRedChannel();
        short[][] newblue = img.getBlueChannel();
        short[][] newgreen = img.getGreenChannel();
        int x = 0;
        int y = 0;
        for (int i = 0; i < grids.size(); i++) {
            var rgb = grids.get(i);
            for (int i1 = 0; i1 < rgb.red.length; i1++) {
                for (int i2 = 0; i2 < red[i1].length; i2++) {

                }
            }



        }

        img.setColorChannels(newred,newgreen,newblue);
        return img;
    }
}
