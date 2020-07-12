package com.first.daka;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

// 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
// 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
// hash貌似没有比普通的遍历快很多。可能是数据量还不够大。
public class WordPattern290 {

    /***
     * 暴力求解
     *
     * 思路：对str进行split
     * 1. 判断长度，不相等直接返回false
     * 2. 长度相等，继续逻辑处理
     *
     * 时间复杂度：O(n^2) - 两层for循环
     * 空间复杂度：O(n)   - 因为新建了一个数组
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern1(String pattern, String str) {
        // 初始：判断空值情况
        if (pattern == null || str == null) {
            return false;
        }
        // 主要逻辑
        String[] strSplit = str.split(" ");
        if (pattern.length() != strSplit.length) {
            return false;
        }
        // 1. 对比每一个相同的pattern 对应的str是不是相等
        // 2. 对比每一个不相同的pattern 对应的str是不是不相等
        for (int i = 0; i < pattern.length(); i++) {
            for (int j = i + 1; j < pattern.length(); j++) {
                if ((pattern.charAt(i) == pattern.charAt(j)) == (strSplit[i].equalsIgnoreCase(strSplit[j]))) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * hashmap+hashset
     *
     * pattern是key，str是value，放到hashmap里面
     * 再把value单独放到hashset里
     * 判断是否有重复的key和value
     *
     * 时间复杂度： O(n) - 一层for循环是n，里面是hash遍历，时间复杂度是O(1)，所以最后的时间复杂度是O(n)
     * 空间复杂度： O(n) - 新建了一个字符串数组，和一个n维的hash表。
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern11(String pattern, String str) {
        // 初始：判断空值情况
        if (pattern == null || str == null) {
            return false;
        }
        // 主逻辑
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        String[] array = str.split(" ");
        if (pattern.length() != array.length) {
            return false;
        }
        // 1. for循环放到hashmap，并判断key是否存在？再判断value是否存在？
        // 2. key存在，判断value是否相等？不相等就return false，相等就continue
        // 3. key不存在，判断value是否存在？存在就return false，不存在就continue
        // 4. 遍历结束就return true
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            //当前 key 是否存在
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array[i])) {
                    return false;
                }
            } else {
                if (set.contains(array[i])) {
                    return false;
                }
                set.add(array[i]);
                map.put(key, array[i]);
            }
        }
        return true;
    }


    /***
     * 只是用hashmap
     *
     * pattern是key，str是value，放到hashmap里面
     * 判断是否有重复的key和value
     *
     * 时间复杂度： O(n) - 一层for循环是n，里面是hash遍历，时间复杂度是O(1)，所以最后的时间复杂度是O(n)
     * 空间复杂度： O(n) - 新建了一个字符串数组，和一个n维的hash表。
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern111(String pattern, String str) {
        // 初始：判断空值情况
        if (pattern == null || str == null) {
            return false;
        }
        // 主要逻辑
        String[] strsplit = str.split(" ");
        if (pattern.length() != strsplit.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            Character key = pattern.charAt(i);
            if (map.containsKey(key)) {
                if (!map.get(key).equals(strsplit[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(strsplit[i])) {
                    return false;
                }
                map.put(key, strsplit[i]);
            }
        }
        return true;
    }

    /***
     * 大神的代码
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern1111(String pattern, String str) {
        // 初始：判断空值情况
        if (pattern == null || str == null) {
            return false;
        }
        String[] strsplit = str.split(" ");
        // 这个地方也容易忘记，会引起结果错误
        if (pattern.length() != strsplit.length) {
            return false;
        }
        // 主要逻辑
        Map map = new HashMap();
        // 这里的Integer是非常关键的点，不可以使用int，因为：
        for (Integer i = 0; i < pattern.length(); i++) {
            if (map.put(pattern.charAt(i), i) != map.put(strsplit[i], i)) {
                return false;
            }
        }
        return true;
    }

    /***
     * Integer a = 66;
     * Integer b = 66;
     * System.out.println(a == b); // true
     *
     * Integer c = 166;
     * Integer d = 166;
     * System.out.println(c == d); // false
     *
     * 当不在 [-128,127] 的范围内时，即使 Integer 包含的值相等，但由于是对象之间比较，依旧会返回 false。
     * 回到之前的问题，如果你非常想用 int ，比较两个值的时候，你可以拆箱去比较。但返回的有可能是 null，所以需要多加几个判断条件。
     */
    public static boolean wordPattern11111(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (int i = 0; i < words.length; ++i) {
            Object a = index.put(pattern.charAt(i), i);
            Object b = index.put(words[i], i);
            if (a == null && b == null)
                continue;
            if (a == null || b == null)
                return false;
            if ((int) a != (int) b) {
                return false;
            }
        }
        return true;
    }

    /***
     * 使用int后，用objects.equal进行判断
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern111111(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (int i = 0; i < words.length; ++i)
            if (!Objects.equals(index.put(pattern.charAt(i), i),
                    index.put(words[i], i)))
                return false;
        return true;
    }

    public static void main(String[] args) {
        Integer a = 166;
        Integer b = 166;
        System.out.println(a == b); // true

        Integer c = 166;
        Integer d = 166;
        System.out.println(c == d);
        System.out.println((int) c == (int) d); // false
    }
}
