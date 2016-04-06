# Vision Processing Requirements
In order for vision processing to work, we need to add several lines in other parts of the project that we cannot package with the library.

- Replace _src/main/java/com/qualcomm/ftcrobotcontroller/FtcRobotControllerActivity.java_ our copy (located in the root of the project). This allows us to easily access the camera methods.
- Add the camera permission to the manifest:
```xml
<uses-permission android:name="android.permission.CAMERA"/>
<uses-feature android:name="android.hardware.camera"
    android:required="true"/>
```
- Add the frame layout (required again for accessing the camera) to the layout file (located in _src/main/res/layout/_):
```xml
<FrameLayout
    android:layout_width="100dp"
    android:layout_height="125dp"
    android:id="@+id/previewLayout"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true">
</FrameLayout>
```

Vision should now work properly!
