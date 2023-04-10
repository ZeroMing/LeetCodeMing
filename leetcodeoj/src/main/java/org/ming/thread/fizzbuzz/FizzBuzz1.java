package org.ming.thread.fizzbuzz;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author liming53
 * @date 2023/1/30
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class FizzBuzz1 {


    public static void main(String[] args) throws InterruptedException {
        FizzBuzz1 fizzBuzz = new FizzBuzz1(15);
        new Thread(()->{
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.number(n -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /*
    请你实现一个有四个线程的多线程版  FizzBuzz1， 同一个 FizzBuzz1 实例会被如下四个线程使用：
    1、线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
    2、线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
    3、线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
    4、线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。

    当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz
     */


    private int n;
    private Semaphore number = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);

    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                // 等待获取锁
                fizz.acquire();
                // 执行
                printFizz.run();
                // 释放number锁
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                // 等待获取锁
                buzz.acquire();
                // 获取到锁执行
                printBuzz.run();
                // 释放number锁
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                number.release();
            }else if(i % 3 == 0 && i % 5 != 0){
                fizz.release();
            }else if(i % 3 != 0 && i % 5 == 0){
                buzz.release();
            }else{
                fizzbuzz.release();
            }
        }
    }


}
