/*
You are given a list of strings words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language, and return it. If the given input is invalid, return "". If there are multiple valid solutions, return any of them.
*/

import java.util.*;
class DfsNode{
    char letter;
    int state;
    HashSet<Character> neighbour;
    DfsNode(char letter){
        this.letter = letter;
        this.state = 0;
        this.neighbour = new HashSet<>();
    }
}
public class AlienDictionary {
    private void dfs(Map<Character, DfsNode> graph, DfsNode current, StringBuilder solution){
        current.state = 1;
        for(char adj : current.neighbour){
            if(graph.get(adj).state == 0){
                dfs(graph, graph.get(adj), solution);
            }
        }
        current.state = 2;
        solution.append(current.letter);
    }
    public String alienOrder(String[] words){
        Map<Character, DfsNode> graph = new HashMap<>();
        Set<Character> startNodes = new HashSet<>();
        StringBuilder solution = new StringBuilder();
        for(int i = 0; i<words.length-1; i++){
            for(int index = 0; index < words[i].length(); index++){
                if(index==words[i+1].length())
                    break;
                if(graph.get(words[i].charAt(index)) == null){
                    graph.put(words[i].charAt(index), new DfsNode(words[i].charAt(index)));
                    startNodes.add(words[i].charAt(index));
                }
                if(graph.get(words[i+1].charAt(index)) == null){
                    graph.put(words[i+1].charAt(index), new DfsNode(words[i+1].charAt(index)));
                    startNodes.add(words[i+1].charAt(index));
                }
                if(words[i].charAt(index) != words[i+1].charAt(index)){
                    graph.get(words[i].charAt(index)).neighbour.add(words[i+1].charAt(index));
                    startNodes.remove(words[i+1].charAt(index));
                }
            }
        }
        for(char startNode : startNodes){
            dfs(graph, graph.get(startNode), solution);
        }
        return solution.reverse().toString();
    }
    public static void main(String[] args) {
        AlienDictionary ob = new AlienDictionary();
        String[] words = {"ab", "adc",};
        System.out.println(ob.alienOrder(words));
    }
}