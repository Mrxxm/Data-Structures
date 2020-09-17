package Set;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(testSet(new BSTSet<Integer>()));
        System.out.println(testSet(new LinkedListSet<Integer>()));
    }

    private static double testSet(Set<Integer> set) {
        long startTime = System.nanoTime();

        Random random = new Random();

        int n = 100000;

        for (int i = 0; i < n; i++) {
            set.add(random.nextInt(10000));
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.00;
    }
}
