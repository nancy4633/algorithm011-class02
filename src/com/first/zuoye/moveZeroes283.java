package com.first.zuoye;


public class moveZeroes283 {
    // 1.暴力求解 - 遍历数组，遇到零就后移，时间复杂度-O(n^2)，空间复杂度O(1)
    // 2.置位删除，先遍历并标记所有零，最后移动零，时间复杂度-O(n^2)，空间复杂度O(1)

    // 3.置位删除，先遍历并标记所有零，最后移动所有非零到前面，后面补零，时间复杂度-O(n^2)，空间复杂度O(1)
    // 4.冒泡的思想，新建一个大小相等的数组，遍历所有非零放到新数组，，后面补零，时间复杂度-O(n)，空间复杂度O(n)

    // 由于我没有仔细看题，其实题目要求，在原数组上操作，不可以拷贝额外的数组。尽量减少操作次数
    // 官方的解法还是很牛的：
    // 初始判断忘记加了，特殊情况一定要考虑，但是可以是先加注释，不做实现，但是至少要知道。

    /***
     * 这个是我根据老师的代码默写完成的，一开始思路没有很清晰，但是写过代码之后的思路就很好延展了。所以迈出第一步很重要，虽然很艰难
     * 但是结果非常好，坚定的按照计划去执行。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     */
    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num = 0; num < nums.length; num++) {
            if (nums[num] != 0 && num != insertPos) {
                nums[insertPos] = nums[num];
                nums[num] = 0;
                insertPos++;
            } else if (nums[num] != 0 && num == insertPos) {
                insertPos++;
            }
        }
    }

    /**
     * 官方最优解，把n++融合到运算当中，虽然思路是一样的，但是代码更简洁，更容易阅读和维护。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     */
    public static void moveZeroes11(int[] nums) {
        // 判断空和大小为0的情况
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) {
                //这个方法很赞，是整个算法的亮点。把操作和++结合到一起。
                nums[insertPos++] = num;
            }
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    /****
     * 置换的方法，不需要再增加while循环去增加0
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param nums
     */
    public static void moveZeroes111(int[] nums) {
        // 判断特殊情况，可以注释为空，不写逻辑。但是不可以缺少。
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num = 0; num < nums.length; num++) {
            if (nums[num] != 0) {
                int temp = nums[insertPos];
                nums[insertPos++] = nums[num];
                nums[num] = temp;
            }
        }
    }

    /***
     * main方法可以暂时做为testcase去实现，慢慢维护模板。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] tests = null;
        moveZeroes111(tests);
        if (tests == null) {
            System.out.println("null");
        } else {
            for (int test : tests) {
                System.out.print(test + ",");
            }
        }
    }
}
