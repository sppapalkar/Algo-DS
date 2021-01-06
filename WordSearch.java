/*
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
*/
import java.util.*;
class TrieNode{
    char ch;
    String word;
    Map<Character, TrieNode> children;
    TrieNode(char ch){
        this.ch = ch;
        this.word = "";
        children = new HashMap<Character, TrieNode>();
    }
}
public class WordSearch {
    private TrieNode createTrie(String[] words){
        TrieNode root = new TrieNode('*');
        TrieNode current, next;
        
        for(String word : words){
            current = root;
            for(char ch : word.toCharArray()){
                next = current.children.get(ch);
                if(next == null){
                    next = new TrieNode(ch);
                    current.children.put(ch, next);
                }
                current = next;
            }
            TrieNode tnode = new TrieNode('*');
            tnode.word = word;
            current.children.put('*',tnode);
        }
        return root;
    }
    private void dfs(char[][] board, TrieNode root, Set<String> solution, int row, int col){
        int[][] coords = {{1,0},{0,1},{-1,0},{0,-1}};
        if(row >= 0 && row < board.length && col>=0 && col<board[0].length && board[row][col] != '#'){
            // System.out.println("Row: "+row+" Col: "+col + " CH: "+board[row][col] + " Node: " +root.ch);
            char ch = board[row][col];
            board[row][col] = '#';
            TrieNode next = root.children.get(ch);
            if(next != null){
                // System.out.println("Next: "+next.ch);
                for(int[] coord : coords){
                    dfs(board, next, solution, row+coord[0], col+coord[1]);
                }   
            }
            board[row][col] = ch;
            if(next != null && next.children.containsKey('*')){
                solution.add(next.children.get('*').word);
            }
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> solution = new HashSet<>();
        // boolean visited[][] = new boolean[board.length][board[0].length];
        int i, j;
        if(words.length == 0)
            return new ArrayList<>();
        TrieNode root = createTrie(words);
        // System.out.println(root.children);
        for(i=0; i<board.length; i++){
            for(j=0; j<board[0].length; j++){
                if(root.children.get(board[i][j]) != null){
                    dfs(board, root, solution, i, j);
                }
            }
        }
        return new ArrayList<>(solution);
    }
}
