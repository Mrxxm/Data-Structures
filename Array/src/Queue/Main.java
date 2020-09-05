package Queue;

public class Main {
    public static void main(String[] args) {
        ArrayQueue<Integer> arrQ = new ArrayQueue<Integer>();

        System.out.println(arrQ.isEmpty());

        for(int i = 0; i < 10; i++) {
            arrQ.enqueue(i + 1);
        }

        System.out.println(arrQ.dequeue());
        arrQ.enqueue(1);
        System.out.println(arrQ.getFront());
        System.out.println(arrQ);
        System.out.println(arrQ.isEmpty());
    }
}
