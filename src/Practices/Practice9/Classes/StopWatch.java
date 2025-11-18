package Practices.Practice9.Classes;

public class StopWatch {
    private long startTime;
    private long endTime;
    private boolean isRunning;
    private long accumulatedTime; // Накопленное время при паузах

    public StopWatch() {
        reset();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            endTime = System.currentTimeMillis();
            accumulatedTime += (endTime - startTime);
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        accumulatedTime = 0;
        isRunning = false;
    }

    public long getElapsedTime() {
        if (isRunning) {
            return accumulatedTime + (System.currentTimeMillis() - startTime);
        } else {
            return accumulatedTime;
        }
    }

    public long getAccumulatedTime() {
        return accumulatedTime;
    }
}