package Queue;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrQ = new ArrayQueue<Integer>(opCount);
        System.out.println("ArrayQueue, time: " + testQueue(arrQ, opCount) + " s");

        LoopQueue<Integer> loopQ = new LoopQueue<Integer>(opCount);
        System.out.println("LoopQueue, time: " + testQueue(loopQ, opCount) + " s");

        LinkedListQueue<Integer> linkQ = new LinkedListQueue<Integer>();
        System.out.println("LinkQueue, time: " + testQueue(linkQ, opCount) + " s");
    }

    // 测试使用q运行opCount个enqueue和dequeue操作所需的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {
        // 纳秒
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
