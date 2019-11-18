package subject;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Subject144 {
    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     *  示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution {
        Stack<TreeNode> stack=new Stack<>();
        public List<Integer> preorderTraversal(TreeNode root){
            List<Integer> result=new ArrayList<>();
            search(root,result);
            return result;
        }
        void search(TreeNode node,List<Integer> values){
            if (node==null)
                return;
            values.add(node.val);
            search(node.left,values);
            search(node.right,values);
        }
        List<Integer> search(TreeNode node){
            List<Integer> result=new ArrayList<>();
            while (node!=null || !stack.isEmpty()){
                while (node!=null){
                    result.add(node.val);
                    stack.push(node);
                    node=node.left;
                }
                if (!stack.isEmpty()){
                    node=stack.pop();
                    node=node.right;
                }
            }
            return result;
        }


    }
}
