package com.xunlianying3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 第五遍 写的不顺手，还没到信手拈来的程度，需要多加练习
// 重点记住解法1和解法2
// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
// 给出数字到字母的映射如下（与电话按键相同）。
// 注意:
// 不对应任何字母。
public class LetterCombinations17 {
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
    public List<String> letterCombinations1(String digits) {
        if (digits.equals("")) return result1;
        dfs1(digits, new char[digits.length()], 0);
        return result1;
    }

    List<String> result1 = new ArrayList<>();
    String[] keys1 = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private void dfs1(String digits, char[] chars, int index) {
        if (index == digits.length()) {
            result1.add(new String(chars));
            return;
        }
        for (char c : keys1[digits.charAt(index) - '2'].toCharArray()) {
            chars[index] = c;
            dfs1(digits, chars, index + 1);
        }
    }

    /**
     * 遍历 + 二唯char数组
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 99.49%
     * 优点:
     * 缺点:
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if ("".equals(digits)) return result;
        char[][] keys = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        int len = digits.length(), result_len = 1, loop = 1;
        for (char c : digits.toCharArray()) {
            result_len *= keys[c - 48].length;
        }
        char[][] list = new char[result_len][len];
        for (int i = 0; i < len; i++) {
            char[] chars = keys[digits.charAt(i) - 48];
            for (int j = 0; j < result_len; j++) {
                char[] c = list[j];
                c[i] = chars[j / loop % chars.length];
            }
            loop *= chars.length;
        }
        for (char[] c : list) {
            result.add(new String(c));
        }
        return result;
    }


    /**
     * 回溯 + 一维String数组
     * 时间复杂度:O() - 88.13%
     * 空间复杂度:O() - 74.43%
     * 优点:
     * 缺点:
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations3(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() < 1) return results; // 这一句要判断哦，否则会报错，把[] 返回成[""]
        StringBuilder result = new StringBuilder();
        dfs3(digits, digits.length(), 0, results, result);
        return results;
    }

    String[] keys3 = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void dfs3(String digits, int len, int index, List<String> results, StringBuilder result) {
        if (index == len) {
            results.add(new String(result));
            return;
        }
        // 当前index对应的可能的码值
        String key = keys3[digits.charAt(index) - 50];  // 也可以写成： keys[digits.charAt(index) - '0' - 2];
        // 0的ASCII码值是48， A的ASCII码是65, a的ASCII码是97
        for (int id = 0; id < key.length(); id++) { // 遍历所有的当前index对应的可能的码值
            result.append(key.charAt(id)); // 加到result的末尾
            dfs3(digits, len, index + 1, results, result); // 继续操作接下来的index。
            result.deleteCharAt(index); // reverse state，这一步应该是回溯的精髓，非常赞，只要有这一步基本上就可以认为是经典的回溯
        }
    }

    /**
     * 回溯 + map
     * 时间复杂度： 88.66%
     * 空间复杂度： 91.24%
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations4(String digits) {
        if (digits.length() != 0) backtrack4("", digits);
        return result4;
    }

    Map<String, String> keys4 = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> result4 = new ArrayList<>();

    public void backtrack4(String combination, String next_digits) {
        if (next_digits.length() == 0) {
            result4.add(combination);
        } else {
            String digit = next_digits.substring(0, 1);
            String letters = keys4.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = keys4.get(digit).substring(i, i + 1);
                backtrack4(combination + letter, next_digits.substring(1));
            }
        }
    }

    /**
     * 回溯 + 一维String数组
     * 时间复杂度： 37.32%
     * 空间复杂度： 31.06%
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations5(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        iterStr5(digits, "", 0);
        return result5;
    }

    String[] keys5 = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result5 = new ArrayList<>();

    void iterStr5(String str, String letter, int index) {
        if (index == str.length()) {
            result5.add(letter);
            return;
        }
        char c = str.charAt(index);
        int pos = c - '0';
        String map_string = keys5[pos];
        for (int i = 0; i < map_string.length(); i++) {
            iterStr5(str, letter + map_string.charAt(i), index + 1);
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
    public List<String> letterCombinations6(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        for (char num : digits.toCharArray()) {
            String chars = letter_map[num - '0'];
            int size = result.size();
            for (int j = 0; j < size; j++) {
                String tmp = result.remove(0);
                for (char c : chars.toCharArray()) {
                    result.add(tmp + c);
                }
            }
        }
        return result;
    }
}
