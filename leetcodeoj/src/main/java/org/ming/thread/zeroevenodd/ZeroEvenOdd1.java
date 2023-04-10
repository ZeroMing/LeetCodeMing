package org.ming.thread.zeroevenodd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author liming53
 * @date 2023/1/29
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class ZeroEvenOdd1 {


    // 0
    // 1
    // 2
    // 3
    // 4

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd1 zeroEvenOdd = new ZeroEvenOdd1(3);
        // 010203
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(n -> System.out.println(n));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(n -> System.out.println(n));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(n -> System.out.println(n));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int n;
    private Semaphore semaphore0 = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);
    private Semaphore semaphoreOdd = new Semaphore(0);

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphore0.acquire();
            printNumber.accept(0);
            if ((i & 1) == 1) { //奇数
                semaphoreOdd.release();
            } else {
                semaphoreEven.release();
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
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) { // 偶数
                semaphoreEven.acquire();
                printNumber.accept(i);
                semaphore0.release();
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
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) { //奇数
                semaphoreOdd.acquire();
                printNumber.accept(i);
                semaphore0.release();
            }
        }
    }

}
