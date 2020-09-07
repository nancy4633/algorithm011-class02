package com.xunlianying3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
// 思路：
// 树的递归、泛型递归
// 回溯
// 迭代
// 因为这里面的key是固定的数目和值，范围也不大，所以map和数组对比来看，数组更适合，而且map是O(1)寻址，数组也可以做到，而且更节省空间
// 所以类似的题更推荐用数组
// 不过map的定义和赋值还是要学一下的，这里的方法块给数组赋值还是很别出心裁的～不错不错！！！
public class LetterCombinations17 {
    /**
     * 回溯： map + 递归
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations1(String digits) {
        if (digits.length() != 0) backtrack("", digits);
        return output;
    }

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    public void backtrack(String combination, String next_digits) {
        if (next_digits.length() == 0) {
            output.add(combination);
        } else {
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    /**
     * 回溯： 数组 + 递归
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations11(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        iterStr(digits, "", 0);
        return res;
    }

    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    void iterStr(String str, String letter, int index) {
        if (index == str.length()) {
            res.add(letter);
            return;
        }
        char c = str.charAt(index);
        int pos = c - '0';
        String map_string = letter_map[pos];
        for (int i = 0; i < map_string.length(); i++) {
            iterStr(str, letter + map_string.charAt(i), index + 1);
        }
    }

    /**
     * 迭代： 队列 + 迭代
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations111(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<String>();
        String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            String letters = letter_map[digits.charAt(i) - '0'];
            int size = res.size();
            for (int j = 0; j < size; j++) {
                String tmp = res.remove(0);
                for (int k = 0; k < letters.length(); k++) {
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;
    }
}
