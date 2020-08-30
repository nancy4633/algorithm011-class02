package com.xunlianying9;

import java.util.ArrayDeque;
import java.util.Deque;

// 第一遍
public class reverseWords151 {

    /**
     * 双指针 +
     * 时间复杂度:O(n) - 74.52% - 线性遍历字符串。
     * 空间复杂度:O(n) - 60.86% - StringBuilder(Java) 中的字符串总长度≤N ，占用O(N)大小的额外空间
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while (i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    /**
     * 分割 + 倒序
     * 时间复杂度:O() - 89.85%
     * 空间复杂度:O() - 91.62%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if (strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }

    /**
     * 时间复杂度:O() - 24.12%
     * 空间复杂度:O() - 53.88%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') ++left;
        while (left <= right && s.charAt(right) == ' ') --right;
        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());
        return String.join(" ", d);
    }

    /**
     * 时间复杂度:O() - 20.68%
     * 空间复杂度:O() - 73.53%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public String reverseWords4(String s) {
        StringBuilder sb = trimSpaces(s);
        reverse(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') ++left;
        while (left <= right && s.charAt(right) == ' ') --right;
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);
            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') ++end;
            reverse(sb, start, end - 1);
            start = end + 1;
            ++end;
        }
    }


}
