package Stack;

public interface Stack<E> {

    void push(E element);
    E pop();
    // 查看栈顶元素
    E peek();
    int getSize();
    boolean isEmpty();
}
