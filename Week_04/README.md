学习笔记
深度优先搜索(DFS)的实现代码(栈实现)：
递归：
visited = set()  # set实现visited
def DFS(node, visited):
  if node in visited:  # terminator
    return
  visited.add(node)
  # process current node
  for next_node in node.children:
    if next_node not in visited:
      DFS(next_node, visited)
非递归：
def DFS(self, tree):
  if tree.root is None:
    return []
  # 栈实现visited
  visited, stack = [], tree.root
  while tree.stack:
    node = stack.pop()
    visited.add(node)
    process(node)
    nodes = generate_related_nodes(node)
    stack.push(nodes)
  
广度优先搜索(BFS)的实现代码(队列实现)
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


  补充：
  LinkedList的add是offerlast，push是offerfirst。写代码的时候要注意！！！
  虽然每天刷题无数，刷题思路也越来越明朗了，但是当看到十几个作业题，外加课后练习题，不下20个，心里还是有点儿被挑战的赶脚。
  