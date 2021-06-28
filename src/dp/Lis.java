package dp;

import java.util.Arrays;

public class Lis {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 2, 4, 3};
        int lis = lis(arr);
        System.out.println(lengthOfLIS(arr));
        System.out.println(lis);
    }

    static int lis(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;
    }

    static int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            //搜索左侧边界的二分搜索
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            //没有找到合适的堆，新建
            if (left == piles) piles++;
            top[left] = poker;
        }
        //堆就是lis长度
        return piles;
    }
}
