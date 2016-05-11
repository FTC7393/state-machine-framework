package ftc.electronvolts.vision;

import android.hardware.Camera;

/**
 * Created by zhivago on 3/14/16.
 * FTC Team 7393
 */
public interface RequiredCameraMethods {
    void initPreview(Camera camera, CameraWrapper context, Camera.PreviewCallback previewCallback);

    void removePreview(CameraWrapper context);
}
