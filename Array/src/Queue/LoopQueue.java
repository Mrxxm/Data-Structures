package Queue;

import Array.ArrayGenericsDynamic;

public class LoopQueue<E> implements Queue<E> {

    private int front;
    private int tail;
    private E[] data;

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    public int getSize() {
        int tmp = tail - front;

        if (tmp >= 0) {
            return tmp;
        } else {
            return data.length + tmp;
        }
    }

    public boolean isEmpty() {
        return front == tail;
    }

    public void enqueue(E element) {

        if ((tail + 1) % data.length == front)
//            throw new IllegalArgumentException("Add failed. Queue is full.");
            resize(getCapacity() * 2);

        data[tail] = element;
        tail = (tail + 1) % data.length;
    }

    public E dequeue() {

        if(isEmpty())
            throw new IllegalArgumentException("Dequeue failed. Queue is empty.");

        E element = data[front];
        front = (front + 1) % data.length;
        data[front] = null;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return element;
    }

    public E getFront() {

        if (isEmpty())
            throw new IllegalArgumentException("Get failed. Queue is empty.");

        return data[front];
    }

    public E get(int index) {

        if (isEmpty())
            throw new IllegalArgumentException("Get failed. Queue is empty.");
        if (tail > front) {
            if (index >= this.tail || index < front)
                throw new IllegalArgumentException("Get failed. Index is illegal.");
        } else {
            if (index >= this.tail && index < front)
                throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];
    }

    /**
     * 动态数组
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];

        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = getSize();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Loop Queue: size = %d , capacity = %d\n", getSize(), getCapacity());

        String ret = "front [";
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            ret += data[i];
            if ((i + 1) % data.length != tail) {
                ret = ret + ", ";
            }
        }
        ret += "] tail";
        result.append(ret);

        return result.toString();
    }
}
