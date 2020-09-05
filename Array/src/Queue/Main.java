package Queue;

public class Main {
    public static void main(String[] args) {
        LoopQueue<Integer> loopQ = new LoopQueue<Integer>();

        System.out.println(loopQ.isEmpty());

        for(int i = 0; i < 9; i++) {
            System.out.println(loopQ.getSize());
            System.out.println(loopQ);
            loopQ.enqueue(i + 1);
        }
        for(int i = 0; i < 9; i++) {
            loopQ.dequeue();
            System.out.println(loopQ.getSize());
            System.out.println(loopQ);
        }
    }
}
