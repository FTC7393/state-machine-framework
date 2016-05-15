package ftc.electronvolts.vision;

/**
 * Created by zhivago on 3/12/16.
 * FTC Team 7393
 */
public class ImageProcessingRunnable implements Runnable {
    private final ImageResultReceiver receiver;
    private CameraWrapper cameraWrapper;
    private RequiredCameraMethods ref;

    public ImageProcessingRunnable(RequiredCameraMethods ref, ImageResultReceiver receiver) {
        this.receiver = receiver;
        this.ref = ref;
    }

    @Override
    public void run() {
        cameraWrapper = new CameraWrapper(ref);
        while (!cameraWrapper.imageReady()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
        BasicImage image = new BasicImage(cameraWrapper.yuvImage/*, 10, 10, 0 , 0*/);
        cameraWrapper.stopCamera();
        BinaryImage redMaskedImage = ImageUtil.threshold(image, 342f, 36f, .2f, 1f, .3f, 1f);
        BinaryImage blueMaskedImage = ImageUtil.threshold(image, 200f, 50f, .2f, 1f, .3f, 1f);

        int[] redColumnData = ImageUtil.columnSum(redMaskedImage, .2f, .8f);
        int[] blueColumnData = ImageUtil.columnSum(blueMaskedImage, .2f, .8f);

        int rNumberOfPoints = 0;
        int rTotal = 0;
        for (int i = 0; i < redColumnData.length; i++) {
            rNumberOfPoints += redColumnData[i];
            rTotal += redColumnData[i]*(i + 1);
        }

        int bNumberOfPoints = 0;
        int bTotal = 0;
        for (int i = 0; i < blueColumnData.length; i++) {
            bNumberOfPoints += blueColumnData[i];
            bTotal += blueColumnData[i]*(i + 1);
        }

        ImageResult result = ImageResult.BLUE_ON_LEFT;

        if (rTotal == 0 || bTotal == 0) {
            result = ImageResult.INCONCLUSIVE;
        } else {
            int redPosition = rTotal / rNumberOfPoints;
            int bluePosition = bTotal / bNumberOfPoints;

            if (bluePosition - redPosition < 0) { // camera is mirrored, so use opposite value
                result = ImageResult.RED_ON_LEFT;
            }
        }

        receiver.receive(result);

        LogVision.writeBasicImage(image);
        LogVision.writeBinaryImage(redMaskedImage, blueMaskedImage);
        LogVision.writeColumnSum(redColumnData, blueColumnData);
    }
}
