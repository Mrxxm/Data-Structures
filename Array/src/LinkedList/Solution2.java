package LinkedList;

public class Solution2 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeElements(ListNode head, int val) {
        // 初始dummy head
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prevNode = dummyHead;
        while (prevNode.next != null) {
            if (prevNode.next.val == val) {
                prevNode.next = prevNode.next.next;
            } else {
                prevNode = prevNode.next;
            }
        }

        return dummyHead.next;
    }
}
