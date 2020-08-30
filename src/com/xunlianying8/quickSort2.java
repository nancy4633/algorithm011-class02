package com.xunlianying8;

// 第一遍
// 快速排序
public class quickSort2 {
    public static void quickSort2(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        System.out.println(pivot);
        quickSort2(array, begin, pivot - 1);
        quickSort2(array, pivot + 1, end);
    }

    static int partition(int[] a, int begin, int end) {
        System.out.println("begin=" + begin + ", end=" + end);
        int pivot = end;
        int counter = begin;
        int temp;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                temp = a[counter];
                a[counter] = a[i];
                a[i] = temp;
                counter++;
            }
        }
        temp = a[pivot];
        a[pivot] = a[counter];
        a[counter] = temp;
        return counter;
    }

    public static void main(String[] args) {
        int[] array = {6, 5, 4, 3, 2, 1};
        quickSort2(array, 0, 5);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
