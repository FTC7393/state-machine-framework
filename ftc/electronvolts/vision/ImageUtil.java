package ftc.electronvolts.vision;

/**
 * Created by zhivago on 3/9/16.
 * FTC Team 7393
 */
public class ImageUtil {
    /**
     * Create a bitmap of pixels in an image that contain a certain value
     * @param img the source HSVImage
     * @param hCenter the center hue range to search for
     * @param hRange the width of the hue area
     * @param sMin the minimum saturation
     * @param sMax the maximum saturation
     * @param vMin the minimum value
     * @param vMax the maximum value
     * @return a binary image where 1 represents a pixel that met the threshold requirements
     */
    public static BinaryImage threshold(final HSVImage img, float hCenter, float hRange, float sMin, float sMax, float vMin, float vMax) {
        int sizeX = img.sizeX();
        int sizeY = img.sizeY();
        float[][][] imgData = img.getData();
        int[][] data = new int[img.sizeY()][img.sizeX()];
        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                float[] hsv = imgData[j][i];

                if (hsv[2] > vMin && hsv[2] < vMax) {
                    if (hsv[1] > sMin && hsv[1] < sMax) {
                        if (hsv[0] > (hCenter - hRange) && hsv[0] < (hCenter + hRange)) {
                            data[j][i] = 1;
                        } else if (hsv[0] > (hCenter - 1) && hsv[0] < (hCenter - 1 + hRange)) {
                            data[j][i] = 1;
                        } else if (hsv[0] > (hCenter + 1 - hRange) && hsv[0] < (hCenter + 1)) {
                            data[j][i] = 1;
                        } else {
                            data[j][i] = 0;
                        }
                    } else {
                        data[j][i] = 0;
                    }
                } else {
                    data[j][i] = 0;
                }
            }
        }

        return new PrebuiltBinaryImage(data);
    }


    public static BinaryImage erode(final BinaryImage img, int iterations) {
        int[][] data = img.getData();

        for (int cycle = 0; cycle < iterations; cycle++) {
            for (int j = 0; j < img.sizeY(); j++) {
                for (int i = 0; i < img.sizeX(); i++) {
                    if (data[j][i] == 1) {
                        if (i > 0) {
                            if (data[j][i - 1] == 0) {
                                data[j][i] = 0;
                                continue;
                            }
                        } else if (i < img.sizeX()) {
                            if (data[j][i + 1] == 0) {
                                data[j][i] = 0;
                                continue;
                            }
                        } else if (j > 0) {
                            if (data[j - 1][i] == 0) {
                                data[j][i] = 0;
                                continue;
                            }
                        } else if (i < img.sizeY()) {
                            if (data[j + 1][i] == 0) {
                                data[j][i] = 0;
                                continue;
                            }
                        }
                    } else if (img.getValue(i, j) == 2) {
                        data[j][i] = 0;
                    }
                }
            }
        }

        for (int j = 0; j < img.sizeY(); j++) {
            for (int i = 0; i < img.sizeX(); i++) {
                if (data[j][i] == 2) {
                    data[j][i] = 0;
                }
            }
        }

        return new PrebuiltBinaryImage(data);
    }

    public static BinaryImage dilate(final BinaryImage img, int iterations) {
        int[][] data = img.getData();

        for (int cycle = 0; cycle < iterations; cycle++) {
            for (int j = 0; j < img.sizeY(); j++) {
                for (int i = 0; i < img.sizeX(); i++) {
                    if (data[j][i] == 1) {
                        if (i > 0) {
                            if (data[j][i - 1] == 1) {
                                data[j][i] = 1;
                                continue;
                            }
                        } else if (i < img.sizeX()) {
                            if (data[j][i + 1] == 1) {
                                data[j][i] = 1;
                                continue;
                            }
                        } else if (j > 0) {
                            if (data[j - 1][i] == 1) {
                                data[j][i] = 1;
                                continue;
                            }
                        } else if (i < img.sizeY()) {
                            if (data[j + 1][i] == 1) {
                                data[j][i] = 1;
                                continue;
                            }
                        }
                    } else if (img.getValue(i, j) == 2) {
                        data[j][i] = 1;
                    }
                }
            }
        }

        for (int j = 0; j < img.sizeY(); j++) {
            for (int i = 0; i < img.sizeX(); i++) {
                if (data[j][i] == 2) {
                    data[j][i] = 1;
                }
            }
        }

        return new PrebuiltBinaryImage(data);
    }

    public static int[] columnSum(BinaryImage img) {
        int[] data = new int[img.sizeY()];
        int[][] imgData = img.getData();
        for (int j = 0; j < img.sizeY(); j++) {
            for (int i = 0; i < img.sizeX(); i++) {
                data[j] += imgData[j][i];
            }
        }
        return data;
    }

    public static int[] columnSum(BinaryImage img, float x1f, float x2f) {
        int[] data = new int[img.sizeY()];
        int[][] imgData = img.getData();

        int x1 = (int) (img.sizeY() * x1f);
        int x2 = (int) (img.sizeY() * x2f);
        for (int j = 0; j < img.sizeY(); j++) {
            for (int i = x1; i < x2; i++) {
                data[j] += imgData[j][i];
            }
        }
        return data;
    }

    public static class PrebuiltBinaryImage implements BinaryImage {
        int[][] data;

        /**
         * Create a new binary image off of a source 2D int array
         * @param data the source 2D int array
         */
        public PrebuiltBinaryImage(int[][] data) {
            this.data = data;
        }

        @Override
        public int sizeX() {
            return data[0].length;
        }

        @Override
        public int sizeY() {
            return data.length;
        }

        @Override
        public int getValue(int x, int y) {
            return data[y][x];
        }

        @Override
        public int[][] getData() {
            return data;
        }
    }
}
