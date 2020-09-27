package Heap;

import Array.ArrayGenericsDynamic;

public class MaxHeap<E extends Comparable<E>> {
    private ArrayGenericsDynamic<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayGenericsDynamic<E>(capacity);
    }

    public MaxHeap() {
        data = new ArrayGenericsDynamic<E>();
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

    private void siftUp(int index) {
        while(index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }
}

