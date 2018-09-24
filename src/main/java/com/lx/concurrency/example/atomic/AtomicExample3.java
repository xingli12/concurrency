package com.lx.concurrency.example.atomic;

import com.lx.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * projectName  concurrency
 * package com.lx.concurrency.annoations
 *
 * @author xingli12
 * description Adder
 * LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，
 * 而在高并发的时候通过分散提高了性能。
 * 缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 *
 * --------------------
 * 本文来自 yao123long 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/yao123long/article/details/63683991?utm_source=copy
 *
 * @version v1
 * @date Created in 2018-09-22 16:13
 * modified By
 * updateDate
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    private static LongAdder count = new LongAdder();

    private static void add(){
        count.increment();
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
