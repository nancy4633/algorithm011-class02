学习总结（复习时重新整理）：
#为啥Redis使用skiplist而不是red-black#
1. skiplist和red-black复杂度一样，但是skiplist实现更简单
2. 并发环境下，红黑树插入、删除时可能需要rebalance，可能涉及整个树的其它部分，skiplist的操作更局部性。锁需要关注的节点更少，因此性能更好。
#刚开始学习时的总结#
第一周实在太慌乱了，每日一题坚持到周五写作业，就崩溃了，第一题卡住，git也搞不定。
然后整体延迟到第二周周四，完全是心魔，心里障碍，其实现在回想，没有很难完成，就是无法静下心来。
目前状态好些，看到IDEA不会头疼，看到leetcode不会头疼，看到极客大学不会头疼了。
这周的目标是把国内站的题解看好，已经没有精力看国际站
先追上进度就行
#复习总结#
期末考试前复习一遍，发现当初死记硬背的只有些模糊的记忆，有点儿像从头再来。
