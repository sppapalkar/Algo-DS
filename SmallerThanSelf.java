/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
*/
import java.util.*;
public class SmallerThanSelf {
    private void sort(int[] nums, int[] indexes, int[] count, int lb, int ub){
        if(lb < ub){
            int mid = (lb+ub)/2;
            sort(nums,indexes, count, lb, mid);
            sort(nums,indexes, count, mid+1, ub);
            mergesort(nums,indexes, count, lb, mid, ub);
        }
    }
    private void mergesort(int[] nums, int[] indexes, int[] count, int lb, int mid, int ub){
        int[] newIndexes = new int[(ub-lb)+1];
        int left, right, k, rightCount = 0;
        left = lb;
        right = mid+1;
        k = 0;
        while(left <= mid && right<=ub){
            if(nums[indexes[left]] >  nums[indexes[right]]){
                newIndexes[k] = indexes[right];
                rightCount++;
                right++;
                k++;
            }
            else{
                newIndexes[k] = indexes[left];
                k++;
                count[indexes[left]] += rightCount;
                left++;
            }
        }
        while(right <= ub){
            newIndexes[k] = indexes[right];
            rightCount++;
            right++;
            k++;
        }
        while(left <= mid){
            newIndexes[k] = indexes[left];
            k++;
            count[indexes[left]] += rightCount;
            left++;
        }
        for(k = 0; k<newIndexes.length; k++){
            indexes[lb+k] = newIndexes[k];
        }
    } 
    public List<Integer> countSmaller(int[] nums) {
        int[] indexes = new int[nums.length];
        int[] count = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            indexes[i] = i;
        }
        sort(nums, indexes, count, 0, nums.length-1);
        List<Integer> soln = new ArrayList<>();
        for(int c : count){
            soln.add(c);
        }
        return soln;
    }
}
