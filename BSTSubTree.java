/*
Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
*/
public class BSTSubTree {
    int maxCount;
    BSTSubTree(){
        maxCount = 0;
    }
    private int[] helper(TreeNode root){
        int result[] = new int[2];
        if(root.left == null && root.right == null){
            result[0] = root.val;
            result[1] = 1;
        }
        else{
            int[] left = {Integer.MIN_VALUE, 0};
            int[] right = {Integer.MAX_VALUE, 0};
            
            if(root.left!=null)
                left = helper(root.left);
            if(root.right!=null)
                right = helper(root.right);

            result[0] = root.val;

            if(left[0]<root.val && root.val<right[0]){
                result[1] = left[1] + right[1] + 1;
            }
            else{
                result[1] = 0;
            }   
        }
        maxCount = Math.max(maxCount, result[1]);
        return result;
    }
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return maxCount;
    }
    public static void main(String[] args){
        // BSTSubtree ob = new BSTSubtree();
    }
}
