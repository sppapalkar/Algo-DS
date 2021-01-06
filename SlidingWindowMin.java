/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the min sliding window.
*/

import java.util.*;
public class SlidingWindowMin {
    private int findSegment(int[] space, int x){
        Deque<Integer> deque = new LinkedList<Integer>();
        if(space.length==0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int index = 0;
        while(index<space.length){
            while(deque.size() > 0 && deque.peekFirst() < (index-x)+1){
                deque.pollFirst();
            }
            while(deque.size() > 0 && space[deque.peekLast()] > space[index]){
                deque.pollLast();
            }
            deque.add(index);
            
            if(index >=x-1){
                System.out.println(space[deque.peekFirst()]);
                max = Math.max(max, space[deque.peekFirst()]);
            }
            index++;
        }
        return max;
    }
    public static void main(String[] args) {
        SlidingWindowMin ob = new SlidingWindowMin();
        int[] space = {3,4,5,1,2,4,3,0,5,7};
        ob.findSegment(space, 3);
    }
}
