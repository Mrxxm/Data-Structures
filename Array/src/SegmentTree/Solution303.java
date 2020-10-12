package SegmentTree;

public class Solution303 {

    // 满二叉树
    private Integer[] tree;
    private Integer[] data;

    public Solution303(int[] nums) {

        if (nums.length > 0) {
            data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }

            // 满二叉树需要的数组空间
            tree = new Integer[4 * nums.length];
            buildSegmentTree(0, 0, data.length - 1);
        }
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return ;
        }

        // 获得左右子树的根节点
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 获得区间范围
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public int sumRange(int i, int j) {
        if (i < 0 || i >= data.length || j < 0 || j >= data.length || i > j)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0 , 0, data.length - 1, i, j);
    }

    private Integer query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        Integer leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        Integer rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return leftResult + rightResult;
    }
}
