package com.lx.concurrency.example.atomic;

import com.lx.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * projectName  concurrency
 * package com.lx.concurrency.example.atomic
 *
 * @author xingli12
 * description AtomicIntegerFieldUpdater
 * @version v1
 * @date Created in 2018-09-22 17:32
 * modified By
 * updateDate
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.count);
        }
        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        }else {
            log.info("update failed ,{}",example5.getCount());
        }
    }
}
