import javax.swing.*;

public class ColorMasking implements PixelFilter{
    final double threshold =44.1672956;
    int r;
    int g;
    int b ;
    public ColorMasking(){
        String r = JOptionPane.showInputDialog("Red");
        String g = JOptionPane.showInputDialog("Green");
        String b = JOptionPane.showInputDialog("Blue");
        this.r = Integer.parseInt(r);
        this.g = Integer.parseInt(g);
        this.b = Integer.parseInt(b);
    }
    private double distance(int r,int g, int b, int tarR, int tarG, int tarB){
        return Math.sqrt(Math.abs(r-tarR)*Math.abs(r-tarR) + Math.abs(g-tarG)* Math.abs(g-tarG) + Math.abs(tarB-b)*Math.abs(tarB-b));
    }
    @Override
    public DImage processImage(DImage img) {
        var red = img.getRedChannel();
        var green = img.getGreenChannel();
        var blue = img.getBlueChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                if (distance(r,g,b,red[i][j],green[i][j],blue[i][j])<=threshold){
                    red[i][j]=255;
                    green[i][j] = 255;
                    blue[i][j] = 255;
                }else{
                    red[i][j]=0;
                    green[i][j] = 0;
                    blue[i][j] = 0;
                }
            }
        }
        img.setColorChannels(red,green,blue);
        return img;

    }
}
