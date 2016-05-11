package ftc.electronvolts.util;

import java.util.List;

/**
 * Created by vandejd1 on 1/13/16.
 * FTC Team EV 7393
 */
public class DeadZones {
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

    public static DeadZone not(final DeadZone deadZone){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return !deadZone.isInside(value);
            }
        };
    }

    public static DeadZone noDeadZone(){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return false;
            }
        };
    }

    public static DeadZone minMaxDeadzone(final double min, final double max){
        return new DeadZone() {
            @Override
            public boolean isInside(double value) {
                return (value >= min && value <= max);
            }
        };
    }

    //deadzone that ranges from center-delta to center+delta
    //center is usually zero so that the deadzone is +/-delta
    public static DeadZone deltaDeadZone(double center, double delta){
        double min = center - delta;
        double max = center + delta;
        return minMaxDeadzone(min, max);
    }
}
