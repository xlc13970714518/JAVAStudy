import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        //意味着最多允许clientCount个线程同时执行
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(() -> {
                try {
                    //调用semaphore.acquire()方法获取信号量。如果当前信号量的可用资源数量大于0，则会直接获取资源并继续执行；否则，线程将被阻塞，直到有可用资源为止。
                    semaphore.acquire();
                    //在获取资源后，打印出当前信号量的可用资源数量，即semaphore.availablePermits()。
                    System.out.println(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //release()方法释放了一个信号量
                    semaphore.release();
                }
            });
        }
        //调用executorService.shutdown()方法关闭线程池
        executorService.shutdown();
    }
}