package com.xunlianying4;

import java.util.Comparator;
import java.util.PriorityQueue;

// 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
// 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
// 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
// 注意，一开始你手头没有任何零钱。
// 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
public class LemonadeChange860 {

    /***
     * 暴力求解
     *
     * @param bills
     * @return
     */
    private boolean lemonadeChange1(int[] bills) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int bill : bills) {
            int change = bill - 5;
            while (change > 0) {
                if (queue.size() <= 0) {
                    return false;
                }
                boolean isFound = false;
                for (Integer i : queue) {
                    if (change - i >= 0) {
                        change = change - i;
                        queue.remove(i);
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    return false;
                }
            }

            if (bill < 20) {
                queue.add(bill);
            }
        }

        return true;
    }

    /***
     * 贪心算法 - ifelse
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange11(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * 贪心算法 - switch
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange111(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    five++;
                    break;
                case 10:
                    five--;
                    ten++;
                    break;
                case 20: {
                    if (ten > 0) {
                        ten--;
                        five--;
                    } else {
                        five -= 3;
                    }
                    break;
                }
                default:
                    break;
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}
