package com.xunlianying2;

import java.util.*;

// 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 思路：
// 暴力求解
// 每个单词排序后，直接比较是否相等
// 每个单词进行hash
public class GroupAnagrams49 {

    /***
     * 暴力求解：
     * 遍历字符串数组，单独写字符串比对函数，判断是否相等
     *
     * 时间复杂度：O(n*最长的单词的长度)
     * 空间复杂度：O(n*最长的单词的长度)
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            List<String> temp = null;
            if (!used[i]) {
                temp = new ArrayList<String>();
                temp.add(strs[i]);
                //两两比较判断字符串是否符合
                for (int j = i + 1; j < strs.length; j++) {
                    if (equals(strs[i], strs[j])) {
                        used[j] = true;
                        temp.add(strs[j]);
                    }
                }
            }
            if (temp != null) {
                ans.add(temp);

            }
        }
        return ans;

    }

    // 字符串比对函数，专门判断异位词
    private boolean equals(String string1, String string2) {
        Map<Character, Integer> hash = new HashMap<>();
        //记录第一个字符串每个字符出现的次数，进行累加
        for (int i = 0; i < string1.length(); i++) {
            if (hash.containsKey(string1.charAt(i))) {
                hash.put(string1.charAt(i), hash.get(string1.charAt(i)) + 1);
            } else {
                hash.put(string1.charAt(i), 1);
            }
        }
        //记录第一个字符串每个字符出现的次数，将之前的每次减 1
        for (int i = 0; i < string2.length(); i++) {
            if (hash.containsKey(string2.charAt(i))) {
                hash.put(string2.charAt(i), hash.get(string2.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        //判断每个字符的次数是不是 0 ，不是的话直接返回 false
        Set<Character> set = hash.keySet();
        for (char c : set) {
            if (hash.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    /***
     * 每个单词先排序，再直接比较是否相等
     *
     * 时间复杂度：O(n*最长单词的长度*log最长单词的长度)
     * 空间复杂度：O(n*最长单词的长度)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams11(String[] strs) {
        // 定义hashmap这一步，不熟悉，之前的思路是定义一个list和一个map，但是不知道怎么互相转换，这个地方要学习！！！
        HashMap<String, List<String>> result = new HashMap();
        Map<String, String> map = new HashMap<String, String>();
        // 遍历每个单词，进行排序
        for (int i = 0; i < strs.length; i++) {
            char[] strsChars = strs[i].toCharArray();
            Arrays.sort(strsChars);
            String key = String.valueOf(strsChars);
            if (result.containsKey(key)) {
                result.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                result.put(key, temp);
            }
        }
        // 这里的hashmap的value方法，不熟悉，要记住！！！
        return new ArrayList<List<String>>(result.values());
    }

    /***
     * 每个单词进行hash运算
     * 这里的计算公式是：正整数的唯一分解定理
     * 把26个字母分别对应成一个质数
     * 原因：数量确定，一定是26个
     *
     * 数学功底很深厚啊，需要活学活用。hash算法的公式确定还是需要很多数学功底的
     * 每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
     *
     * 时间复杂度：O(n*最长单词的长度)
     * 空间复杂度：O(n*最长单词的长度)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams111(String[] strs) {
        HashMap<Integer, List<String>> hash = new HashMap<>();
        // 把每个字母对应成一个质数
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }

    /***
     * 其实还是hashmap键值的确定，这里又用到了数组
     * 这个解法没有仔细看，感觉不够优美，除非万不得已不会用到哈哈
     *
     * 时间复杂度：O(n*最长单词的长度)
     * 空间复杂度：O(n*最长单词的长度)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams1111(String[] strs) {
        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] num = new int[26];
            //记录每个字符的次数
            for (int j = 0; j < strs[i].length(); j++) {
                num[strs[i].charAt(j) - 'a']++;
            }
            //转成 0#2#2# 类似的形式
            String key = "";
            for (int j = 0; j < num.length; j++) {
                key = key + num[j] + '#';
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }

    public static void main(String[] args) {

    }
}
