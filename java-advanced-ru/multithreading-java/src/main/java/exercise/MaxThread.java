package exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN
class MaxThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(MaxThread.class.getName());

    private int[] array;
    private int maxValue = Integer.MIN_VALUE;

    MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        LOGGER.setLevel(Level.INFO);
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        LOGGER.info("Thread " + getName() + " finished");
    }

    public int getMaxValue() {
        return maxValue;
    }
}
// END
