package CafeteriaClient.utils;

public class MultiThreadingUtil {
    public static void executeMultiThreaded(Runnable task) {
        Thread thread = new Thread(task);
        thread.start();
    }
}
