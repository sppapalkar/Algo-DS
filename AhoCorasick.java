import java.util.*;
class AhoTrieNode{
    char ch;String op;
    Map<Character, AhoTrieNode> children;
    AhoTrieNode failure, dict;
    AhoTrieNode(char ch){
        this.ch = ch;
        children = new HashMap<>();
        failure = null;
        dict = null;
        op = "";
    }
} 
public class AhoCorasick {
    private AhoTrieNode createTrie(String[] wordDict){
        AhoTrieNode head = new AhoTrieNode('*');
        AhoTrieNode current, nextNode;
        for(String word : wordDict){
            current = head;
            for(int i =0; i< word.length(); i++){
                char ch = word.charAt(i);
                nextNode = current.children.get(ch);
                if(nextNode == null){
                    nextNode = new AhoTrieNode(ch);
                    current.children.put(ch, nextNode);
                }
                current = nextNode;
            }
            current.op = word;
        }
        return head;
    }
    private void addLinks(AhoTrieNode root){
        Queue<AhoTrieNode> queue = new LinkedList<>();
        AhoTrieNode curr, child, failure;
        root.failure = root;
        queue.add(root);
        
        while(queue.size() > 0){
            curr = queue.remove();
            for(char key : curr.children.keySet()){
                child = curr.children.get(key);
                failure = curr;
                while(failure.failure != failure){
                    failure = failure.failure;
                    if(failure.children.containsKey(child.ch)){
                        child.failure = failure.children.get(child.ch);
                        break;
                    }
                }
                if(child.failure == null){
                    child.failure = root;
                }
                queue.add(child);
            }
        }
    }
    private void search(String s, AhoTrieNode root){
        AhoTrieNode curr = root;

        for(char ch : s.toCharArray()){
            if(curr.op.length() > 0)
                System.out.println(curr.op);
            
            if(curr.children.containsKey(ch)){
                curr = curr.children.get(ch);
            }
            else{
                while(curr.failure != curr){
                    curr = curr.failure;
                    if(curr.children.containsKey(ch)){
                        curr = curr.children.get(ch);
                        break;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        AhoCorasick ob = new AhoCorasick();
        AhoTrieNode root = ob.createTrie(new String[]{"ACC", "ATC", "CAT", "GCG"});
        ob.addLinks(root);
        ob.search("CGATCG", root);
    }
}
