package com.company;

public class Main {

    public static <T extends Comparable<T>> void swap(T[] data, int i, int j) {

        T temp = data[i];

        data[i] = data[j];

        data[j] = temp;

    }

    public static <T extends Comparable<T>> void maxHeap(T[] data, int i, int heapN, int begin) {

        T temp = data[begin + i - 1];

        int child;

        while (i <= heapN / 2) {

            child = 2 * i;

            if (child < heapN && data[begin + child - 1].compareTo(data[begin + child]) < 0) {

                child++;

            }

            if (temp.compareTo(data[begin + child - 1]) >= 0) {

                break;

            }

            data[begin + i - 1] = data[begin + child - 1];

            i = child;

        }

        data[begin + i - 1] = temp;

    }

    public static <T extends Comparable<T>> void heapify(T[] data, int begin, int heapN) {

        for (int i = (heapN) / 2; i >= 1; i--) {

            maxHeap(data, i, heapN, begin);

        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] data, int begin, int end) {

        int heapN = end - begin;

        heapify(data, begin, heapN);

        for (int i = heapN; i >= 1; i--) {

            swap(data, begin, begin + i);

            maxHeap(data,1, i, begin);

        }
    }

    public static <T extends Comparable<T>> int findPivot(T[] data, int a1, int b1, int c1) {

        T max = null;

        T min = null;

        if(data[a1].compareTo(data[b1]) >= 0 && data[a1].compareTo(data[c1]) >= 0) {

            max = data[a1];

        }

        if(data[b1].compareTo(data[a1]) >= 0 && data[b1].compareTo(data[c1]) >= 0) {

            max = data[b1];

        }

        if(data[c1].compareTo(data[a1]) >= 0 && data[c1].compareTo(data[b1]) >= 0) {

            max = data[c1];

        }

        if(data[a1].compareTo(data[b1]) <= 0 && data[a1].compareTo(data[c1]) <= 0) {

            min = data[a1];

        }

        if(data[b1].compareTo(data[a1]) <= 0 && data[b1].compareTo(data[c1]) <= 0) {

            min = data[b1];

        }

        if(data[c1].compareTo(data[a1]) <= 0 && data[c1].compareTo(data[b1]) <= 0) {

            min = data[c1];

        }
        
        Integer median = Integer.parseInt(max.toString()) ^ Integer.parseInt(min.toString()) ^ Integer.parseInt(data[a1].toString()) ^ Integer.parseInt(data[b1].toString()) ^ Integer.parseInt(data[c1].toString());

        if (median == Integer.parseInt(data[a1].toString())) {

            return a1;

        }

        if (median == Integer.parseInt(data[b1].toString())) {

            return b1;

        }

        return c1;

    }

    public static <T extends Comparable<T>> int partition(T[] data, int low, int high) {

        T pivot = data[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (data[j].compareTo(pivot) <= 0) {

                i++;

                swap(data, i, j);

            }
        }

        swap(data,i + 1, high);

        return (i + 1);

    }

    public static <T extends Comparable<T>> void insertionSort(T[] data, int left, int right) {

        for (int i = left; i <= right; i++) {

            T key = data[i];

            int j = i;

            while (j > left && data[j - 1].compareTo(key) > 0) {

                data[j] = data[j - 1];

                j--;

            }

            data[j] = key;

        }
    }

    public static <T extends Comparable<T>> void sort(T[] data, int begin, int end, int depthLimit) {

        if (end - begin > 16) {

            if (depthLimit == 0) {

                heapSort(data, begin, end);

                return;

            }

            depthLimit = depthLimit - 1;

            int pivot = findPivot(data, begin, begin + ((end - begin) / 2) + 1, end);

            swap(data, pivot, end);

            int p = partition(data,begin, end);

            sort(data, begin, p - 1, depthLimit);

            sort(data,p + 1, end, depthLimit);

        }

        else {

            insertionSort(data, begin, end);

        }
    }

    public static void main(String[] args) {

        int size = 10000000;

        Integer[] arr = new Integer[size];

        for (int i = 0; i < size; i++) {

            arr[i] = (int)((Math.random() * ((10000 - 1) + 1)) + 1);

        }

        int maxDepth = (int)((Math.log(arr.length)/Math.log(2)) * 2);

        sort(arr, 0 ,arr.length - 1, maxDepth);

        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i] + " " + (i + 1));

        }
    }
}