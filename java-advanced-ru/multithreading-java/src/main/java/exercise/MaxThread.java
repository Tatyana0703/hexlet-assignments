package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private final int[] arr;
    private int maxValue;

    public MaxThread(int[] arr) {
        this.arr = arr;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void run() {
        maxValue = Arrays.stream(arr)
                .max()
                .getAsInt();
    }
}
// END
