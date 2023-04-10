package org.ming.thread.zeroevenodd;

import java.util.function.IntConsumer;

/**
 * Synchronized 和 notify
 *
 * @author liming53
 * @date 2023/1/29
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class ZeroEvenOdd7 {


    public static void main(String[] args) throws InterruptedException {


        ZeroEvenOdd7 zeroEvenOdd = new ZeroEvenOdd7(10);
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

    volatile int state = 0; //0 打印 0 ， 1 打印奇数， 2 打印偶数
    private volatile boolean control = true;
    private int n;
    private Object lock = new Object();

    public ZeroEvenOdd7(int n) {
        this.n = n;
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized (lock){
            for (int i = 0; i < n; i++) {
                while (state != 0) {
                    lock.wait();
                }
                printNumber.accept(0);
                if (control) {
                    state = 1;
                } else {
                    state = 2;
                }
                lock.notifyAll();
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
        synchronized (lock){
            for (int i = 2; i <= n; i += 2) {
                while (state != 2) {//当state不为2的时候，为就绪状态
                    lock.wait();
                }
                printNumber.accept(i);
                control = true;
                state = 0;
                lock.notify();
            }
        }

    }

    /**
     * 奇数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        synchronized (lock){
            for (int i = 1; i <= n; i += 2) {
                while (state != 1) {
                    lock.wait();
                }
                printNumber.accept(i);
                control = false;
                state = 0;
                lock.notify();
            }
        }

    }

}
