package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * This is a class that stores which team you are on.
 */
public enum TeamColor {
    RED,
    BLUE,
    UNKNOWN;
    
    public static TeamColor fromString(String s){
    	s = s.toUpperCase();
    	if (s.contains("BLUE"))	return BLUE;
    	if (s.contains("RED"))	return RED;
    	if (s.contains("B"))	return BLUE;
    	if (s.contains("R"))	return RED;
    	return UNKNOWN;
    }
}
