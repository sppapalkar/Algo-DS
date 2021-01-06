/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
*/

import java.util.*;
public class WordBreak {
    private List<String> DFS(String s, List<String> wordDict, Map<String, List<String>> cache){
        if(cache.containsKey(s))
            return cache.get(s);
        List<String> res = new ArrayList<>();
        if(s.length() == 0){
            res.add("");
            return res;
        }
        for(String word : wordDict){
            if(s.startsWith(word)){
                List<String> sub = DFS(s.substring(word.length()), wordDict, cache);
                for(String subWord : sub){
                    if(subWord.length() == 0)
                        res.add(word);
                    else
                        res.add(word + " " + subWord);
                }
            }
        }
        cache.put(s, res);
        return res;
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, List<String>>());
    }
}
