/*
    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
*/
import java.util.*;
public class RemoveParenthesis {
    private void helper(String s, int startI, int startJ, char openParen, char closeParen, List<String> soln){
        int open = 0, close = 0;
        int i, j;
        
        for(i = startI; i<s.length(); i++){
            char ch = s.charAt(i); 
            if(ch == openParen)
                open++;
            if(ch == closeParen)
                close++;
            if(close > open){
                for(j = startJ; j<=i; j++){
                    if(s.charAt(j) == closeParen && (j == startJ || s.charAt(j) != s.charAt(j-1))){
                        helper(s.substring(0,j) + s.substring(j+1), i, j, openParen, closeParen, soln);
                    }
                }
                return;
            }
        }
        if(openParen == '(')
            helper(new StringBuilder(s).reverse().toString(), 0, 0, closeParen, openParen, soln);
        else
            soln.add(new StringBuilder(s).reverse().toString());        
    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> soln = new ArrayList<>();
        if(s.length() == 0){
            soln.add("");
            return soln;
        }
        helper(s, 0, 0, '(', ')', soln);
        return soln;
    }
}
