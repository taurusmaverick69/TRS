package com.trs;

import java.util.Arrays;
import java.util.Random;

public class PZ2 {

    private final static int LENGTH = (int) Math.pow(2, 10);

    private static void fillArray(int arr[]) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
        }
    }

    private static void batcherSort(int[] arr) {

        long before = System.nanoTime();

        for (int p = arr.length; p > 0; p >>= 1) {

            int q = arr.length, r = 0, d = p;
            boolean b;
            do {
                int nTo = arr.length - d;
                for (int i = 0; i < nTo; i++) {
                    if ((i & p) == r) {
                        if (arr[i] > arr[i + d]) {
                            int temp = arr[i];
                            arr[i] = arr[i + d];
                            arr[i + d] = temp;
                        }
                    }
                }

                b = q != p;
                if (b) {
                    d = q - p;
                    q >>= 1;
                    r = p;
                }
            } while (b);
        }
        System.out.println("Batcher time = " + (System.nanoTime() - before));
    }

    private static void bubbleSort(int[] arr) {

        long before = System.nanoTime();

        int j;
        boolean flag = true;
        int temp;

        while (flag) {
            flag = false;
            for (j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println("Bubble time = " + (System.nanoTime() - before));
    }

    public static void main(String[] args) throws InterruptedException {

        int[] batcherSingleArr = new int[LENGTH];
        int[] bubbleSingleArr = new int[LENGTH];
        int[] batcherMultipleArr = new int[LENGTH];
        int[] bubbleMultipleArr = new int[LENGTH];

        fillArray(batcherSingleArr);

        System.arraycopy(batcherSingleArr, 0, bubbleSingleArr, 0, batcherSingleArr.length);
        System.arraycopy(batcherSingleArr, 0, batcherMultipleArr, 0, batcherSingleArr.length);
        System.arraycopy(batcherSingleArr, 0, bubbleMultipleArr, 0, batcherSingleArr.length);

        System.out.println("Batcher single thread initial array: " + Arrays.toString(batcherSingleArr));
        System.out.println("Bubble single thread initial array: " + Arrays.toString(bubbleSingleArr));
        System.out.println("Batcher multiple thread initial array: " + Arrays.toString(batcherMultipleArr));
        System.out.println("Batcher multiple thread initial array: " + Arrays.toString(bubbleMultipleArr));

        System.out.println(System.lineSeparator() + "***SINGLE THREAD***");

        batcherSort(batcherSingleArr);
        bubbleSort(bubbleSingleArr);

        System.out.println("Batcher single thread sorted array: " + Arrays.toString(batcherSingleArr));
        System.out.println("Bubble single thread sorted array: " + Arrays.toString(bubbleSingleArr));

        System.out.println(System.lineSeparator() + "***MULTIPLE THREAD***");

        Thread[] batcherThreads = new Thread[4];
        for (int i = 0; i < batcherThreads.length; i++) {
            batcherThreads[i] = new Thread(() -> {
                batcherSort(batcherMultipleArr);
            });
            batcherThreads[i].start();
            batcherThreads[i].join();
        }
        System.out.println("Batcher multiple thread sorted array: " + Arrays.toString(batcherMultipleArr) + System.lineSeparator());

        Thread[] bubbleThreads = new Thread[4];
        for (int i = 0; i < bubbleThreads.length; i++) {
            bubbleThreads[i] = new Thread(() -> {
                bubbleSort(bubbleMultipleArr);
            });
            bubbleThreads[i].start();
            bubbleThreads[i].join();
        }
        System.out.println("Bubble multiple thread sorted array: " + Arrays.toString(bubbleMultipleArr));
    }
}