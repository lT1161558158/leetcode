package subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路:
 * 计算区域内出现次数为奇数次的字母的个数c,若c<=2*k+1则一定可以变成回文串
 */
public class Subject1177 {

    //    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
//        List<Boolean> result = new ArrayList<>(queries.length);
//        for (int[] query : queries) {
//            int[] chars = new int[26];
//            int l = query[0];
//            while (l <= query[1]) {
//                int index = s.charAt(l++) - 'a';
//                chars[index] = (chars[index] + 1) & 1;
//            }
//            int count = 0;
//            for (int aChar : chars) {
//                if (aChar != 0)
//                    ++count;
//            }
//            result.add(count <= query[2] * 2 + 1);
//        }
//        return result;
//    }
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        /**
         * 设int中的右26位表示z-a,若二进制位为0则表示字母出现了偶数次,为1则表示字母出现了奇数次.
         * 设 mem[i]为[0,i]中字母的出现频率
         * 则[i,j]中字母出现的奇数次的个数为mem[j]异或mem[i]后值的二进制表示中1的个数
         */
        List<Boolean> ans = new ArrayList<>();
        int[] odds = new int[s.length() + 1]; // odds[i]: within range [0...i) of s, the jth bit of odd[i] indicates even/odd of the count of (char)(j + 'a').
        for (int i = 0; i < s.length(); ++i)
            odds[i + 1] = odds[i] ^ 1 << s.charAt(i) - 'a';
        for (int[] q : queries)
            ans.add(Integer.bitCount(odds[q[1] + 1] ^ odds[q[0]]) / 2 <= q[2]); // odds[q[1] + 1] ^ odds[q[0]] indicates the count of (char)(i + 'a') in substring(q[0], q[1] + 1) is even/odd.
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcda";
        int[][] queries = {{0, 3, 1}};
        System.out.println(new Subject1177().canMakePaliQueries(s, queries));
    }

}
