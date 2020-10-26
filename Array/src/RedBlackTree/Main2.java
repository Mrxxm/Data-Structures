package RedBlackTree;

import AVLTree.AVLTree;
import BinarySearchTree.BST;

import java.util.ArrayList;
import java.util.Random;

// 添加操作对比
public class Main2 {
    public static void main(String[] args) {
        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // Test BST
        long startTime = System.nanoTime();

        BST<Integer> bst = new BST<>();
        for (Integer x: testData)
            bst.add(x);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        // Test AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<Integer, Integer>();
        for (Integer x: testData)
            avl.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");

        // Test RBT
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<Integer, Integer>();
        for (Integer x: testData)
            rbt.add(x, null);

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBT: " + time + " s");
    }
}
