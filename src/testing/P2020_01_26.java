package testing;

import subject.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class P2020_01_26 {
    /**
     * 动态规划问题
     * 使用记忆化搜索解决
     * 设c[i]为从A[i]个开始的集合长度
     */
    static class Subjectxx {
        //        public int arrayNesting(int[] nums) {
//            int[] c = new int[nums.length];
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < c.length; i++) {
//                c[i] = searchPath(nums, i, new HashSet<>(), c);
//                max = Math.max(c[i], max);
//            }
//            return max;
//        }
//
//        int searchPath(int[] nums, int index, Set<Integer> sets, int[] memory) {
//            if (sets.contains(index))
//                return -1;
//            if (memory[index] != 0)
//                return memory[index];
//            sets.add(index);
//            return memory[index] = searchPath(nums, nums[index], sets, memory) + 1;
//        }

        public int arrayNesting(int[] nums) {
            int[] c = new int[nums.length];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < c.length; i++) {
                //不可能出现两个位置跳向同一个新的位置,因此set可以省略为初始索引
                c[i] = searchPath(nums, nums[i], i, c) + 1;
                max = Math.max(c[i], max);
            }
            return max;
        }

        int searchPath(int[] nums, int index, int start, int[] memory) {
            if (index == start)//说明回到了原点
                return -1;
            if (memory[index] != 0)
                return memory[index];
            return memory[index] = searchPath(nums, nums[index], start, memory) + 1;
        }
        //暂时不实现了,还有问题
//        public int arrayNesting(int[] nums) {
//            /**
//             * 因为数组nums中包含的元素不可能>=nums.length
//             * 因此可以直接省略c数组,而是在nums中直接设置为 c[i]+nums.length
//             */
////            int[] c = new int[nums.length];
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < nums.length; i++) {
//                //不可能出现两个位置跳向同一个新的位置,因此set可以省略为初始索引
////                nums[i] = searchPath(nums, nums[i], i) + 1;
//                int cur = nums[i];
//                int count = 0;
//                do {
//                    count++;
//                    cur = nums[cur];
//                    if (cur > nums.length) {
//                        count += cur - nums.length;
//                        break;
//                    }
//                } while (cur != nums[i]);
//                nums[i] = count + nums.length;
//                max = Math.max(nums[i] - nums.length, max);
//            }
//            return max;
//        }

//        int searchPath(int[] nums, int index, int start) {
//            if (index == start)//说明回到了原点
//                return nums.length - 1;
//            if (index >= nums.length)//已经搜索过了的情况,
//                return index - 1;
//            return searchPath(nums, nums[index], start) + 1;
//        }
    }

    /**
     * 简单
     * 层次遍历树后,间隔反转
     */
    static class Subjectxx2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> layers = new LinkedList<>();
            if (root == null)
                return layers;
            eachLayer(layers, 0, root);
            boolean left = true;
            for (List<Integer> layer : layers) {
                if (!left)
                    Collections.reverse(layer);
                left = !left;
            }
            return layers;
        }

        void eachLayer(List<List<Integer>> layers, int index, TreeNode node) {
            List<Integer> layer;
            if (index >= layers.size()) {
                layer = new LinkedList<>();
                layers.add(layer);
            } else {
                layer = layers.get(index);
            }
            layer.add(node.val);
            if (node.left != null)
                eachLayer(layers, index + 1, node.left);
            if (node.right != null)
                eachLayer(layers, index + 1, node.right);
        }

    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        System.out.println(new Subjectxx().arrayNesting(nums));
    }
}
