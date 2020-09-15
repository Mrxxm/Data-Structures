package BinarySearchTree;

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

    // 方法二(node是新开辟的一块内存)
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
            return ;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }




}
