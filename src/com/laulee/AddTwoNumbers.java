package com.laulee;

/**
 * Created by laulee on 2020/5/27.
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 2题
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);


        ListNode listNode = addTwoNumbers.addTwoNumbers(l1, l2);
        System.out.println(listNode);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode cur = new ListNode(0);
        ListNode result = cur; //引用传递
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry; //carry：上一个节点的进位

            carry = sum / 10; //是否进位
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next; //指针指向下一个节点

            if(l1 != null){
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

        }

        if (carry == 1) {  //两个链表如果遍历完成之后进位不为0，需要保存
            cur.next = new ListNode(carry);
        }

        return result.next;

    }
}


class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}


