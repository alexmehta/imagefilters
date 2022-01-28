import javax.swing.*;

public class ArbUpscaling implements PixelFilter{
    public ArbUpscaling(){
        multiplier = Integer.parseInt(JOptionPane.showInputDialog("Input value"));

    }
    int multiplier;
    @Override
    public DImage processImage(DImage img) {
        var original = img.getBWPixelGrid();
        short[][] upscaled = new short[original.length*multiplier][original[0].length*multiplier];
        int a = 0;
        for (int i = 0; i < upscaled.length; i+=multiplier,a++) {
            int b = 0;
            for (int j = 0; j < upscaled[i].length; j+=multiplier,b++) {
                upscaled[i][j] = original[a][b];
            }
            b=0;
        }
        a = 0;
        for (int i = 1; i < upscaled.length; i+=multiplier,a++) {
            int b= 0;
            for (int j = 1; j < upscaled[i].length; j+=multiplier,b++) {
//                upscaled[i][j] = getAverage(a,b,original);
                System.err.println(upscaled[i][j]);
            }
        }
        img.setPixels(upscaled);
        return img;
    }

//    private short getAverage(int i, int j,short[][] original) {
       int inbetween = multiplier;
//       int slope = calcSlope(original[i][j],original[i][j+1]);
//    }

    private int getPixel(short[][] upscaled,int i,int j, int i_c, int jc) {
        try{
            return upscaled[i+i_c][j+jc];
        }catch (ArrayIndexOutOfBoundsException ignored){};
        return 0;
    }
}
