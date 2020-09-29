package Heap;

import Array.ArrayGenericsDynamic;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private ArrayGenericsDynamic<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayGenericsDynamic<E>(capacity);
    }

    public MaxHeap() {
        data = new ArrayGenericsDynamic<E>();
    }

    // heapify过程
    public MaxHeap(E[] arr) {
        data = new ArrayGenericsDynamic<E>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 does not have parent.");
        return (index - 1) / 2;
    }

    // 左孩子
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 右孩子
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }
    // 上浮
    private void siftUp(int index) {
        while(index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax() {
        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return ret;
    }

    // 下沉
    private void siftDown(int index) {

        while (leftChild(index) < data.getSize()) {
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(index);
                // data[j] 是 leftChild 和 rightChild 中的最大值
            }
            if (data.get(index).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(index, j);
            index = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();

        data.set(0, e);
        siftDown(0);

        return ret;
    }

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] testData = new Integer[n];

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");
        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num: testData) {
                maxHeap.add(num);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            // 堆排序
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}

