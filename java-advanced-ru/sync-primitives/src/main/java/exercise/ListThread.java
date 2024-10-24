package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {

    public static final int ELEMENTS_COUNT = 30;

    // Разделяемый ресурс
    SafetyList resource;

    ListThread(SafetyList resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        new Random().ints(0,350).limit(ELEMENTS_COUNT).forEach( 
                v -> {
                    try {
                        Thread.sleep(1L);
                        resource.add(v);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
// END
