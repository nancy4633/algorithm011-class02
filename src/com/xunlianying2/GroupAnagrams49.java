package com.xunlianying2;

import java.util.*;

// 第一遍
// 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
public class GroupAnagrams49 {
    /***
     * HashMap - 每个单词先排序，再直接比较是否相等
     * 时间复杂度：O(n*最长单词的长度*log最长单词的长度) - 97.60%
     * 空间复杂度：O(n*最长单词的长度) - 92.36%
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> result = new HashMap();
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            char[] strsChars = strs[i].toCharArray();
            Arrays.sort(strsChars);
            String key = String.valueOf(strsChars);
            if (result.containsKey(key)) {
                result.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                result.put(key, temp);
            }
        }
        return new ArrayList<List<String>>(result.values());
    }

    /***
     * 质数 - 溢出报错
     * 每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
     * 时间复杂度：O(n*最长单词的长度)
     * 空间复杂度：O(n*最长单词的长度)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams5(String[] strs) {
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

    /**
     * 其实还是hashmap键值的确定，这里又用到了数组
     * 时间复杂度：O(n*最长单词的长度) - 7.03%
     * 空间复杂度：O(n*最长单词的长度) - 5.13%
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams9(String[] strs) {
        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] num = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                num[strs[i].charAt(j) - 'a']++;
            }
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

    /***
     * 暴力求解：
     * 时间复杂度：O(n * k) - 超时
     * 空间复杂度：O(n * k) -
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams8(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            List<String> temp = null;
            if (!used[i]) {
                temp = new ArrayList<String>();
                temp.add(strs[i]);
                for (int j = i + 1; j < strs.length; j++) {
                    if (equals(strs[i], strs[j])) {
                        used[j] = true;
                        temp.add(strs[j]);
                    }
                }
            }
            if (temp != null) ans.add(temp);
        }
        return ans;
    }

    private boolean equals(String string1, String string2) {
        Map<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {
            if (hash.containsKey(string1.charAt(i))) {
                hash.put(string1.charAt(i), hash.get(string1.charAt(i)) + 1);
            } else {
                hash.put(string1.charAt(i), 1);
            }
        }
        for (int i = 0; i < string2.length(); i++) {
            if (hash.containsKey(string2.charAt(i))) {
                hash.put(string2.charAt(i), hash.get(string2.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        Set<Character> set = hash.keySet();
        for (char c : set) {
            if (hash.get(c) != 0) {
                return false;
            }
        }
        return true;
    }


}
