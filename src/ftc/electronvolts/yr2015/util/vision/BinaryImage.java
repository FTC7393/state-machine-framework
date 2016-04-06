package ftc.electronvolts.yr2015.util.vision;

/**
 * Created by zhivago on 3/9/16.
 * FTC Team 7393
 */
public interface BinaryImage {
    int sizeX();
    int sizeY();
    int getValue(int x, int y);
    int[][] getData();
}
