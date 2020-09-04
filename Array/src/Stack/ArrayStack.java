package Stack;

import Array.ArrayGenericsDynamic;

public class ArrayStack<E> implements Stack<E> {

    // 动态数组成员变量
    ArrayGenericsDynamic<E> array;

    public ArrayStack(int capacity) {
        array = new ArrayGenericsDynamic<E>(capacity);
    }

    public ArrayStack() {
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
    public void push(E element) {
        array.addLast(element);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Stack: ");

        String ret = "[";
        for (int i = 0; i < array.getSize(); i++) {
            ret += array.get(i);
            if (i != array.getSize() - 1) {
                ret = ret + ", ";
            }
        }
        ret += "] top";
        result.append(ret);

        return result.toString();
    }
}
