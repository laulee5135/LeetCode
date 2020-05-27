package com.laulee;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by laulee on 2020/5/25.
 */
public class LRUCache extends LinkedHashMap{


    public LRUCache(int capacity) {
    }

    /**
     * get之前需要排序
     * @param key
     * @return
     */
    public int get(int key) {
        return 0;
    }

    /**
     * put之前需要检查容量并确定是否先删除，
     * @param key
     * @param value
     */
    public void put(int key, int value) {
    }


}

//class Node{
//    public int key,val;
//    public Node next,prev;
//
//    public Node(int key,int val){
//        this.key = key;
//        this.val = val;
//    }
//
//}
//
//
///**
// * 以下均为O(1)时间复杂度
// */
//class DoubleList{
//
//    public Node head,tail;
//    public int size;
//
//    public DoubleList(){
//        head = new Node(0,0);
//        tail = new Node(0,0);
//        head.next = tail;
//        tail.prev = head;
//        size = 0;
//    }
//
//    /**
//     * 添加一个节点
//     * @param x
//     */
//    public void addTail(Node x){
//        x = tail.next;
//        size ++;
//    }
//
//    /**
//     * 删除一个节点
//     * @param x
//     */
//    public void remove(Node x){
//        x.prev.next = x.next;
//        x.next.prev = x.prev;
//
//        head.prev = x.next;
//        tail.next = x.prev;
//        size --;
//    }
//
//    /**
//     * 删除最不常用的节点,并返回
//     */
//    public void removeHead(){
//
//    }
//
//    public int getSize(){
//        return this.size;
//    }

//}





