import java.util.Arrays;

public class Upscaling implements PixelFilter{

    @Override
    public DImage processImage(DImage img) {
        var original = img.getBWPixelGrid();
        short[][] upscaled = new short[original.length*2][original[0].length*2];
        int a = 0;
        for (int i = 0; i < upscaled.length; i+=2,a++) {
            int b = 0;
            for (int j = 0; j < upscaled[i].length; j+=2,b++) {
                upscaled[i][j] = original[a][b];
            }
            b=0;
        }
        a = 0;
        for (int i = 1; i < upscaled.length; i+=2,a++) {
            int b= 0;
            for (int j = 1; j < upscaled[i].length; j+=2,b++) {
                upscaled[i][j] = getAverage(a,b,original);
                System.err.println(upscaled[i][j]);
            }
        }
        img.setPixels(upscaled);
        return img;
    }

    private short getAverage(int i, int j,short[][] upscaled) {
        int average = 0;
        average += getPixel(upscaled,i,j,1,0);
        average += getPixel(upscaled,i,j,-1,0);
        average += getPixel(upscaled,i,j,0,1);
        average += getPixel(upscaled,i,j,0,-1);
        return (short)(average/4);
    }

    private int getPixel(short[][] upscaled,int i,int j, int i_c, int jc) {
        try{
            return upscaled[i+i_c][j+jc];
        }catch (ArrayIndexOutOfBoundsException ignored){};
        return 0;
    }
}
