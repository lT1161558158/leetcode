package explore.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 */
public class PascalTriangle1 {
    /**
     * 要求第i层,则先求i-1层
     * 第一层可以直接求解
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows<1)
            return new ArrayList<>();
        return generateLayer(numRows-1);
    }
    List<List<Integer>> generateLayer(int i){
        if (i==0){
            List<List<Integer>> result=new ArrayList<>();
            result.add(Collections.singletonList(1));
            return result;
        }
        List<List<Integer>> result=generateLayer(i-1);
        List<Integer> before=result.get(i-1);
        List<Integer> now=new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            if (j==0 || i==j){
                now.add(1);
            }else{
                now.add(before.get(j)+before.get(j-1));
            }
        }
        result.add(now);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle1().generate(4));
    }

}
