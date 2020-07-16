学习笔记
# 深度优先搜索(DFS)的实现代码：
visited = set() #set实现visited
# 递归实现：
def DFS(node, visited):
  if node in visited:  # terminator
    return
  visited.add(node)
  # process current node
  for next_node in node.children:
    if next_node not in visited:
      DFS(next_node, visited)
# 非递归实现（栈）：
def DFS(self, tree):
  if tree.root is None:
    return []
  visited, stack = [], tree.root
  while tree.stack:
    node = stack.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
    stack.push(nodes)
  
# 广度优先搜索(BFS)的实现代码
# 非递归实现（队列）
def BFS(self, tree):
  queue = []
  queue.append([start])
  visited.add(start)
  while queue:
    node = queue.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
  # 不太了解python，如果是java代码这里是不是应该用add(offerlast)
    queue.push(nodes)

# 二分查找的实现代码
left, right = 0, len(array) - 1 
while left <= right: 
  mid = (left + right) / 2 
  if array[mid] == target: 
  # find the target!! 
    break or return result 
  elif array[mid] < target: 
    left = mid + 1 
  else: 
    right = mid - 1
		    
# 补充：
  LinkedList的add是offerlast，push是offerfirst。写代码的时候要注意！！！
  虽然每天刷题无数，刷题思路也越来越明朗了，但是当看到十几个作业题，外加课后练习题，不下30个，心里还是有点儿被挑战的赶脚。
  后来发现重复的问题还是很多的，非常适合练手，同一类问题，到最后各种已经如数家珍了，哪个快哪个慢哪个超时都记清楚了。
  算法只靠看书真的不行，看来刷题真是王道，这是刚刚领悟的哈哈哈哈

# 对比 贪心算法、回溯、动态规划
贪心算法 - 当下做最优判断 + 无法回退
回溯    -                 能够回退
动态规划 - 最优判断      + 能够回退

# 贪心算法 - 每一步都选择当前状态下最好｜最优的选择，希望导致结果是全局最好或最优的算法。
# 可以解决生活中一些最优化问题，如求图中的最小生成树，求哈夫曼编码等。
# 然而对于工程和生活中的问题，一般不能得到我们想要的答案。
# 一旦一个问题能用贪心算法解决，那么贪心法一般是解决这个问题的最好办法
# 由于贪心算法的高效性 & 其所求得的答案比较接近最优解，贪心算法也可以用作辅助算法｜直接解决一些要求结果不特别精确的问题。

# 本周进度：
周日开始看视频 + 1个习题
周二课后习题*0 + 刷次数*0
周二课后习题*0 + 刷次数*0
周三课后习题*3 + 刷次数*3
周四作业*3 + 刷次数*3
周五作业*3 + 刷次数*3
周六整理+总结+作业提交 + 刷次数*3


