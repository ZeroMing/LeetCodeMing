package org.ming.thread.zeroevenodd;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
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
public class ZeroEvenOdd5 {


    public static void main(String[] args) throws InterruptedException {


        ZeroEvenOdd5 zeroEvenOdd = new ZeroEvenOdd5(5);
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

    private AtomicInteger ai = new AtomicInteger(0);
    volatile int state = 0;//0 打印 0 ， 1 打印奇数， 2 打印偶数
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public ZeroEvenOdd5(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // 不等于0且不等于2，让出时间片
            while (ai.get() != 0 && ai.get() != 2) {
                Thread.yield();
            }
            // 等于0或者等于2，输出为0
            printNumber.accept(0);
            ai.incrementAndGet();
        }
    }

    /**
     * 偶数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            // 不等于3，让出时间片
            while (ai.get() != 3) {
                Thread.yield();
            }
            printNumber.accept(i);
            // 设置为0
            ai.set(0);
        }
    }

    /**
     * 奇数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            // 不等于1,让出时间片
            while (ai.get() != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            // 设置为2
            ai.set(2);
        }
    }

}
