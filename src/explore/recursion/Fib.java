package explore.recursion;

import java.util.HashMap;
import java.util.Map;

public class Fib {

    public int fib(int N) {
        Map<Integer,Integer> cache=new HashMap<>();
        return fib(N,cache);
    }

    /**
     * 记忆化搜索
     */
    int fib(int N, Map<Integer,Integer> cache){
        if (N==0)
            return 0;
        if (N==1)
            return 1;
        return cache.computeIfAbsent(N,k->fib(N-1,cache)+fib(N-2,cache));//若键不存在,则对k使用函数f,生成v,并将k-v映射
    }
}
