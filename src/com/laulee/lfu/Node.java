package com.laulee.lfu;

/**
 * LFU：Least Frequently Use ：最不经常使用的
 * <p>
 * 1.内存有限
 * 2.删除最不经常使用的数据：
 * 2.1排序,最不经常使用的放在队尾，采用linkedhashmap; 使用其中的链表，保持有序
 * 2.2对每个元素进行使用一次计数+1，
 * 2.3如果使用次数相等，按照时间排序
 * <p>
 * Created by laulee on 2021/3/23.
 */
public class Node<K,V> implements Comparable<Node>{

    private K key;
    private V value;
    private long time;
    private long count;


    public Node() {
    }

    public Node(K key, V value, long time, long count) {
        super();
        this.key = key;
        this.value = value;
        this.time = time;
        this.count = count;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        int compare = Long.compare(this.count, o.count);
        if (compare == 0) {
            compare = Long.compare(this.time,o.time);
        }
        return compare;
    }

    @Override
    public String toString() {
        return
                "key=" + key +
                ", value=" + value +
                ", count=" + count
                ;
    }
}


