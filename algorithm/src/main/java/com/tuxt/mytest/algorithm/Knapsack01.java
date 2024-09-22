package com.tuxt.mytest.algorithm;

/**
 * 葡萄的重量为2，价值为3元。矿泉水的重量为3，价值为5元。西瓜的重量为4，价值为6元。而我们的背包能放的最大容量是6。背包里面应该装什么东西能够确保价值最大？
 */
public class Knapsack01 {
    // 动态规划方法解决01背包问题，并追踪选择的物品
    public static int knapsack(int W, int[] weight, int[] value, int n) {
        int[][] dp = new int[n + 1][W + 1];
        boolean[][] keep = new boolean[n + 1][W + 1]; // 用于追踪是否选择了物品

        for (int i = 1; i <= n; i++) { //i 从 1 开始循环到 n（包含 n），其中 n 是物品的数量。这个循环遍历所有物品。
            for (int j = 1; j <= W; j++) { //j 从 1 开始循环到背包的最大容量 W（包含 W），这个循环遍历所有可能的背包重量。
                if (weight[i - 1] <= j) { //这行代码检查当前物品（第 i 个物品）的重量是否小于或等于当前考虑的背包重量 j。如果是，那么这个物品可以考虑放入背包中。
                    /*如果当前物品可以放入背包（即不超过背包容量），则检查两种情况：

                    不放当前物品，保持背包重量为 j 的最大价值（dp[i - 1][j]）。
                    放当前物品，加上当前物品的价值和剩余重量（j - weight[i - 1]）能达到的最大价值（value[i - 1] + dp[i - 1][j - weight[i - 1]]）。*/
                    if (value[i - 1] + dp[i - 1][j - weight[i - 1]] > dp[i - 1][j]) {
                        dp[i][j] = value[i - 1] + dp[i - 1][j - weight[i - 1]];
                        keep[i][j] = true;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 输出最大价值
        int max价值 = dp[n][W];
        System.out.println("背包里物品的最大价值为: " + max价值);

        // 追踪选择的物品
        int w = W;
        for (int i = n; i > 0; i--) {
            if (keep[i][w]) {
                System.out.println("选择了物品 " + (i-1) + " (重量: " + weight[i-1] + ", 价值: " + value[i-1] + ")");
                w -= weight[i-1];
            }
        }
        return max价值;
    }

    public static void main(String[] args) {
        int[] weight = {2, 3, 4}; // 物品的重量
        int[] value = {3, 5, 6};  // 物品的价值
        int W = 6;                // 背包的最大容量
        int n = weight.length;    // 物品的数量

        knapsack(W, weight, value, n);
    }
}