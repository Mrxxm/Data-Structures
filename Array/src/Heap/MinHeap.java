package Heap;

import Array.ArrayGenericsDynamic;

import java.util.Random;

public class MinHeap<E extends Comparable<E>> {
    private ArrayGenericsDynamic<E> data;

    public MinHeap(int capacity) {
        data = new ArrayGenericsDynamic<E>(capacity);
    }

    public MinHeap() {
        data = new ArrayGenericsDynamic<E>();
    }

    // heapify过程
    public MinHeap(E[] arr) {
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
        // 父亲节点还要大的话 就要上浮操作
        while(index > 0 && data.get(parent(index)).compareTo(data.get(index)) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMin() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMin when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMin() {
        E ret = findMin();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return ret;
    }

    // 下沉
    private void siftDown(int index) {

        while (leftChild(index) < data.getSize()) {
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j = rightChild(index);
                // data[j] 是 leftChild 和 rightChild 中的最小值
            }
            if (data.get(index).compareTo(data.get(j)) <= 0) {
                break;
            }
            data.swap(index, j);
            index = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMin();

        data.set(0, e);
        siftDown(0);

        return ret;
    }

    public static void main(String[] args) {
        int n = 10;

        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            minHeap.add(random.nextInt(100));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // 堆排序
            arr[i] = minHeap.extractMin();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new IllegalArgumentException("Error");
            }
            System.out.println(arr[i - 1]);
        }

        System.out.println("Test MinHeap completed.");
    }
}

