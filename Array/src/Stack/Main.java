package Stack;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);

        stack.pop();

        System.out.println(stack);

        System.out.println(stack.peek());
    }
}
