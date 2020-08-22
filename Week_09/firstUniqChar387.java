package com.xunlianying9.zuoye1;

import java.util.HashMap;

// 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
// 提示：你可以假定该字符串只包含小写字母。
// 思路：
// 这里的不重复是全局不重复
public class firstUniqChar387 {

    public static int firstUniqChar(String s) {
        return 0;
    }

    /**
     * 时间复杂度:O() - 91.70%
     * 空间复杂度:O() - 90.88%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public int firstUniqChar1(String s) {
        int[] all = new int[26];
        for (char c : s.toCharArray()) {
            all[c - 'a']++;
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (all[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    /**
     * 时间复杂度:O() - 51.43%
     * 空间复杂度:O() - 85.27%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int length = s.length();
        char c;
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            if (count.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
}
