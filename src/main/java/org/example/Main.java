package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int[] s = new int[10000];
        for (int i = 0; i < s.length; i++) {
            s[i] = random.nextInt(0, 20);
        }
        long startTime = System.currentTimeMillis();
        int sum = calcSum(s);
        System.out.println("Summa - " + sum);
        System.out.println("Srednee - " + (double) sum / s.length);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");


        startTime = System.currentTimeMillis();
        ValueSumCounter valueSumCounter = new ValueSumCounter(s);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(valueSumCounter));
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");

        startTime = System.currentTimeMillis();
        System.out.println("Recursive - " + calcSumRecursive(s));
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }
    public static int calcSum(int[] s) throws InterruptedException {
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            sum += s[i];
            //Thread.sleep(1);
        }
        return sum;
    }

    public static int calcSumRecursive(int[] s) {
        if (s.length <= 2) {
            return Arrays.stream(s).sum();
        }
        int[] firstHalf = Arrays.copyOfRange(s, 0, s.length/2);
        int[] secondHalf = Arrays.copyOfRange(s, s.length/2, s.length);
        return calcSumRecursive(firstHalf) + calcSumRecursive(secondHalf);

    }
}