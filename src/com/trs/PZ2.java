package com.trs;

import java.util.Arrays;
import java.util.Random;

public class PZ2 {

    private final static int LENGTH = (int) Math.pow(2, 4);

    public static void fillArray(int arr[]) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
        }
    }

    public static void batcherSort(int[] arr) {
        int p = arr.length;
        while (p > 0) {
            int q = arr.length, r = 0, d = p;
            boolean b;
            do {
                int nTo = arr.length - d;
                for (int i = 0; i < nTo; i++)
                    if ((i & p) == r) {
                        if (arr[i] > arr[i + d]) {
                            int temp = arr[i];
                            arr[i] = arr[i + d];
                            arr[i + d] = temp;
                        }
                    }
                b = q != p;
                if (b) {
                    d = q - p;
                    q >>= 1;
                    r = p;
                }
            } while (b);
            p >>= 1;
        }
    }

    public static void bubbleSort(int[] arr) {
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1])   // change to > for ascending sort
                {
                    temp = arr[j];                //swap elements
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
    }


    public static void main(String[] args) {

        int[] singleArr = new int[LENGTH];
        int[] multipleArr = new int[LENGTH];

        fillArray(singleArr);

        System.arraycopy(singleArr, 0, multipleArr, 0, singleArr.length);


//        System.out.println(Arrays.toString(singleArr));
//        System.out.println(Arrays.toString(multipleArr));

        long before = System.nanoTime();
        batcherSort(singleArr);
        System.out.println("Single thread = " + (System.nanoTime() - before));

        before = System.nanoTime();
        bubbleSort(multipleArr);
        System.out.println("Bubble thread = " + (System.nanoTime() - before));


//        System.out.println(Arrays.toString(singleArr));
//        System.out.println(Arrays.toString(multipleArr));


//        long before = System.currentTimeMillis();
//        batcherSort(singleArr);
//        System.out.println("Single thread = " + (System.currentTimeMillis() - before));
//
//
//        Thread[] threads = new Thread[4];
//        before = System.currentTimeMillis();
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(() -> {
//                batcherSort(multipleArr);
//            });
//            threads[i].start();
//        }
//        System.out.println("Multiple thread = " + (System.currentTimeMillis() - before));
//
//        System.out.println(Arrays.toString(singleArr));
//        System.out.println(Arrays.toString(multipleArr));

    }

}







