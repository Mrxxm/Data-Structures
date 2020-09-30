package Heap;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution347 {

    private class Freq implements Comparable<Freq>{
        int e, times;
        public Freq(int e, int times) {
            this.e = e;
            this.times = times;
        }
        @Override
        public int compareTo(Freq another) {
            if (this.times > another.times) {
                return 1;
            } else if (this.times < another.times) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        for (int num: nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
            }
        }

        // 放入的元素类型必须是可比较的
//        PriorityQueueMin<Freq> pq = new PriorityQueueMin<>();

        // PriorityQueueMin
//        for (int key: treeMap.keySet()) {
//            if (pq.getSize() < k) {
//                pq.enqueue(new Freq(key, treeMap.get(key)));
//            } else if (treeMap.get(key) > pq.getFront().times) {
//                pq.dequeue();
//                pq.enqueue(new Freq(key, treeMap.get(key)));
//            }
//        }

//        int[] arr = new int[k];
//        for (int i = k - 1; i >= 0; i--) {
//            arr[i] = pq.dequeue().e;
//        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();

        for (int key: treeMap.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, treeMap.get(key)));
            } else if (treeMap.get(key) > pq.peek().times) {
                pq.remove();
                pq.add(new Freq(key, treeMap.get(key)));
            }
        }

        int[] arr = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            arr[i] = pq.remove().e;
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347 obj = new Solution347();
        int[] res = obj.topKFrequent(nums, 2);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
