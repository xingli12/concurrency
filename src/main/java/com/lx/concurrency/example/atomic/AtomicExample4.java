package com.lx.concurrency.example.atomic;

import com.lx.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * projectName  concurrency
 * package com.lx.concurrency.example.atomic
 *
 * @author xingli12
 * description AtomicReference
 * @version v1
 * @date Created in 2018-09-22 17:32
 * modified By
 * updateDate
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2); //结果 2
        count.compareAndSet(0,1); //no
        count.compareAndSet(1,3); //no
        count.compareAndSet(2,4); //4
        count.compareAndSet(3,5); //no
        log.info("count:{}",count.get());

    }
}
