package LinkedList;

public class LinkedListDummy<E> {

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

    // 修改
    private Node dummyHead;
    int size;

    public LinkedListDummy() {
        dummyHead = new Node(null, null);
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

    // 在链表的index位置添加新的元素e
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index .");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        prev.next = new Node(e, prev.next);

        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 获取链表的第index(0开始)个位置元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index .");

        Node cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 更新
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index .");

        Node cur = dummyHead;
        for (int i = 0; i <= index; i++) {
             cur = cur.next;
        }

        cur.e = e;
    }

    // 查找
    public boolean contains(E e) {
        Node cur = dummyHead;
        for (int i = 0; i < size; i++) {
            cur = cur.next;
            if (cur.e.equals(e))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Linked List : size %d", size) + "\n");

        Node cur = dummyHead;

        for (int i = 0; i < size; i++) {
            cur = cur.next;
            res.append(cur.e);
            if (i != size - 1) {
                res.append(" -> ");
            } else {
                res.append(" -> NULL");
            }
        }

        return res.toString();
    }

}
