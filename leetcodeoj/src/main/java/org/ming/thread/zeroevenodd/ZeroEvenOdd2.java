package org.ming.thread.zeroevenodd;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

/**
 *
 * Synchronized 和 notify
 *
 * @author liming53
 * @date 2023/1/29
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class ZeroEvenOdd2 {


    public static void main(String[] args) throws InterruptedException {
        Thread.yield();
        ZeroEvenOdd2 zeroEvenOdd = new ZeroEvenOdd2(3);
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
    private CountDownLatch countDownLatch0 = new CountDownLatch(0);
    private CountDownLatch countDownLatchEven = new CountDownLatch(1);
    private CountDownLatch countDownLatchOdd = new CountDownLatch(1);

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            countDownLatch0.await();
            printNumber.accept(0); //打印0
            countDownLatch0 = new CountDownLatch(1);
            if ((i & 1) == 1) { //奇数
                countDownLatchOdd.countDown();
            } else {
                countDownLatchEven.countDown();
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
                countDownLatchEven.await();
                printNumber.accept(i);
                countDownLatchEven = new CountDownLatch(1);
                countDownLatch0.countDown();
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
                countDownLatchOdd.await();
                printNumber.accept(i);
                countDownLatchOdd = new CountDownLatch(1);
                countDownLatch0.countDown();
            }
        }
    }

}
