package LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedListDummy<Integer> link = new LinkedListDummy<Integer>();

        for (int i = 0; i < 10; i++) {
            link.addFirst(i + 1);
            System.out.println(link);
        }

        link.removeLast();
        System.out.println(link);
        System.out.println(link.get(0));
    }
}
