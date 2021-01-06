/*
Design and implement a data structure for Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Sets or inserts the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be evicted.
Could you do both operations in O(1) time complexity?
*/
import java.util.*;
class DeNode{
    int key, val, freq;
    DeNode next, prev;
    DeNode(int key, int val, int freq){
        this.key = key;
        this.val = val;
        this.freq = freq;
        next = null;
        prev = null;
    }
}
class Dequeue{
    DeNode head, tail;
    Dequeue(){
        head = new DeNode(-1,-1,-1);
        tail = new DeNode(-1,-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    void remove(DeNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    void insert(DeNode node){
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
    int removeFirst(){
        DeNode node = head.next;
        head.next = node.next;
        node.next.prev = head;
        node.next = null;
        node.prev = null;
        return node.key;
    }
}
class LFU {
    Map<Integer, DeNode> cache;
    Map<Integer, Dequeue> freqMap;
    int capacity; int minFreq;
    public LFU(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
    }
    
    public int get(int key) {
        if(cache.get(key) == null || capacity == 0)
            return -1;
        
            DeNode curr = cache.get(key);
        Dequeue freqQ = freqMap.get(curr.freq);
        freqQ.remove(curr);
        
        if(freqQ.head.next == freqQ.tail)
            minFreq++;
        
        curr.freq = curr.freq + 1;
        if(freqMap.get(curr.freq) == null)
            freqMap.put(curr.freq, new Dequeue());
        
        Dequeue newFreqQ = freqMap.get(curr.freq);
        newFreqQ.insert(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        if(capacity == 0)
            return;
        if(cache.containsKey(key)){
            get(key);
            cache.get(key).val = value;
            return;
        }
        if(cache.size() == capacity){
            Dequeue minFreqQ = freqMap.get(minFreq);
            int removeKey = minFreqQ.removeFirst();
            cache.remove(removeKey);
        }
        DeNode curr = new DeNode(key, value, 0);
        
        if(freqMap.get(curr.freq) == null)
            freqMap.put(curr.freq, new Dequeue());
        Dequeue newFreqQ = freqMap.get(curr.freq);
        newFreqQ.insert(curr);
        cache.put(key, curr);
        minFreq = 0;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFU obj = new LFU(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */