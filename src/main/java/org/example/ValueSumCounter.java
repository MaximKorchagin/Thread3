package org.example;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ValueSumCounter extends RecursiveTask<Integer> {
    private int[] array;

    public ValueSumCounter(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2) {
            return Arrays.stream(array).sum();
        }
        ValueSumCounter firstHalfValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        ValueSumCounter secondHalfValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        firstHalfValueSumCounter.fork();
        secondHalfValueSumCounter.fork();
        return firstHalfValueSumCounter.join() + secondHalfValueSumCounter.join();
    }
}
