/*
    Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
*/
import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class UniqueBST {
    private List<TreeNode> buildTree(int lb, int ub){
        List<TreeNode> trees = new ArrayList<>();
        if(lb == ub){
            trees.add(null);
            return trees;
        }
        for(int i = lb; i<ub; i++){
            List<TreeNode> left = buildTree(lb, i);
            List<TreeNode> right = buildTree(i+1, ub);
            
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode newNode = new TreeNode(i);
                    newNode.left = l;
                    newNode.right = r;
                    trees.add(newNode);
                }
            }
        }
        return trees;
    }
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)
            return new ArrayList<>();
        return buildTree(1, n+1);
    }
    public static void main(String[] args) {
        UniqueBST ob = new UniqueBST();
        ob.generateTrees(5);
    }
}
