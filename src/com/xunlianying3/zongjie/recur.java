package com.xunlianying3.zongjie;

// 递归代码模版！！！！
// 要点：
// 不要人肉进行递归 - 直接看函数即可
// 找最近重复子问题 - 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
// 数学归纳法思维
public class recur {
    // 终止条件
    int MAXLEVEL = 0;
    public void recur(int level, int param) {
        // terminator - 第一步：定义终止条件
        if (level > MAXLEVEL) {
            // process result
            return;
        }
        // process current logic - 第二步：处理当前层逻辑
        process(level, param);
        // drill down - 第三步：下探到下一层
        int newparam = 0;
        recur(level + 1, newparam);
        // restore current status - 第四步：清理当前层
    }

    private void process(int level, int param) {
    }
}
