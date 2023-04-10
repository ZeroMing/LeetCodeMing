package org.ming.thread.zeroevenodd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * Synchronized 和 notify
 *
 * @author liming53
 * @date 2023/1/29
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class ZeroEvenOdd4 {


    public static void main(String[] args) throws InterruptedException {


        ZeroEvenOdd4 zeroEvenOdd = new ZeroEvenOdd4(3);
        // 010203
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(n -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(n -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(n -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private Map<String, Thread> map = new ConcurrentHashMap<>();
    volatile int state = 0;//0 打印 0 ， 1 打印奇数， 2 打印偶数
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public ZeroEvenOdd4(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        map.put("zero", Thread.currentThread());
        for (int i = 1; i <= n; i++) {
            while (state != 0){
                LockSupport.park();
            }
            printNumber.accept(0);
            if ((i & 1) == 0) {//偶数
                state = 2;
            } else {
                state = 1;
            }
            // 通知其他两个线程
            map.forEach((k,v) -> LockSupport.unpark(v));
        }
    }

    /**
     * 偶数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        map.put("even", Thread.currentThread());
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero"));    //通知zero线程
        }
    }

    /**
     * 奇数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        map.put("odd", Thread.currentThread());
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero"));
        }
    }

}
