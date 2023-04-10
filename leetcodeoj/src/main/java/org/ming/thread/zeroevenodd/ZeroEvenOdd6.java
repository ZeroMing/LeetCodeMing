package org.ming.thread.zeroevenodd;

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
public class ZeroEvenOdd6 {


    public static void main(String[] args) throws InterruptedException {


        ZeroEvenOdd6 zeroEvenOdd = new ZeroEvenOdd6(5);
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

    volatile int state = 0;//0 打印 0 ， 1 打印奇数， 2 打印偶数
    private volatile boolean control = true;
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public ZeroEvenOdd6(int n) {
        this.n = n;
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            if (control) {
                state = 1;
            } else {
                state = 2;
            }
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
            while (state != 2) {//当state不为2的时候，为就绪状态
                Thread.yield();
            }
            printNumber.accept(i);
            control = true;
            state = 0;
        }
    }

    /**
     * 奇数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            control = false;
            state = 0;
        }
    }

}
