package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        LOGGER.setLevel(Level.INFO);
        MaxThread maxThread = new MaxThread(arr);
        MinThread minThread = new MinThread(arr);
        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");
        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");
        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Map.of("min", minThread.getMinValue(), "max", maxThread.getMaxValue());
    }
    // END
}
