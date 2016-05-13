package ftc.electronvolts.util;

import java.util.List;

/**
 * A factory class for DeadZone
 */
public class DeadZones {
    /**
     * @param deadZoneList the list of DeadZones to be combined
     * @return the composite DeadZone
     */
    public static DeadZone any(final List<DeadZone> deadZoneList){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                for(DeadZone deadZone : deadZoneList){
                    if(deadZone.isInside(value)){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * @param deadZoneList the list of DeadZones to be combined
     * @return the composite DeadZone
     */
    public static DeadZone all(final List<DeadZone> deadZoneList){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                for(DeadZone deadZone : deadZoneList){
                    if(!deadZone.isInside(value)){
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * inverts a DeadZone
     * @param deadZone the DeadZone to invert
     * @return the resulting DeadZone
     */
    public static DeadZone not(final DeadZone deadZone){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return !deadZone.isInside(value);
            }
        };
    }

    /**
     * a DeadZone that is empty
     * @return the DeadZone
     */
    public static DeadZone noDeadZone(){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return false;
            }
        };
    }

    /**
     * A DeadZone between min and max
     * @param min the lower edge of the DeadZone
     * @param max the upper edge of the DeadZone
     * @return the DeadZone
     */
    public static DeadZone minMaxDeadzone(final double min, final double max){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return (value >= min && value <= max);
            }
        };
    }

    /**
     * deadzone that ranges from center-delta to center+delta
     * center is usually zero so that the deadzone is +/-delta
     * @param center the center of the deadzone
     * @param delta the delta on either side of the deadzone
     * @return the DeadZone
     */
    public static DeadZone deltaDeadZone(double center, double delta){
        double min = center - delta;
        double max = center + delta;
        return minMaxDeadzone(min, max);
    }
}
