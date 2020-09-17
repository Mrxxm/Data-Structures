package BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//
//        for (int i = 0; i < nums.length; i++) {
//            bst.add(nums[i]);
//        }
//
//        // 前序遍历
//        bst.preOrder();
//        System.out.println();
//        // 非递归前序遍历
//        bst.preOrderNR();;
//        System.out.println();
//        // 中序遍历
//        bst.inOrder();
//        System.out.println();
//        // 后续遍历
//        bst.backOrder();
//        System.out.println();
//        // 层序遍历
//        bst.levelOrder();

//        System.out.println(bst);

        // 6-11
//        Random random = new Random();
//        int n = 1000;
//
//        for (int i = 0; i < n; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        ArrayList<Integer> nums = new ArrayList<>();
//        while (!bst.isEmpty()) {
//            nums.add(bst.removeMin());
//        }
//
//        System.out.println(nums);
//        // 检查数组是否从小到大
//        for (int i = 1; i < nums.size(); i++)
//            if (nums.get(i - 1) > nums.get(i))
//                throw new IllegalArgumentException("Error");
//
//        System.out.println("removeMin test completed.");

        // 6-12
        int[] nums = {5, 3, 6, 8, 4, 2};

        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        bst.remove(3);

        System.out.println(bst);
    }
}
