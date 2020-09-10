package Queue;

import LinkedList.LinkedList;
import Stack.LinkedListDummyStack;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private Node tail;
    int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        // tail为空表名head也为空,链表为空
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node delNode = head;
        head = head.next;
        delNode.next = null;
        size--;

        if (head == null)
            tail = null;

        return delNode.e;
    }

    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot get from an empty queue.");
        return head.e;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("List Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + " -> ");
            cur = cur.next;
        }
        res.append("NULL tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> linkQ = new LinkedListQueue<Integer>();

        for (int i = 0; i < 5; i++) {
            linkQ.enqueue(i);
            System.out.println(linkQ);
        }

        linkQ.dequeue();

        System.out.println(linkQ);
        System.out.println(linkQ.getFront());
    }
}
