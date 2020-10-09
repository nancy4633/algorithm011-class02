package com.xunlianying3;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 第四遍 重点：LinkedList的offer-addfirst  pop-removefirst  offer-add-addlast！！！二叉搜索树：节点值不可以相等！！！
// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
// 假设一个二叉搜索树具有如下特征：
// 节点的左子树只包含小于当前节点的数。
// 节点的右子树只包含大于当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
public class IsValidBST98 {
    /**
     * 递归：从最左边的叶子节点，不断遍历左其子树，每次取到的都是右节点或者根节点的值，所以一定是大于preroot的值的
     * 重点：思维一定要转换，很多遍历不是从跟节点开始遍历，而是从左节点，这样判断条件是一致的，更容易写代码和循环。
     * 时间复杂度：O(n) - 每个元素都必须访问一次 - 100.00%
     * 空间复杂度：O(h) - 最坏的情况下，需要存放O(h)个函数调用(h是树的高度) - 83.72%
     *
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST1(root.left)) return false; // 这句话之所以放在后面两句话的前面，是因为，要先递归到最左侧，才开始判断值，否则判断条件依然是不一致的。
        if (root.val <= preroot) return false; // 这里的小于一定要记得加"="！！！节点值相等的情况也不属于二叉搜索树
        preroot = root.val; // 父节点的值，给下一轮遍历用
        return isValidBST1(root.right);
    }

    long preroot = Long.MIN_VALUE;        // -9223372036854775808

    /**
     * 迭代 + DFS : 也是从最左边叶子节点遍历左子树，跟解法一的遍历顺序是一样的，这样判断条件就是一致的，便于代码实现。
     * 时间复杂度：O(n) - 同样每个节点都需要入队列/出队列一次 - 31.79%
     * 空间复杂度：0(n) - 最坏的情况下会包含所有的叶子节点，完全二叉树叶子节点是n/2个 - 98.38%
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        long min = Long.MIN_VALUE; // double inorder = -Double.MAX_VALUE; 作用就是超过int的取之范围，以免边缘值造成错误
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= min) return false; // 这里是： <=   一定要看清题目的条件！！！
            min = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 同解法2 - 用LinkedList实现
     * 时间复杂度:O() - 31.59%
     * 空间复杂度:O() - 99.00%
     * 优点:
     * 缺点:
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        double min = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if (root.val <= min) return false;
            min = root.val; // min都那么小了， root.val一定要大于min才可以完成后面的赋值啊！！！所以前面的符号就定下来了。
            root = root.right;
        }
        return true;
    }
}
