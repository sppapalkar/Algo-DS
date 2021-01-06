/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
*/
import java.util.*;
class MyComparator2 implements Comparator<Integer>{
    int[] nums;
    MyComparator2(int[] nums){
        this.nums = nums;       
    }
    public int compare(Integer a, Integer b){
        if(nums[a] == nums[b])
            return a-b;
        else
            if(nums[a]>nums[b])
                return 1;
            return -1;
    }
}
public class SlidingWindowMedian {
    private double getMedian(int[] nums, TreeSet<Integer> firstHalf, TreeSet<Integer> secondHalf){
        System.out.println(firstHalf+ " "+secondHalf);
        if((firstHalf.size() + secondHalf.size()) % 2 == 0){
            return ((double)nums[firstHalf.last()] + (double)nums[secondHalf.first()])/2;
        }
        else
            return nums[firstHalf.last()];
    }
    private void balanceSet(TreeSet<Integer> firstHalf, TreeSet<Integer> secondHalf){
        if(firstHalf.size() - secondHalf.size() >= 2)
            secondHalf.add(firstHalf.pollLast());
        else if(secondHalf.size() > firstHalf.size())
            firstHalf.add(secondHalf.pollFirst());
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeSet<Integer> firstHalf = new TreeSet<Integer>(new MyComparator2(nums));
        TreeSet<Integer> secondHalf = new TreeSet<Integer>(new MyComparator2(nums));
        double[] solution = new double[(nums.length - k) + 1];
        int start = 0; int end = 0; int cnt = 0;
        
        while(end < nums.length){
            if(end-start == k){
                solution[cnt] = getMedian(nums, firstHalf, secondHalf);
                cnt++;
                if(nums[start] <= nums[firstHalf.last()]){
                    // System.out.println("remove from first half"+start);
                    if(!firstHalf.remove(start))
                        secondHalf.remove(start);
                }
                else{
                    // System.out.println("remove "+start);
                    if(!secondHalf.remove(start));
                        firstHalf.remove(start);
                }
                start++;
                balanceSet(firstHalf, secondHalf);
            }
            else{
                if(firstHalf.size() == 0 || nums[end] < nums[firstHalf.last()])
                    firstHalf.add(end);
                else
                    secondHalf.add(end);
                balanceSet(firstHalf, secondHalf);
                end++;
            }
        }
        solution[cnt] = getMedian(nums, firstHalf, secondHalf);
        return solution;
    }

    public static void main(String[] args) {
        SlidingWindowMedian ob = new SlidingWindowMedian();
        double[] solution = ob.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(solution.toString());
    }
}
