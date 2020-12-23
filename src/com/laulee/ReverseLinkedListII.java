package com.laulee;

/**
 * 92.
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * Created by laulee on 2020/9/27.
 */
public class ReverseLinkedListII {


    public static void main(String[] args) {
        ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);

        ListNode reverse = reverseLinkedListII.reverse(listNode);
        System.out.println(reverse);
    }

    /**
     * 反转整个链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }


}


