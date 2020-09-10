package LinkedList;

public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {
        // 初始dummy head
        ListNode dummyHead = new ListNode(1);
        dummyHead.next = head;

        ListNode prevNode = dummyHead;
        while (prevNode.next != null) {
            if (prevNode.next.val == val) {
                prevNode.next = prevNode.next.next;
            } else {
                prevNode = prevNode.next;
            }
//            System.out.println(prevNode);
            System.out.println(head);
        }


        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);

//        System.out.println(head);

        ListNode result = (new Solution3()).removeElements(head, 6);
//        System.out.println(result);
    }
}
