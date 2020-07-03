package com.second.zuoye;

import java.util.Arrays;

// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
// 思路：
// 暴力求解
// 栈
public class IsAnagram242 {

    /***
     * 暴力求解：先排序，后对比是否相等。
     *
     * 时间复杂度：O(nlogn) - 排序的时间复杂度是O(nlogn)，比较的时间复杂度是O(n)
     * 空间复杂度：O(1) - 空间取决于排序实现，如果使用 heapsort，通常需要 O(1)O(1) 辅助空间。注意，在 Java 中，toCharArray() 制作了一个字符串的拷贝，所以它花费 O(n)O(n) 额外的空间，但是我们忽略了这一复杂性分析，因为：这依赖于语言的细节。这取决于函数的设计方式。例如，可以将函数参数类型更改为 char[]。
     * 这个时间复杂度分析，比我想的要复杂，值得学习
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram1(String s, String t) {
        // 初始值判断
        if (s == null && t == null)
            return true;
        if ((s == null) & (t == null) == false)
            return false;
        if (s.length() != t.length())
            return false;
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        // 数组排序是改变数组本身！！！
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        // 可以直接打印数组中的值
        System.out.println(sArray);
        System.out.println(tArray);
        // 数组判断值是否相等！！！
        if (Arrays.equals(sArray, tArray)) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * hash
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram11(String s, String t) {
        // 初始值判断
        if (s == null && t == null)
            return true;
        if ((s == null) & (t == null) == false)
            return false;
        if (s.length() != t.length())
            return false;
        int[] sArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 这里的++符号一开始不敢用，怕自己用错了，确实一开始用错了，不过现在有点儿理解了
            sArray[s.charAt(i) - 'a']++;
            sArray[t.charAt(i) - 'a']--;
        }
        for (int i : sArray) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /***
     * 优化的hash
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram111(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        // 这里的遍历就不一定是全遍历了，最坏情况是O(n)
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram1("anagram", "nagaram"));
    }
}
