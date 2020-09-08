package Stack;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opCount = 10000000;

        ArrayStack<Integer> arrS = new ArrayStack<Integer>(opCount);
        System.out.println("ArrayStack, time: " + testStack(arrS, opCount) + " s");

        LinkedListStack<Integer> linkS = new LinkedListStack<Integer>();
        System.out.println("LinkStack, time: " + testStack(linkS, opCount) + " s");
    }

    // 测试使用q运行opCount个enqueue和dequeue操作所需的时间，单位：秒
    private static double testStack(Stack<Integer> s, int opCount) {
        // 纳秒
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            s.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            s.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
