package RedBlackTree;

import AVLTree.AVLTree;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {

        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 默认初始化红色
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    public int getSize() { return size; }

    public boolean isEmpty() { return size == 0; }

    // 左旋转
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 右旋转
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 保持根节点为黑色节点
    }

    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { // key.compareTo(node.key) == 0
            node.value = value;
        }

        // 左旋转
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        // 右旋转
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        // 颜色翻转
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else { // key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " does not exist!");

        node.value = newValue;
    }

    // 寻找二分搜索树的最小元素
    public K minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return minimum(root).key;
    }
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }
    // 寻找二分搜索树的最大元素
    public K maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).key;
    }
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 从二分搜索树种删除最小值所在的节点，返回最小值
    public K removeMin() {
        K ret = minimum();

        // 删除处理
        root = removeMin(root);

        return ret;
    }

    public K removeMax() {
        K ret = maximum();

        // 删除处理
        root = removeMax(root);

        return ret;
    }

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

    // 从二分搜索树中删除元素为e的节点
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);

            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // e == node.e
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;

                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;

                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
