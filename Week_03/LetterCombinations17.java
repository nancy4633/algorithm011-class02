package com.xunlianying3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 第一遍
// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
public class LetterCombinations17 {

    /**
     * 回溯
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.97%
     * 优点:
     * 缺点:
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations0(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() < 1) return result;
        StringBuilder sb = new StringBuilder();
        traceback0(digits.length(), result, digits, 0, sb);
        return result;
    }

    String[] chars = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void traceback0(int n, List<String> result, String digits, int i, StringBuilder sb) {
        if (i == n) {
            result.add(sb.toString());
            return;
        }
        String toBeAppend = chars[digits.charAt(i) - '0' - 2];
        for (int idx = 0; idx < toBeAppend.length(); idx++) {
            sb.append(toBeAppend.charAt(idx));
            traceback0(n, result, digits, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 递归 + 二唯char数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.49%
     * 优点:
     * 缺点:
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return new ArrayList<>();
        char[][] q = new char[][]{
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
        int l = digits.length();
        int resLength = 1;
        for (int i = 0; i < l; i++) {
            resLength *= q[digits.charAt(i) - 48].length;
        }
        char[][] list = new char[resLength][l];
        int loop = 1;
        for (int i = 0; i < l; i++) {
            char[] chars = q[digits.charAt(i) - 48];
            for (int j = 0; j < resLength; j++) {
                char[] c = list[j];
                c[i] = chars[j / loop % chars.length];
            }
            loop *= chars.length;
        }
        List<String> res = new ArrayList<>(resLength);
        for (char[] chars : list) {
            res.add(new String(chars));
        }
        return res;
    }

    /**
     * 递归
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.97%
     * 优点:
     * 缺点:
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations3(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.equals("")) return list;
        String[] strs = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int len = digits.length();
        char[] chars = new char[len];
        recurse3(strs, list, digits, chars, 0);
        return list;
    }

    public void recurse3(String[] strs, List<String> list, String digits, char[] chars, int count) {
        if (count == chars.length) {
            list.add(new String(chars));
            return;
        }
        for (char c : strs[digits.charAt(count) - '2'].toCharArray()) {
            chars[count] = c;
            recurse3(strs, list, digits, chars, count + 1);
        }
    }

    /**
     * 回溯： map + 递归
     * 时间复杂度： 88.66%
     * 空间复杂度： 91.24%
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations7(String digits) {
        if (digits.length() != 0) backtrack7("", digits);
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

    public void backtrack7(String combination, String next_digits) {
        if (next_digits.length() == 0) {
            output.add(combination);
        } else {
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack7(combination + letter, next_digits.substring(1));
            }
        }
    }

    /**
     * 回溯： 数组 + 递归
     * 时间复杂度： 37.32%
     * 空间复杂度： 31.06%
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations8(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        iterStr8(digits, "", 0);
        return res;
    }

    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    void iterStr8(String str, String letter, int index) {
        if (index == str.length()) {
            res.add(letter);
            return;
        }
        char c = str.charAt(index);
        int pos = c - '0';
        String map_string = letter_map[pos];
        for (int i = 0; i < map_string.length(); i++) {
            iterStr8(str, letter + map_string.charAt(i), index + 1);
        }
    }

    /**
     * 迭代： 队列 + 迭代
     * 时间复杂度：29.42%
     * 空间复杂度： 33.80%
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations9(String digits) {
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
