package ftc.electronvolts.yr2015.util.vision;

/**
 * Created by zhivago on 3/9/16.
 * FTC Team 7393
 */
public interface HSVImage {
    int sizeX();
    int sizeY();
    float[] getHSV(int x, int y);
    float[][][] getData();
}
