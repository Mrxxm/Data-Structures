package BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};

        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        // 前序遍历
        bst.preOrder();
        System.out.println();
        // 非递归前序遍历
        bst.preOrderNR();;
        System.out.println();
        // 中序遍历
        bst.inOrder();
        System.out.println();
        // 后续遍历
        bst.backOrder();
        System.out.println();
        // 层序遍历
        bst.levelOrder();

//        System.out.println(bst);
    }
}
