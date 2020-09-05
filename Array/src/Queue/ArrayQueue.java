package Queue;

import Array.ArrayGenericsDynamic;

public class ArrayQueue<E> implements Queue<E> {

    // 动态数组成员变量
    ArrayGenericsDynamic<E> array;

    public ArrayQueue(int capacity) {
        array = new ArrayGenericsDynamic<E>(capacity);
    }

    public ArrayQueue() {
        array = new ArrayGenericsDynamic<E>();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E element) {
        array.addLast(element);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Array Queue: ");

        String ret = "top [";
        for (int i = 0; i < array.getSize(); i++) {
            ret += array.get(i);
            if (i != array.getSize() - 1) {
                ret = ret + ", ";
            }
        }
        ret += "] end";
        result.append(ret);

        return result.toString();
    }
}
