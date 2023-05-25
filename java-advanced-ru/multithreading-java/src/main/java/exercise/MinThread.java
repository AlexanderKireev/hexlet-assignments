package exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN
class MinThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());

    private int[] array;
    private int minValue = Integer.MAX_VALUE;

    MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        LOGGER.setLevel(Level.INFO);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        LOGGER.info("Thread " + getName() + " finished");
    }

    public int getMinValue() {
        return minValue;
    }
}
// END
