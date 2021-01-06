import java.util.*;
class Node{
    int key, val;
    Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
class LRUCache {
    Deque<Node> deque;
    Map<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        deque = new LinkedList<Node>();
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.get(key) != null){
            Node temp = map.get(key);
            deque.remove(temp);
            deque.addFirst(temp);
            return temp.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(get(key) != -1){
            map.get(key).val = value;
        }
        else{
            if(map.size() == capacity){
                map.remove(deque.pollLast().key);
            }
            Node newnode = new Node(key, value);
            deque.addFirst(newnode);
            map.put(key, newnode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */