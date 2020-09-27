package Set;

import java.util.ArrayList;
import java.util.TreeSet;

public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int num1: nums1) {
            set.add(num1);
        }
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int num2: nums2) {
            if (set.contains(num2)) {
                arr.add(num2);
                set.remove(num2);
            }
        }
        int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }

        return res;
    }
}
