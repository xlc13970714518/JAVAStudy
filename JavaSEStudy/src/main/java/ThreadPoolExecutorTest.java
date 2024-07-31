import java.sql.SQLOutput;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
/*        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(threadNamePrefix + "-%d")
                .setDaemon(true).build();*/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(threadPoolExecutor.isTerminated());
                System.out.println("执行线程" + Thread.currentThread().getName());
            });
        }
    }
}
