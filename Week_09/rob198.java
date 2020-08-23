package com.xunlianying9.zuoye1;

// 第一遍
// 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
// 提示：
// 0 <= nums.length <= 100
// 0 <= nums[i] <= 400
public class rob198 {

    /**
     * 动态规划（自底向上） + 新建一维数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.33%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];
        int[] result = new int[length];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2] + nums[i]);
        }
        return result[length - 1];
    }

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }
        return dp[N];
    }

    /**
     * 动态规划（自底向上） + 两个变量 - 对于降维的解法还是要多练，尤其是把多维数组转换为两个变量的方法，太精妙了。
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.18%
     * 优点:
     * 缺点:
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int prev = 0;
        int curr = 0;
        for (int i : nums) {
            int temp = Math.max(curr, prev + i);
            prev = curr;
            curr = temp;
        }
        return curr;
    }
}
