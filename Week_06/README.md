学习笔记
#递归模版
public void recur(level, param) {
    // terminator
    if () return ;
    // process
    process() ;
    // drill down 
    process(level+1, new_param) ;
    // restore current status
}
#分治模版
public void devide_conquer(problem, param1, param2, ...) {
    // terminator
    if (problem == null) {
        print_result;
        return ;
    }
    // prepare data
    data = prepare_data(problem);
    subproblem = splib_problems(data, problem);
    subresult1=conquer_problem(subproblem[0], param1,param2,...);
    subresult2=conquer_problem(subproblem[1], param1,param2,...);
    ...
    result = joint_result(subresult1,subresult2,...)
    return result;
}
#动态规划模版
#DP三部曲：1.子问题 2.状态定义 3.DP方程
#核心：状态转移方程+自底向上for循环
#调优方案：状态存储 + 状态压缩
public void dynamic_programming(int n) {
    // terminator
    if (n==0) return ;
    // 定义状态存储 + 状态压缩（一维转变量，二维转一维）
    int[] count = new int[n+1];
    // 自底向上for循环
    for(int i =? ; i<n;i++) {
        // 状态转移方程
    }
    return count[n];
}

这周应该是比树的上周简单太多了，心里突然很轻松，周四才开始刷题，感觉so easy～