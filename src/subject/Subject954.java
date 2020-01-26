package subject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Subject954 {

//    public boolean canReorderDoubled(int[] arr) {
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = Math.abs(arr[i]);
//        Arrays.sort(arr);
//
//        Set<Integer> cases = new HashSet<>();
//        for (int i = 0; i < arr.length; i++) {
//            if (!cases.contains(i)) {
//                Comparator<Integer> comparator = arr[i] > 0 ? (a, b) -> b * 2 - a : (a, b) -> b - a * 2;
//                int caseIndex = binarySearch(arr, i + 1, arr.length - 1, arr[i], comparator);
//                if (caseIndex < 0 && !cases.contains(i))
//                    return false;
//                cases.add(caseIndex);
//            }
//        }
//        return true;
//    }
//
//    int binarySearch(int[] arr, int l, int r, int operate, Comparator<Integer> comparator) {
//        if (l > r)
//            return -1;
//        int mid = l + (r - l) / 2;
//        int origin = arr[mid];
//        if (comparator.compare(origin, operate) == 0)
//            return mid;
//        else if (comparator.compare(origin, operate) < 0)
//            return binarySearch(arr, l, mid - 1, operate, comparator);
//        else
//            return binarySearch(arr, mid + 1, r, operate, comparator);
//    }


    //    public boolean canReorderDoubled(int[] arr) {
//        if ((arr.length & 1) != 0)
//            return false;
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = Math.abs(arr[i]);
//        Arrays.sort(arr);
//        int l = 0, r = arr.length - 1;
//        long count = 0;
//        while (l < r)
//            count = arr[l++] * 2 - arr[r--];
//        return count == 0;
//    }
    public boolean canReorderDoubled(int[] arr) {
        if ((arr.length & 1) != 0)
            return false;
        Map<Integer, Integer> memory = new HashMap<>(arr.length);
        Integer[] newArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            memory.compute(arr[i], (k, v) -> v == null ? 1 : v + 1);//值,以及值出现的次数
            newArr[i] = arr[i];
        }
        Arrays.sort(newArr, Comparator.comparingInt(Math::abs));//按绝对值排序
        for (Integer x : newArr) {
            if (memory.get(x) == 0)//判断当前的数有没有用完,例如2x的值,在正确的情况下应该都是用完的,也就不会搜索2(2x)的值从而导致异常判断
                continue;
            if (memory.getOrDefault(2 * x, 0) == 0)//没有找到2倍值,则说明配对失败,找到了不能使用的二倍值,也是失败
                return false;
            memory.put(x, memory.get(x) - 1);
            memory.put(2 * x, memory.get(2 * x) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {-8, -4, -2, -1, 0, 0, 1, 2, 4, 8};
        System.out.println(new Subject954().canReorderDoubled(arr));
    }
}
