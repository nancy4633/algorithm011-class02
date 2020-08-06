package com.xunlianying4.xiti2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
// 思路：
// 做了好久的题，都没有用到贪心算法，还是挺开心的，终于消灭了一种算法～
//
public class CoinChange322 {

    /***
     * 搜索回溯 - 超时
     * 优点: 简单，容易想到。
     * 缺点: 超时。
     * 时间复杂度：O(S^n) 在最坏的情况下，复杂性是硬币数量的指数
     * 空间复杂度：O(n)，在最坏的情况下，递归的最大深度是n。因此，我们需要系统递归堆栈使用O(n)的空间。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        return coinChange1(0, coins, amount);
    }

    private int coinChange1(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange1(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    /***
     * 动态规划（自上而下） - 8%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange11(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange11(coins, amount, new int[amount]);
    }

    private int coinChange11(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange11(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /***
     * 动态规划（自下而上） - 60%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange111(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /***
     * 动态规划 - 31%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1111(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int cost = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    if (f[i - coins[j]] != Integer.MAX_VALUE)
                        cost = Math.min(cost, f[i - coins[j]] + 1);
                }
            }
            f[i] = cost;
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }

    /***
     * 动态规划 - 86%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange11111(int[] coins, int amount) {
        // 自底向上的动态规划
        if (coins.length == 0) {
            return -1;
        }

        // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] memo = new int[amount + 1];
        // 给memo赋初值，最多的硬币数就是全部使用面值1的硬币进行换
        // amount + 1 是不可能达到的换取数量，于是使用其进行填充
        Arrays.fill(memo, amount + 1);
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    // memo[i]有两种实现的方式，
                    // 一种是包含当前的coins[i],那么剩余钱就是 i-coins[i],这种操作要兑换的硬币数是 memo[i-coins[j]] + 1
                    // 另一种就是不包含，要兑换的硬币数是memo[i]
                    memo[i] = Math.min(memo[i], memo[i - coins[j]] + 1);
                }
            }
        }

        return memo[amount] == (amount + 1) ? -1 : memo[amount];
    }

    /***
     * 动态规划 - 44%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange111111(int[] coins, int amount) {
        // 给 0 占位
        int[] dp = new int[amount + 1];

        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != amount + 1) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        if (dp[amount] == amount + 1) {
            dp[amount] = -1;
        }
        return dp[amount];

    }

    /***
     * 完全背包  -93%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1111111(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[amount] == amount + 1) {
            dp[amount] = -1;
        }
        return dp[amount];
    }

    /**
     * BFS - 20%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange11111111(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        visited[amount] = true;
        queue.offer(amount);

        // 排序是为了加快广度优先遍历过程中，对***面值的遍历，起到剪枝的效果
        Arrays.sort(coins);

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer head = queue.poll();
                for (int coin : coins) {
                    int next = head - coin;
                    // 只要遇到 0，就找到了一个最短路径
                    if (next == 0) {
                        return step;
                    }

                    if (next < 0) {
                        // 由于 coins 升序排序，后面的面值会越来越大，剪枝
                        break;
                    }

                    if (!visited[next]) {
                        queue.offer(next);
                        // 添加到队列的时候，就应该立即设置为 true
                        // 否则还会发生重复访问
                        visited[next] = true;
                    }
                }
            }
            step++;
        }
        // 进入队列的顶点都出队，都没有看到 0 ，就表示凑不出***
        return -1;
    }

    /***
     * 记忆化递归（同 动态规划）- 17%
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange111111111(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        Arrays.sort(coins);
        return dfs(coins, amount, memo);
    }

    private int dfs(int[] coins, int amount, int[] memo) {
        int res = Integer.MAX_VALUE;
        if (amount == 0) {
            return 0;
        }

        if (memo[amount] != -2) {
            return memo[amount];
        }

        for (int coin : coins) {
            if (amount - coin < 0) {
                break;
            }

            int subRes = dfs(coins, amount - coin, memo);
            if (subRes == -1) {
                continue;
            }
            res = Math.min(res, subRes + 1);
        }
        return memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
    }


    /**
     * DFS+剪枝 - 第一快 - 99%
     * 经验之谈：剪枝真的是提高效率的最好方法
     * 优点
     * 缺点
     * 时间复杂度：O()
     * 空间复杂度：O()
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int ans = Integer.MAX_VALUE;

    public void dfs(int[] coins, int index, int amount, int cnt) {
        if (index < 0) {
            return;
        }
        for (int c = amount / coins[index]; c >= 0; c--) {
            int na = amount - c * coins[index];
            int ncnt = cnt + c;
            if (na == 0) {
                ans = Math.min(ans, ncnt);
                break;//剪枝1
            }
            if (ncnt + 1 >= ans) {
                break; //剪枝2
            }
            dfs(coins, index - 1, na, ncnt);
        }
    }
}
