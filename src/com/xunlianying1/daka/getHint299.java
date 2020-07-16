package com.xunlianying1.daka;

import java.util.HashMap;

// 猜字游戏：
// 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
// 你写出一个秘密数字，并请朋友猜这个数字是多少。
// 朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
// 朋友根据提示继续猜，直到猜出秘密数字。
// ****请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
// xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
// yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
// 请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
public class getHint299 {

    /**
     * 暴力求解：
     * 两层循环
     *
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint1(String secret, String guess) {
        // 初始判断：空值判断 + 长度相等 + 纯数字

        if (secret == null || guess == null) {
            return null;
        }
        if (secret.length() != guess.length()) {
            return null;
        }
        // 主逻辑
        int A = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            }
        }
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapG = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            mapS.put(secret.charAt(i), mapS.getOrDefault(secret.charAt(i), 0) + 1);
            mapG.put(guess.charAt(i), mapG.getOrDefault(guess.charAt(i), 0) + 1);
        }
        int B = 0;
        for (Character key : mapG.keySet()) {
            int n1 = mapG.getOrDefault(key, 0);
            int n2 = mapS.getOrDefault(key, 0);
            B = B + Math.min(n1, n2);
        }
        return A + "A" + (B - A) + "B";
    }

    /***
     * 用数组代替map - 因为key的范围是0～9
     * 两层循环
     *
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint11(String secret, String guess) {
        int A = 0;

        // 因为key的范围是0～9，是确定大小的数组
        // 对于确定大小，且数据量不大的数据，数组是非常适合存储的
        int[] mapS = new int[10];
        int[] mapG = new int[10];
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                mapS[secret.charAt(i) - '0']++;
                mapG[guess.charAt(i) - '0']++;
            }
        }
        int B = 0;
        for (int i = 0; i < 10; i++) {
            B += Math.min(mapS[i], mapG[i]);
        }
        return A + "A" + B + "B";
    }

    /***
     * 只用一个循环
     * 比较难理解，不过思路太妙了。
     * number数组相当于是10个栈，secret出现就进栈，guess出现就出栈
     *
     * 栈的实际作用：其实就是减少了一层循环。
     *
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint111(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = (int) (secret.charAt(i) - '0');
            int g = (int) (guess.charAt(i) - '0');
            if (s == g) bulls++;
            else {
                //当前数小于 0, 说明之前在 guess 中出现过, 和 secret 当前的数匹配
                if (numbers[s] < 0) cows++;
                //当前数大于 0, 说明之前在 secret 中出现过, 和 guess 当前的数匹配
                if (numbers[g] > 0) cows++;
                //secret 中的数, 计数加 1
                numbers[s]++;
                //guess 中的数, 计数减 1
                numbers[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint1("12345678901234567890", "12345678901234567890"));
    }
}
