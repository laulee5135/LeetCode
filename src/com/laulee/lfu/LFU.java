package com.laulee.lfu;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * LRU和LFU侧重点不同，
 *  LRU主要体现在对元素的使用时间上，而LFU主要体现在对元素的使用频次上。
 * LFU的缺陷是：
 *  在短期的时间内，对某些缓存的访问频次很高，
 *  这些缓存会立刻晋升为热点数据，而保证不会淘汰，
 *  这样会驻留在系统内存里面。而实际上，这部分数据只是短暂的高频率访问，
 *  之后将会长期不访问，瞬时的高频访问将会造成这部分数据的引用频率加快，
 *  而一些新加入的缓存很容易被快速删除，因为它们的引用频率很低。
 *
 * Created by laulee on 2021/3/23.
 */
public class LFU<K,V> {

    int capacity;
    Map<K,Node> caches;

    public LFU(int capacity) {
        this.capacity = capacity;
        this.caches = new LinkedHashMap<>(capacity);
    }

    public void put(K key, V value){
        Node node = caches.get(key);
        if (node == null) {
            if (caches.size() >= capacity) {
                //空间不够用了，开始计算count最小的元素
                K leaseMin = getLeaseMinCount();
                //删除最不经常使用的元素
                caches.remove(leaseMin);
            }
            caches.put(key,new Node(key,value,System.nanoTime(),1));
        }else {
            node.setValue(value);
            node.setCount(node.getCount()+1);
            node.setTime(System.nanoTime());
        }
        sort();
    }

    private K getLeaseMinCount() {
        Collection<Node> values = caches.values();
        Node min = Collections.min(values);
        return (K)min.getKey();
    }


    public V get(K key){
        Node node = caches.get(key);
        if (node != null) {
            node.setTime(System.nanoTime());
            node.setCount(node.getCount()+1);
            sort();
            return (V)node.getValue();
        }
        return null;
    }

    private void sort() {
        ArrayList<Map.Entry<K, Node>> entries = new ArrayList<>(caches.entrySet());
        Collections.sort(entries,(e1,e2)->{
            return e1.getValue().compareTo(e2.getValue());
        });

        caches.clear();
        for (Map.Entry<K, Node> entry : entries) {
            caches.put(entry.getKey(),entry.getValue());
        }
    }

    public static void main(String[] args) {
        LFU lfu = new LFU(3);
        lfu.put(1,"a");
        lfu.put(2,"b");
        lfu.put(3,"c");
        lfu.put(4,"d");

        Object o = lfu.get(1);
        System.out.println(o);
        lfu.get(2);
        lfu.get(2);
        lfu.get(3);
        lfu.get(3);
        lfu.get(3);

        lfu.put(1,"a");
        System.out.println(lfu.caches);

    }

}
