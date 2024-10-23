package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) throws InterruptedException {
        MaxThread maxThread = new MaxThread(arr);
        MinThread minThread = new MinThread(arr);
        LOGGER.info("Thread " + maxThread.getClass().getSimpleName() + " started");
        maxThread.start();
        LOGGER.info("Thread " + minThread.getClass().getSimpleName() + " started");
        minThread.start();
        maxThread.join();
        LOGGER.info("Thread " + maxThread.getClass().getSimpleName() + " finished");
        minThread.join();
        LOGGER.info("Thread " + minThread.getClass().getSimpleName() + " finished");

        return Map.of("min", minThread.getMinValue(), "max", maxThread.getMaxValue());
    }
    // END
}
