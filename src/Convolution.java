import java.util.Arrays;

public class Convolution implements PixelFilter {
    private final double[][] blurKernel = {{1.0 / 9, 1.0 / 9, 1.0 / 9}, {1.0 / 9, 1.0 / 9, 1.0 / 9}, {1.0 / 9, 1.0 / 9, 1.0 / 9}};
    private final double[][] outlineKernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    private final double[][] embossKernel = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
    private final double[][] gaussianBlur = {{0, 0, 1, 2, 1, 0, 0}, {0, 3, 13, 22, 13, 3, 0}, {1, 13, 59, 97, 59, 13, 1}, {2, 22, 97, 159, 97, 22, 2}, {1, 13, 5, 9, 97, 59, 13, 1}, {0, 3, 13, 22, 13, 3, 0}, {0, 0, 1, 2, 1, 0, 0}};
    private final double[][] Gx = {{-1, 0, +1}, {-2, 0, +2}, {-1, 0, +1}};
    private final double[][] Gy = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};

    private double[][] prewitt(int n) throws Exception {
        if (n % 2==0) throw new Exception("Even number N");
        double[][] prewitt = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(prewitt[i], -1);
        }
        prewitt[n / 2][n / 2] = n * n - 1;
        return prewitt;
    }

    private double[][] boxBlur(int n) throws Exception {
        if (n % 2 == 0) throw new Exception("Even number N");
        double[][] boxblur = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(boxblur[i], 1);
        }
        return boxblur;
    }
    @Override
    public DImage processImage(DImage img) throws Exception {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels;

        outputPixels = applyKernel(pixels,prewitt(7));
//        outputPixels = preComputedAverage(pixels, prewitt(4));
//        outputPixels = sobel(applyKernel(pixels, Gx), applyKernel(pixels, Gy));
        img.setPixels(outputPixels);
        return img;
    }

    public short[][] sobel(short[][] Gx, short[][] Gy) {
        short[][] out = new short[Gx.length][Gx[0].length];
        for (int i = 0; i < Gx.length; i++) {
            for (int j = 0; j < Gx[0].length; j++) {
                out[i][j] = (short) Math.sqrt((Gx[i][j] * Gx[i][j]) + (Gy[i][j] * Gy[i][j]));
            }
        }
        return out;
    }

    private double kernelWeight(double[][] kernel) {
        double sum = 0;
        for (double[] a : kernel) {
            for (double v : a) {
                sum += v;
            }
        }
        return sum;
    }

    private short[][] applyKernel(short[][] pixels, double[][] kernel) {
        double weight = kernelWeight(kernel);
        short[][] output = new short[pixels.length][pixels[0].length];
        for (int i = Math.floorDiv(kernel.length, 2); i < pixels.length - Math.floorDiv(kernel.length, 2); i++) {
            for (int j = Math.floorDiv(kernel.length, 2); j < pixels[i].length - Math.floorDiv(kernel.length, 2); j++) {
                int sum = getSum(pixels, output, i, j, kernel);
                if (weight == 0) weight = 1;
                if (weight != 1) sum /= weight;
                output[i][j] = (short) (sum);
                if (output[i][j] < 0) output[i][j] = 0;
                else if (output[i][j] > 255) output[i][j] = 255;
            }
        }
        return output;
    }

    private int getSum(short[][] pixels, short[][] output, int i, int j, double[][] Kernel) {
        int sum = 0;
        int dist = Math.floorDiv(Kernel.length, 2);
        for (int k = -dist; k <= dist; k++) {
            for (int l = -dist; l <= dist; l++) {
                if (at(output, i + k, j + l)) sum += ((pixels[i + k][j + l]) * Kernel[k + dist][l + dist]);
            }
        }
        return sum;
    }

    private boolean at(short[][] output, int i, int j) {
        return i >= 0 && j >= 0 && i < output.length && j < output[0].length;
    }


}
