package Map;

import java.util.ArrayList;
import java.util.TreeMap;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num1: nums1) {
            if (map.containsKey(num1)) {
                int times = map.get(num1);
                map.put(num1, times + 1);
            } else {
                map.put(num1, 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num2: nums2) {
            if (map.containsKey(num2) && map.get(num2) > 0) {
                list.add(num2);
                map.put(num2, map.get(num2) - 1);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
