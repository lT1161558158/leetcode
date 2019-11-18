package explore.recursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {
    /**
     * 设到达n阶的方法数为f(n)
     * 则
     * 若n>1,则
     * f(n)=f(n-1)+f(n-2)
     * 若n==0,则
     * f(n)=1
     * 若n==1,则
     * f(1)=1
     */
    public int climbStairs(int n) {
        Map<Integer,Integer> cache=new HashMap<>();
        return fib(n,cache);
    }
    /**
     * 记忆化搜索
     */
    int fib(int N, Map<Integer,Integer> cache){
        if (N==0)
            return 1;
        if (N==1)
            return 1;
        return cache.computeIfAbsent(N,k->fib(N-1,cache)+fib(N-2,cache));//若键不存在,则对k使用函数f,生成v,并将k-v映射
    }
}
