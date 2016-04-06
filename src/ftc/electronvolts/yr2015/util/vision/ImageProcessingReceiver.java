package ftc.electronvolts.yr2015.util.vision;

/**
 * Created by zhivago on 3/12/16.
 * FTC Team 7393
 */
public class ImageProcessingReceiver implements ImageResultReceiver {
    private ImageResult result;

    @Override
    public void receive(ImageResult results) {
        this.result = results;
    }

    public boolean isReady() {
        return result != null;
    }

    public ImageResult getResult() {
        return result;
    }
}
