package Array;

public class Main {

    public static void main(String[] args) {

        ArrayGenericsDynamic<Integer> arrD = new ArrayGenericsDynamic<Integer>();
        for (int i = 0; i < 10; i++) {
            arrD.addLast(2);
        }
        System.out.println(arrD);
        arrD.addLast(3);
        System.out.println(arrD);
        arrD.removeFirst();
        System.out.println(arrD);
    }
}
