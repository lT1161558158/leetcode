package subject;

import java.util.Stack;

/**
 * 数组中的每个数对于结果的贡献值为p(j),j为数的下标
 * 则有结果r为
 * r=sum(p(j)),j=[0,n]
 * <p>
 * 设c为以数A[j]为最右最小值的数组个数.因为数字是可以重复的,为了避免出现多次计算
 * 则有p(j)=c*A[j]
 * 令i为以数A[j]为最右最小值的最大子数组的左边界,k为右边界.
 * 则c=(j-i+1)*(k-j+1)
 * <p>
 * 关键点 单调栈.
 */
public class Subject907 {

//    public int sumSubarrayMins(int[] A) {
//        int mod = (int) (1e9 + 7);
//        int res = A[0];
//        int n = A.length;
//        /**
//         * 令dp[i]为以i为结尾的数组的最小值之和
//         */
//        int[] dp = new int[n];
//        dp[0] = A[0];
//        for (int i = 1; i < n; i++) {
//            if (A[i] >= A[i - 1])//新增一个大于等于最小值的数,则不会对原有的结果产生影响,因此新的值只需要新增当前值即可
//                dp[i] = dp[i - 1] + A[i];
//            else {//A[i]<A[i-1],新的值成为了最小值
//                int j = i - 1;
//                while (j >= 0 && A[i] < A[j]) --j;//向前查找到一个比A[i]还要小的值.
//                //[j,i]这段以A[i]为最小值,<0表示找到了数组的最左端,因此子数组数量即为i+1.
//                dp[i] = (j < 0) ? (i + 1) * A[i] : (dp[j] + (i - j) * A[i]);//在中途找到了更小值A[j],则使用先设置dp[i]=dp[j],然后加上新增的子数组数量(i-j)与当前最小值的乘积,即新增的子数组的最小值之和,因为子数组的数量为i+1.
//            }
//            res = (res + dp[i]) % mod;
//        }
//        return res;
//    }

//    public int sumSubarrayMins(int[] A) {
//        int mod = (int) (1e9 + 7);
//        int res = 0;
//        int n = A.length;
//
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        /**
//         * 令dp[i+1]为以i为结尾的数组的最小值之和
//         */
//        int[] dp = new int[n + 1];
//        for (int i = 0; i < n; i++) {
//            while (stack.peek() != -1 && A[i] <= A[stack.peek()])//如果栈中还有值,并且栈顶的值>=当前值,则一直出栈.
//                stack.pop();
//            dp[i + 1] = (dp[stack.peek() + 1]) + (i - stack.peek()) * A[i] % mod;//当前栈顶的值为前面的最小index设为t,计算方法一致,找到dp[t+1],并且加上新增的子数组数量与当前最小值的乘积,即新增的子数组的最小值之和
//            stack.push(i);
//            res = (res + dp[i + 1]) % mod;
//        }
//        return res;
//    }

    public int sumSubarrayMins(int[] A) {
        int mod = (int) (1e9 + 7);
        int res = 0;
        int n = A.length;

        Stack<Integer> stack = new Stack<>();
        /**
         * 搜索过程如下
         * 如果遇到了新的数导致了数据向下移动,则说明新数就是V的右边界
         * 分段计算
         * 每次遇到有新的最小值输入,则需要获取上次的最小值,然后加上这次新输入的最小值作为数组末端的数组最小值之和
         * 子数组的最小值之和为len*min
         *
         *
         * 需要记忆的就使用栈
         */
        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? Integer.MIN_VALUE : A[i];//最后一次遍历,赋值为0.
            while (!stack.isEmpty() && cur < A[stack.peek()]) {//当前数小于栈顶数,则说明找到了右边界,因为再往右的以值为尾端的子数组都将以当前值作为最小值了
                int index = stack.pop();//前面一个比较小的数位置
                int left = index - (stack.isEmpty() ? -1 : stack.peek());//如果栈是空的,那么左边界为数组边界,否则左边界为再前面一个比较小的数字
                int right = i - index;//右边子数组的大小
                res = (res + A[index] * left * right) % mod;// left * right 为 A[index] 为最小值的所有子数组数量
            }
            stack.push(i);
        }
        return res;
    }
//    public int sumSubarrayMins(int[] A) {
//        int max = (int) (Math.pow(10, 9) + 7);
//        int n = A.length;
//        //设prev[i]为数A[i]为最右最小值的最大子数组的左边界.
//        int[] prev = new int[n];
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < n; i++) {
//            while (!stack.isEmpty() && A[i] <= A[stack.peek()])
//                stack.pop();
//            prev[i] = stack.isEmpty() ? -1 : stack.peek();
//            stack.push(i);
//        }
//        stack.clear();
//        //设next[i]为数A[i]为最右最小值的最大子数组的右边界.
//        int[] next = new int[n];
//        for (int i = n - 1; i >= 0; --i) {
//            while (!stack.isEmpty() && A[i] < A[stack.peek()])
//                stack.pop();
//            next[i] = stack.isEmpty() ? n : stack.peek();
//            stack.push(i);
//        }
//        long ans = 0;
//        for (int i = 0; i < n; i++) {
//            ans += (i - prev[i]) * (next[i] - i) % max * A[i] % max;
//            ans %= max;
//        }
//        return (int) ans;
//
//    }


    public static void main(String[] args) {
        int[] arr = {3,5,2};
        Subject907 subject907 = new Subject907();
        System.out.println(subject907.sumSubarrayMins(arr));
    }
}
