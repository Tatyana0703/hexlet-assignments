package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private final int[] arr;
    private int minValue;

    public MinThread(int[] arr) {
        this.arr = arr;
    }

    public int getMinValue() {
        return minValue;
    }

    @Override
    public void run() {
        minValue = Arrays.stream(arr)
                .min()
                .getAsInt();
    }
}
// END
