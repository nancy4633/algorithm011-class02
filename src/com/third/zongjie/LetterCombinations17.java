package com.third.zongjie;

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

    /***
     * 回溯： map + 递归
     *
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
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    /***
     * 回溯： 数组 + 递归
     *
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations11(String digits) {
        //注意边界条件
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        iterStr(digits, "", 0);
        return res;
    }

    //一个映射表，第二个位置是"abc“,第三个位置是"def"
    //这里也可以用map，用数组可以更节省点内存
    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    //最终输出结果的list
    List<String> res = new ArrayList<>();

    //递归函数
    void iterStr(String str, String letter, int index) {
        //递归的终止条件，注意这里的终止条件看上去跟动态演示图有些不同，主要是做了点优化
        //动态图中是每次截取字符串的一部分，"234"，变成"23"，再变成"3"，最后变成""，这样性能不佳
        //而用index记录每次遍历到字符串的位置，这样性能更好
        if (index == str.length()) {
            res.add(letter);
            return;
        }
        //获取index位置的字符，假设输入的字符是"234"
        //第一次递归时index为0所以c=2，第二次index为1所以c=3，第三次c=4
        //subString每次都会生成新的字符串，而index则是取当前的一个字符，所以效率更高一点
        char c = str.charAt(index);
        //map_string的下表是从0开始一直到9， c-'0'就可以取到相对的数组下标位置
        //比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
        int pos = c - '0';
        String map_string = letter_map[pos];
        //遍历字符串，比如第一次得到的是2，页就是遍历"abc"
        for (int i = 0; i < map_string.length(); i++) {
            //调用下一层递归，用文字很难描述，请配合动态图理解
            iterStr(str, letter + map_string.charAt(i), index + 1);
        }
    }

    /**
     * 迭代： 队列 + 迭代
     * <p>
     * 时间复杂度：
     * 空间复杂度：
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations111(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {
                " ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        List<String> res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letter_map[digits.charAt(i) - '0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for (int j = 0; j < size; j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for (int k = 0; k < letters.length(); k++) {
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;
    }
}
