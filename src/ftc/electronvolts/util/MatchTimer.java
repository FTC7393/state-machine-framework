package ftc.electronvolts.util;

/**
 * This file was made by the electronVolts, FTC team 7393
 *
 * A simple class for keeping track of elapsed time
 */
public class MatchTimer {
    private final long matchLengthMillis;
    private long startTime, previousTime, deltaTime, now;
    private boolean hasStopped = false;

    /**
     * The match-timer needs to know how long the match will last.
     *
     * @param matchLengthMillis the length of the match. If negative, the match continues indefinitely
     */
    public MatchTimer(long matchLengthMillis) {
        this.matchLengthMillis = matchLengthMillis;
    }

    /**
     * Start the match timer. Should be called in init method of project.
     */
    public void start() {
        startTime = System.currentTimeMillis();
        previousTime = startTime;
    }

    /**
     * Update the match timer. Must be called every loop.
     *
     * @return the time between the last call of update() and now
     */
    public long update() {
        now = System.currentTimeMillis();
        deltaTime = now - previousTime;
        previousTime = now;
        return deltaTime;
    }

    /**
     * Get the time since the last update.
     *
     * @return time since last update
     */
    public long getDeltaTime() {
        return deltaTime;
    }

    /**
     * Get the total time that the match has been going
     *
     * @return total time that the match has been going
     */
    public long getElapsedTime() {
        return now - startTime;
    }

    /**
     * Get the time left in a match
     *
     * @return the time left in a match
     */
    public long getTimeLeft() {
        return matchLengthMillis - (now - startTime);
    }

    /**
     * Return if the match is over
     *
     * @return returns true if the match is over
     */
    public boolean isMatchOver() {
        return matchLengthMillis >= 0 && now - startTime >= matchLengthMillis;
    }

    /**
     * @return if this is the first loop that the match has been over
     */
    public boolean isMatchJustOver() {
        if (matchLengthMillis >= 0 && now - startTime >= matchLengthMillis && !hasStopped) {
            hasStopped = true;
            return true;
        }
        return false;
    }
}