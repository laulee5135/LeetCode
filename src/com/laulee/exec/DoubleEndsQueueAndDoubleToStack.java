package com.laulee.exec;

/**
 *
 * Created by laulee on 2020/12/23.
 */
public class DoubleEndsQueueAndDoubleToStack {

    public static class Node<T>{
        public T value;
        public Node last;
        public Node next;

        public Node(T t){
            this .value = t;
        }
    }

    /**
     * 双向列表实现队列
     */
    public static class DoubleEndsQueue<T>{

        public Node<T> head;
        public Node<T> tail;


        public void pushFromHead(T val){
            Node<T> cur = new Node<T>(val);
            if (head == null) {
                head = cur;
                tail = cur;
            }else{
                cur.next = head;
                head.last = cur; //双向，相互指向
                head = cur; //记录head
            }
        }

        public void pushFromTail(T val){
            Node<T> cur = new Node<>(val);
            if (tail == null) {
                head = cur;
                tail = cur;
            }else{
                cur.last = tail;
                tail.next = cur;
                tail = cur; //记录tail
            }

        }

        public T popFromHead(){
            if(head == null){
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                head = cur.next;
                cur.next  = null; //?
                head.last = null;
            }

            return cur.value;
        }

        public T popFromTail(){

            if (tail == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            }else{
                tail = cur.last;
                cur.last = null;
                tail.next = null;
            }

            return cur.value;
        }

        public boolean isEmpty(){
            return head == null;
        }


    }


}


