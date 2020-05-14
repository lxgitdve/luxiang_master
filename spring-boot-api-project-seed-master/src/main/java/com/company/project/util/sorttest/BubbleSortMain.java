package com.company.project.util.sorttest;

import com.company.project.util.ArrayUtil;

/**
 * @Version: 1.0
 * @Description: 冒泡排序
 */


public class BubbleSortMain {

    public static void main(String[] args) {
        int[] arr = {2,5,1,3,8,5,7,4,3};
        
        ArrayUtil.print(arr);
        
        System.out.println("\n 排序后");
        bubbleSort(arr);

        ArrayUtil.print(arr);

    }

    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {   
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
