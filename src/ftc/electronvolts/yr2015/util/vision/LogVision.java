package ftc.electronvolts.yr2015.util.vision;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/**
 * Created by zhivago on 3/11/16.
 * FTC Team 7393
 */
public class LogVision {
    private static File dir = Environment.getExternalStorageDirectory();

    public static void setStorageDirectory(File dir) {
        LogVision.dir = dir;
    }

    public static void writeBinaryImage(BinaryImage image) {
        int sizeX = image.sizeX();
        int sizeY = image.sizeY();
        int size = sizeX * sizeY;

        int[][] imgData = image.getData();

        byte [] colorBits = new byte[size * 4];

        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                int offset = i + sizeX * j;

                int pixelValue = 255 * imgData[j][i];
                int r = pixelValue;
                int g = pixelValue;
                int b = pixelValue;
                r = r > 255 ? 255 : r < 0 ? 0 : r;
                b = b > 255 ? 255 : b < 0 ? 0 : b;

                colorBits[offset * 4] = (byte) r;
                colorBits[offset * 4 + 1] = (byte) g;
                colorBits[offset * 4 + 2] = (byte) b;
                colorBits[offset * 4 + 3] = (byte) 0xff; // the alpha.
            }
        }

        writeImageToFile(colorBits, sizeX, sizeY, "singleBinaryImage");
    }

    public static void writeBinaryImage(BinaryImage redImage, BinaryImage blueImage) {
        if (redImage.sizeX() != blueImage.sizeX() || redImage.sizeY() != blueImage.sizeY()) {
            return; // Mismatch!
        }

        int sizeX = redImage.sizeX();
        int sizeY = redImage.sizeY();
        int size = sizeX * sizeY;

        int[][] redImgData = redImage.getData();
        int[][] blueImgData = blueImage.getData();

        byte [] colorBits = new byte[size*4];

        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                // Note: the images are rotated, so the indices get
                // switched here to keep them from looking sideways.
//                int offset = i + sizeX * j;
                int offset = sizeY-1-j + sizeY * i;

                int r = 255 * redImgData[j][i];
                int g = 0;
                int b = 255 * blueImgData[j][i];
                r = r > 255 ? 255 : r < 0 ? 0 : r;
                b = b > 255 ? 255 : b < 0 ? 0 : b;

                colorBits[offset * 4] = (byte) r;
                colorBits[offset * 4 + 1] = (byte) g;
                colorBits[offset * 4 + 2] = (byte) b;
                colorBits[offset * 4 + 3] = (byte) 0xff; // the alpha.
            }
        }

        writeImageToFile(colorBits, sizeY, sizeX, "redBlueBinaryImage");
    }

    public static void writeBasicImage(BasicImage image) {
        int sizeX = image.sizeX();
        int sizeY = image.sizeY();
        int size = sizeX * sizeY;

        float[][][] imgData = image.getData();

        byte [] colorBits = new byte[size*4];

        for(int j = 0; j < sizeY; j++) {
            for(int i = 0; i < sizeX; i++) {
                // Note: the images are rotated, so the indices get
                // switched here to keep them from looking sideways.
//                int offset = i + sizeX * j;
                int offset = sizeY-1-j + sizeY * i;

                int[] yuv = image.getRealYUV(i, j);
                int y = yuv[0];
                int u = yuv[1];
                int v = yuv[2];
                int r = y + (int) 1.402f * v;
                int g = y - (int)(0.344f*u + 0.714f * v);
                int b = y + (int) 1.772f * u;
                r = r > 255 ? 255 : r < 0 ? 0 : r;
                g = g > 255 ? 255 : g < 0 ? 0 : g;
                b = b > 255 ? 255 : b < 0 ? 0 : b;

                colorBits[offset * 4] = (byte) r;
                colorBits[offset * 4 + 1] = (byte) g;
                colorBits[offset * 4 + 2] = (byte) b;
                colorBits[offset * 4 + 3] = (byte) 0xff; // the alpha.
            }
        }

        writeImageToFile(colorBits, sizeY, sizeX, "yuvImage");
    }

    private static void writeImageToFile(byte[] colorBits, int sizeX, int sizeY, String filePrefix) {
        Bitmap bitmapImage = Bitmap.createBitmap(sizeX, sizeY, Bitmap.Config.ARGB_8888);
        bitmapImage.copyPixelsFromBuffer(ByteBuffer.wrap(colorBits));

        FileOutputStream fos = null;
        try {
            File file = new File(dir, filePrefix + "_" + System.currentTimeMillis() + ".jpeg");
            fos = new FileOutputStream(file);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeColumnSum(int[] redColumnSum, int[] blueColumnSum) {
        if (redColumnSum.length != blueColumnSum.length) {
            return; // Mismatch!
        }

        PrintStream printStream = null;
        try {
            File file = new File(dir, "columnAverages_" + System.currentTimeMillis() + ".csv");
            printStream = new PrintStream(file);
            printStream.println("index,red,blue");
            for (int i = 0; i < redColumnSum.length; i++) {
                printStream.println(i + "," + redColumnSum[i] + "," + blueColumnSum[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }
}
