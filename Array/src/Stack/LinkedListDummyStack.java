package Stack;

import LinkedList.LinkedListDummy;

public class LinkedListDummyStack<E> implements Stack<E> {

    private LinkedListDummy<E> list;

    public LinkedListDummyStack() {
        list = new LinkedListDummy<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListDummyStack<Integer> stack = new LinkedListDummyStack<Integer>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();

        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
