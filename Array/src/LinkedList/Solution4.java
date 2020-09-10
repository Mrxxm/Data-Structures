package LinkedList;

public class Solution4 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
//        # 1
//        ListNode res = removeElements(head.next, val);
//        if (head.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }
//        # 2
//        head.next = removeElements(head.next, val);
//        if (head.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
