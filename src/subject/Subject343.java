package subject;


/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject343 {
    int max(int a,int ...b){
        int max=a;
        for (int i : b) {
            max=Math.max(i,max);
        }
        return max;
    }
    public int integerBreak(int n) {
        return dp(n);
    }
    int dp(int n){
        int[] dp = new int[n + 1];
        dp[1]=1;
        for (int i = 2; i < n+1; i++) {
            for (int j = 1; j < i; j++) {
                dp[i]=max(j*(i-j),dp[i-j]*j,dp[i]);//还需要和上一次的结果比较
            }
        }
        return dp[n];
    }

    /**
     * 暴力搜索
     * @param n
     * @return
     */
    int forceSearch(int n){
        if (n==1)
            return 1;
        int max=Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max= max(max,(n-i)*i,integerBreak(n-i)*i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Subject343().integerBreak(10));
    }
}
