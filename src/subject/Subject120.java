package subject;

import java.util.List;

public class Subject120 {
    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     * <p>
     * 例如，给定三角形：
     * <p>
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * <p>
     * 说明：
     * <p>
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
//    private int[][] minimums;
//
//    public int minimumTotal(List<List<Integer>> triangle) {
//        minimums = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
//        for (int[] minimum : minimums) {
//            for (int i = 0; i < minimum.length; i++) {
//                minimum[i] = Integer.MAX_VALUE;
//            }
//        }
//        return minimumTotal(triangle, 0, 0);
//    }
//
//    private int minimumTotal(List<List<Integer>> triangle, int deep, int index) {
//        if (triangle.size() <= deep)
//            return 0;
//        List<Integer> layer = triangle.get(deep);
//        int now = layer.get(index);
//        if (triangle.size() <= deep + 1) {
//            return now;
//        } else {
//            if (minimums[deep + 1][index] == Integer.MAX_VALUE) {
//                minimums[deep + 1][index] = minimumTotal(triangle, deep + 1, index);
//            }
//            if (minimums[deep + 1][index + 1] == Integer.MAX_VALUE) {
//                minimums[deep + 1][index + 1] = minimumTotal(triangle, deep + 1, index + 1);
//            }
//            return now + Math.min(minimums[deep + 1][index], minimums[deep + 1][index + 1]);
//
//        }
//    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle==null || triangle.isEmpty())
            return 0;
        int row=triangle.size();
        int[] minimums = new int[row+1];
        for (int level = row-1; level >= 0; level--) {
            for (int i = 0; i < level+1; i++) {
                minimums[i]=triangle.get(level).get(i)+Integer.min(minimums[i],minimums[i+1]);
            }
        }
        return minimums[0];
    }
}
