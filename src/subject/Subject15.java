package subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subject15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//排序,如果不排序的搜索数组,需要搜索所有的值,难以去重.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                break;
            if (i>0 && nums[i]==nums[i-1])//跳过重复的结果
                continue;
            int l = i + 1, r = nums.length - 1;//如果l从0开始,则会搜索到相同的解.
            while (l < r) {
                int c = nums[i] + nums[l] + nums[r];
                if (c == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[l]);
                    item.add(nums[r]);
                    result.add(item);
                    //跳过重复解
                    while (l < r && nums[l] == nums[l + 1])
                        l++;
                    while (l < r && nums[r] == nums[r - 1])
                        r--;
                    l++;
                    r--;
                } else if (c < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }
}
