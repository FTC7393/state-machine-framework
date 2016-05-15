package ftc.electronvolts.vision;

import android.graphics.ImageFormat;
import android.graphics.YuvImage;
import android.hardware.Camera;

public class CameraWrapper {
    public Camera camera;
    public CameraPreview preview;

    public int width;
    public int height;
    public YuvImage yuvImage = null;

    volatile private boolean imageReady = false;

    RequiredCameraMethods ref;

    private int looped = 0;
    private String data;
    private int ds = 2; // downsampling parameter

    public int getLooped() {
        return looped;
    }

    public Camera.PreviewCallback previewCallback;

    public boolean imageReady() {
        return imageReady;
    }

    public boolean isCameraAvailable() {
        return camera !=null;
    }

    public Camera openCamera(int cameraInfo) {
        Camera tempCamera = null;
        try {
            tempCamera = Camera.open(cameraInfo);
        } catch (Exception e) {

        }

        if (tempCamera == null) {
            tempCamera = Camera.open();
        }

        return tempCamera;
    }

    public CameraWrapper(RequiredCameraMethods ref) {
        camera = openCamera(Camera.CameraInfo.CAMERA_FACING_BACK);

        previewCallback = new Camera.PreviewCallback() {
            public void onPreviewFrame(byte[] data, Camera camera) {
                try {
                    Camera.Parameters parameters = camera.getParameters();
                    width = parameters.getPreviewSize().width;
                    height = parameters.getPreviewSize().height;
                    yuvImage = new YuvImage(data, ImageFormat.NV21, width, height, null);
                    imageReady = true;
                    looped += 1;
                } catch (Exception e) {

                }
            }
        };
        camera.setPreviewCallback(previewCallback);

        Camera.Parameters parameters = camera.getParameters();

        width = parameters.getPreviewSize().width / ds;
        height = parameters.getPreviewSize().height / ds;
        parameters.setPreviewSize(width, height);

        camera.setParameters(parameters);

        data = parameters.flatten();

        this.ref = ref;

        if (preview == null) {
            this.ref.initPreview(camera, this, previewCallback);
        }
    }

    public void stopCamera() {
        if (camera != null) {
            if (preview != null) {
                ref.removePreview(this);
                preview = null;
            }
            camera.stopPreview();
            camera.setPreviewCallback(null);
            if(camera != null) {
                camera.release();
            }
            camera = null;
        }
    }
}