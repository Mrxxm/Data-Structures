package SegmentTree;

public class Solution307 {
    private int[] sum;
    private int[] data;

    // 构造O(n)复杂度
    public Solution307(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            data[i] = nums[i];

        sum = new int[nums.length + 1]; // sum[i]存储前i个元素和， sum[0] = 0
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    // O(n)复杂度
    public void update(int index ,int val) {
        data[index] = val;
        for (int i = index + 1; i < sum.length; i ++)
            sum[i] = sum[i - 1] + data[i - 1];
    }

    // 计算O(1)复杂度
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
