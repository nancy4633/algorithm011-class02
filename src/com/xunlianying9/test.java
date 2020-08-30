package com.xunlianying9;

public class test {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int left = 0, step = 2 * k; left < chars.length; left += step) {
            int right = Math.min(chars.length - 1, left + k - 1);
            System.out.println("left=" + left + " right=" + right);
            swap(chars, left, right);
        }
        return new String(chars);
    }

    // 交换左右子数组
    private void swap(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }
}
