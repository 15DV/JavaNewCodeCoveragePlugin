package ru.spb.coverage;

public class SortUtils {

    private SortUtils() {
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }

    public static void insertSort(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    private static void swap(int[] array, int i, int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
