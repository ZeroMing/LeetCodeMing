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
public class ZeroEvenOdd3 {


    public static void main(String[] args) throws InterruptedException {
        Thread.yield();
        ZeroEvenOdd3 zeroEvenOdd = new ZeroEvenOdd3(3);
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

    private volatile int start = 1;
    private volatile int state;
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public ZeroEvenOdd3(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if(state != 0){
                    zero.await();
                }
                printNumber.accept(0);
                if((start & 1) == 0){
                    state = 2;
                    even.signal();
                }else{
                    state = 1;
                    odd.signal();
                }
                zero.await();
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 偶数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if(state != 2){
                    even.await();
                }else{
                    printNumber.accept(start++);
                    state = 0;
                    zero.signal();
                }
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 奇数
     *
     * @param printNumber
     * @throws InterruptedException
     */
    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if(state != 1){
                    odd.await();
                }else{
                    printNumber.accept(start++);
                    state = 0;
                    zero.signal();
                }
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }

    }

}
