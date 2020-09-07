package LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedListDummy<Integer> link = new LinkedListDummy<Integer>();

        for (int i = 0; i < 10; i++) {
            link.addFirst(i + 1);
        }

        link.set(2, 2);

        System.out.println(link);
    }
}
