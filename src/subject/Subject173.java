package subject;

import java.util.Stack;

public class Subject173 {

    class BSTIterator {

        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            if (root == null)
                return;
            stack.push(root);
            while (root.left != null) {
                stack.push(root.left);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        /**
         * 仔细分析一下，该循环只有在节点有右子树的时候才需要进行，也就是不是每一次操作都需要循环的，循环的次数加上初始化的循环总共会有O(n)次操作，均摊到每一次next()的话平均时间复杂度则是O(n)/n=O(1)，因此可以确定该实现方式满足O(1)的要求。
         *
         * 这种分析方式称为摊还分析，详细的学习可以看看**《算法导论》- 第17章 摊还分析**
         *
         * 作者：user5707F
         * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator/solution/nextshi-jian-fu-za-du-wei-o1-by-user5707f/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * @return
         */
        /**
         * 中序遍历的过程拆分到每次执行next时
         * 先访问左节点,在访问当前节点,最后访问右节点
         *
         * @return
         */
        public int next() {
            TreeNode pop = stack.pop();//栈顶为最小节点
            int val = pop.val;
            if (pop.right != null) {//若当前节点存在右节点
                stack.push(pop.right);//将右节点放入栈顶
                pop = pop.right;
                while (pop.left != null) {//若该节点存在左节点,则将将左子树压入栈
                    stack.push(pop.left);
                    pop = pop.left;
                }
            }
            return val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.empty();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
