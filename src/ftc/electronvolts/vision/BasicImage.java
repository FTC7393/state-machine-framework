package ftc.electronvolts.vision;

import android.graphics.Color;
import android.graphics.YuvImage;

/**
 * Created by zhivago on 3/5/16.
 * FTC Team 7393
 */
public class BasicImage implements HSVImage {
    private final byte[] yuvBytes;
    private final int imageHeight, imageWidth;
    private float[][][] data;

    /**
     * Use a YuvImage object as the source image
     * @param image the YuvImage object
     */
    public BasicImage(YuvImage image) {
        yuvBytes = image.getYuvData();
        imageHeight = image.getHeight();
        imageWidth = image.getWidth();
        data = new float[sizeY()][sizeX()][3];
        for (int j = 0; j < sizeY() - 1; j++) {
            for (int i = 0; i < sizeX() - 1; i++) {
                data[j][i] = computeHSV(i, j);
            }
        }
    }

//    public BasicImage(YuvImage image, double percentCropTop, double percentCropBottom, double percentCropLeft, double percentCropRight) {
//        // Note: DOES NOT CROP yuvBytes
//        yuvBytes = image.getYuvData();
//        int pixelsCropTop = (int) ((percentCropTop * .01) * image.getHeight());
//        int pixelsCropBottom = (int) ((percentCropBottom * .01) * image.getHeight());
//        int pixelsCropLeft = (int) ((percentCropLeft * .01) * image.getWidth());
//        int pixelscropRight = (int) ((percentCropRight * .01) * image.getWidth());
//        imageHeight = image.getHeight() - pixelsCropTop - pixelsCropBottom;
//        imageWidth = image.getWidth() - pixelsCropLeft - pixelscropRight;
//
//        int maxSearchWidth = pixelsCropLeft + imageWidth;
//        int maxSearchHeight = pixelsCropTop + imageHeight;
//
//        data = new float[sizeY()][sizeX()][3];
//        for (int j = pixelsCropTop; j < maxSearchHeight - 1; j++) {
//            for (int i = pixelsCropLeft; i < maxSearchWidth - 1; i++) {
//                data[j][i] = computeHSV(i, j);
//            }
//        }
//    }

    /**
     * Use a bytestream as the source image. Height and width must be provided!
     * @param yuvBytes source bytestream
     * @param imageHeight source image height
     * @param imageWidth source image width
     */
    public BasicImage(byte[] yuvBytes, int imageHeight, int imageWidth) {
        this.yuvBytes = yuvBytes;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        data = new float[sizeY()][sizeX()][3];
    }

    public int getRealY(int realX, int realY) {
        int yByteNumber = (realY * imageWidth * 2) + (2 * realX);
        return (yuvBytes[yByteNumber] & 0xff) ;
    }

    public int getRealU(int realX, int realY) {
        int uByteNumber = (int) ((imageHeight * imageWidth) + (realY * imageWidth) + (2 * realX)) + 1;
        return (yuvBytes[uByteNumber] & 0xff) - 128;
    }

    public int getRealV(int realX, int realY) {
        int vByteNumber = (int) ((imageHeight * imageWidth) + (realY * imageWidth) + (2 * realX));
        return (yuvBytes[vByteNumber] & 0xff) - 128;
    }

    /**
     * Get YUV value at specific x and y, using only top left corner (for y values)
     * @param realX x value of the scaled image
     * @param realY y value of the scaled image
     * @return size 3 array with y value in index 1, u value in index 2 and v value in index 3
     */
    public int[] getRealYUV(int realX, int realY) {
        int[] yuv = new int[3];
        yuv[0] = getRealY(realX, realY);
        yuv[1] = getRealU(realX, realY);
        yuv[2] = getRealV(realX, realY);
        return yuv;
    }

    @Override
    public int sizeX() {
        return imageWidth / 2;
    }

    @Override
    public int sizeY() {
        return imageHeight / 2;
    }

    @Override
    public float[] getHSV(int realX, int realY) {
        return data[realY][realX];
    }

    private float[] computeHSV(int realX, int realY) {
        int[] yuv = getRealYUV(realX, realY);
        int y = yuv[0];
        int u = yuv[1];
        int v = yuv[2];
        int r = y + (int) 1.402f * v;
        int g = y - (int)(0.344f*u +0.714f*v);
        int b = y + (int) 1.772f * u;
        r = r > 255 ? 255 : r < 0 ? 0 : r;
        g = g > 255 ? 255 : g < 0 ? 0 : g;
        b = b > 255 ? 255 : b < 0 ? 0 : b;

        float [] hsv = new float[3];
        int alpha = 0xff;
        Color.colorToHSV(Color.argb(alpha, r, g, b), hsv);
        return hsv;
    }

    @Override
    public float[][][] getData() {
        return data;
    }
}
