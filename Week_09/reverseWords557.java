package com.xunlianying9.zuoye1;

import java.util.Arrays;
import java.util.stream.Collectors;

// 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序
// 提示：
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
public class reverseWords557 {
    /**
     * 时间复杂度:O(n) - 100.00% - 虽然有两层while，但只遍历了一遍。
     * 空间复杂度:O(n) - 93.82%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        char[] sentence = s.toCharArray();
        int i = 0, j = 0;
        while (j < sentence.length) {
            while (i < sentence.length && sentence[i] == ' ') ++i;
            while (j < sentence.length && sentence[j] != ' ') ++j;
            reverse(sentence, i, j - 1);
            i = j;
            ++j;
        }
        s = String.valueOf(sentence);
        return s;
    }

    private void reverse(char[] arr, int start, int end) {
        if (arr.length == 0) return;
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
    }

    /**
     * 双指针：字符串转数组，遍历数组，每碰到1次空格反转空格前的单词，因为最后一个单词后面没有空格，遍历结束后再反转1次最后一个单词
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O() - 87.58%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int left = 0;
        int right = 0;
        char temp;
        for (int i = 0; i < n; i++) {
            if (a[i] == ' ') {
                right = i - 1;
                while (left < right) {
                    temp = a[left];
                    a[left++] = a[right];
                    a[right--] = temp;
                }
                left = i + 1;
            }
        }
        right = n - 1;
        while (left < right) {
            temp = a[left];
            a[left++] = a[right];
            a[right--] = temp;
        }
        return new String(a);
    }

    /**
     * 堕落解法
     * 时间复杂度:O() - 47.72%
     * 空间复杂度:O() - 61.65%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        return Arrays.stream(s.split(" ")).map(v -> new StringBuffer(v).reverse().toString()).collect(Collectors.joining(" "));
    }

    /**
     * 简单：将输入字符串中按照空白字符串分开，然后把所有单词放到一个字符串列表中，然后我们逐一遍历每一个字符串并把反转结果连接起来。最后，我们将删除了额外空白字符的字符串返回。
     * 时间复杂度:O(n) - 59.58%
     * 空间复杂度:O(n) - 27.77%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords4(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }
}
