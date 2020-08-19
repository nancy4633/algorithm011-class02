package com.xunlianying8.zuoye1;

// 第三遍
// 给定一个非负整数num。对于0≤i≤num范围中的每个数字i ，计算其二进制数中的1的数目并将它们作为数组返回。
public class countBits338 {
    /**
     * 遍历
     * 时间复杂度：O(nk) - 28.53% - 对于每个整数x，我们需要O(k)次操作，其中k是x的位数。
     * 空间复杂度：O(n) - 80.29%  - 我们需要O(n)的空间来存储计数结果。如果排除这一点，就只需要常数空间。
     * 优点:
     * 缺点:
     *
     * @param num
     * @return
     */
    public int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; ++i) {
            result[i] = popcount(i);
        }
        return result;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count) {
            x &= x - 1; //zeroing out the least significant nonzero bit
        }
        return count;
    }

    /**
     * 动态规划+最高有效位
     * 时间复杂度：O(n)。对每个整数x，我们只需要常数时间。
     * 空间复杂度：O(n)。我们需要O(n)的空间来存储技术结果。如果排除这一点，就只需要常数空间。
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while (i < b && i + b <= num) {
                result[i + b] = result[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return result;
    }

    /**
     * 动态规划+最低有效位
     * 时间复杂度：O(n) - 99.76% - 对每个整数x，我们只需要常数时间。
     * 空间复杂度：O(n) - 46.63% - 与方法二相同。
     *
     * @param num
     * @return
     */
    public int[] countBits3(int num) {
        int[] result = new int[num + 1];
        for (int pos = 1; pos <= num; pos++) {
            result[pos] = result[pos >> 1] + (pos & 1); // 位运算// 最后的括号一定要加！！！
        }
        return result;
    }

    /**
     * 动态规划+最后设置位
     * 时间复杂度：O(n)O(n)。 与方法三相同。
     * 空间复杂度：$$(n)$。与方法三相同。
     * 优点：这个方法更属于传统的位运算，很赞。
     *
     * @param num
     * @return
     */
    public int[] countBits4(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            result[i] = result[i & (i - 1)] + 1; // 位运算
        }
        return result;
    }
}
