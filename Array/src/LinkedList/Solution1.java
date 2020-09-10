package LinkedList;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution1 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeElements(ListNode head, int val) {
        // 删除所有相同元素的头节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return head;
        }

        ListNode prevNode = head;
        while (prevNode.next != null) {
            if (prevNode.next.val == val) {
//                ListNode delNode = prevNode.next;
//                prevNode.next = delNode.next;
//                delNode.next = null;
                // 这里改变的是 节点next指针的指向 preNode指向未改变
                prevNode.next = prevNode.next.next;
            } else {
                // 只是改变指向的节点
                prevNode = prevNode.next;
            }

        }

        return head;
    }
}
