package com.lx.concurrency.example.count;

import com.lx.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * projectName  concurrency
 * package com.lx.concurrency.annoations
 *
 * @author xingli12
 * description compareAndSwap CAS
 *     public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *         return var5;
 *     }
 *     拿当前值（工作内存）与底层值（主内存）进行对比，如果一样时才进行对应操作，否则继续取最新的值再跟底层内存值进行对比
 * AtomicInteger
 * (线程安全)
 * @version v1
 * @date Created in 2018-09-22 16:13
 * modified By
 * updateDate
 */
@Slf4j
@ThreadSafe
public class CountExample2 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    private static void add(){
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("exception",e);
        }
        executorService.shutdown();
        log.info("count:{}",count);

    }

}
