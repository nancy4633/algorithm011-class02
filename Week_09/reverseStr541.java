package com.xunlianying9.zuoye1;

// 第一遍：一开始题目的意思理解错了，如果面试的时候第一次见到这个题，那肯定错了，还好这次挽救了。
// 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
// 1.如果剩余字符少于 k 个，则将剩余字符全部反转。
// 2.如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
// 提示：
// 该字符串只包含小写英文字母。
// 给定字符串的长度和 k 在 [1, 10000] 范围内。
// 思路：
// 条件1和条件2如何合并到总体逻辑
public class reverseStr541 {
    /**
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 68.81%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr1(String s, int k) {
        char[] arr = s.toCharArray();
        for (int left = 0, step = 2 * k; left < arr.length; left += step) {
            int right = Math.min(arr.length - 1, left + k - 1);
            swap(arr, left, right);
        }
        return new String(arr);
    }

    private void swap(char[] arr, int left, int right) {
        while (left < right) {
            char swap = arr[left];
            arr[left++] = arr[right];
            arr[right--] = swap;
        }
    }

    /**
     * 时间复杂度:O() - 34.31%
     * 空间复杂度:O() - 79.13%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr2(String s, int k) {
        int i = 0;
        char[] ch = s.toCharArray();
        char mid;
        while (i < ch.length) {
            if (i % (2 * k) == 0) {
                if (i + k - 1 < ch.length) {
                    for (int j = 0; j < k / 2; j++) {
                        mid = ch[i + j];
                        ch[i + j] = ch[i + k - 1 - j];
                        ch[i + k - 1 - j] = mid;
                    }
                } else {
                    for (int j = 0; j < (ch.length - i) / 2; j++) {
                        mid = ch[i + j];
                        ch[i + j] = ch[ch.length - 1 - j];
                        ch[ch.length - 1 - j] = mid;
                    }
                }
            }
            i += k;
        }
        return new String(ch);
    }
}
