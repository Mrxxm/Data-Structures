package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    public Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    // 方法一
//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }
//
      // 向以node为根的二分搜索树中插入元素e，递归算法
//    // 方法一
//    private void add(Node node, E e) {
//        if (e.equals(node.e)) {
//            return ;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return ;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return ;
//        }
//
          // 等于0的情况一开始已经被判断
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

    // 方法二
    public void add(E e) {
        root = add(root, e);
    }

    // 返回插入新节点后二分搜索树的根
    // 方法二()
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo((E) node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo((E) node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    // 二分搜索树的查询
    public boolean contains(E e) {
        return contains(root, e);
    }

    private Boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo((E) node.e) == 0) {
            return true;
        } else if (e.compareTo((E) node.e) < 0) {
            return contains(node.left, e);
        } else { // e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 前序遍历(非递归)
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历
    public void backOrder() {
        backOrder(root);
    }

    private void backOrder(Node node) {
        if (node == null)
            return;

        backOrder(node.left);
        backOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历
//    public void levelOrder() {
//        Queue.LinkedListQueue<Node> queue = new Queue.LinkedListQueue<Node>();
//        queue.enqueue(root);
//
//        while (!queue.isEmpty()) {
//            Node cur = queue.dequeue();
//            System.out.println(cur.e);
//
//            if (cur.left != null)
//                queue.enqueue(cur.left);
//            if (cur.right != null)
//                queue.enqueue(cur.right);
//        }
//    }
    // 层序遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return (E)minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return (E)maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 从二分搜索树种删除最小值所在的节点，返回最小值
    public E removeMin() {
        E ret = minimum();

        // 删除处理
        root = removeMin(root);

        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        // 递归到底的情况
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;

            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    public E removeMax() {
        E ret = maximum();

        // 删除处理
        root = removeMax(root);

        return ret;
    }

    private Node removeMax(Node node) {

        // 递归到底的情况
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;

            return leftNode;
        }

        node.right = removeMin(node.right);

        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);

        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i++)
            res.append("--");

        return res.toString();
    }

}






















