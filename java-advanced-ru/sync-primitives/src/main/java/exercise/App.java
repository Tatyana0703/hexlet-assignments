package exercise;

import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("App");
/*
    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList(3);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(9);
        LOGGER.info("list.get(0) : " + list.get(0));
        LOGGER.info("list.get(1) : " + list.get(1));
        LOGGER.info("list.get(2) : " + list.get(2));
        LOGGER.info("list.get(3) : " + list.get(3));
        LOGGER.info("list.getSize() : " + list.getSize());
        // END
    }
 */
    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        SafetyList list = new SafetyList();
        Thread thread = new Thread(new ListThread(list));
        thread.start();
        // Работает примерно 1 секунду (1000 элементов * 1 мс)
        // Дожидаемся его окончания
        thread.join();
        LOGGER.info("list.getSize() : " + list.getSize());
        // END
    }
}

