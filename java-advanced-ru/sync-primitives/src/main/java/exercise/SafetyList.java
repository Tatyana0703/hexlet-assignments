package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

class SafetyList {
    private static final Logger LOGGER = Logger.getLogger("SafetyList");

    // BEGIN
    private int capacity = 10;
    private int[] arr;
    private int size = 0;

    public SafetyList() {
        this.arr = new int[this.capacity];
    }

    public SafetyList(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public int get(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Некорректное значение индекса: " + index);
        return arr[index];
    }

    public synchronized void add(int element) {
        ensureCapacity();
        arr[size++] = element;
    }

    private void ensureCapacity() {
        if (size + 1 > capacity) {
            int[] oldData = new int[size];
            System.arraycopy(arr, 0, oldData, 0, size);
            LOGGER.info("oldData: " + Arrays.toString(oldData));

            capacity = (capacity * 3) / 2 + 1;
            arr = new int[capacity];
            System.arraycopy(oldData, 0, arr, 0, size);
            LOGGER.info("newData: " + Arrays.toString(arr));
        }
    }
    // END
}
