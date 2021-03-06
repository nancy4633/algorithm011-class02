**泛型递归**
# 递归的代码实现模版：
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
}

**树的递归** 树的前中后序遍历模版：
# 前序遍历
def preorder(self, root):  
  if root:    
    self.traverse_path.append(root.val)    
    self.preorder(root.left)    
    self.preorder(root.right) 
# 中序遍历
def inorder(self, root): 
  if root:    
    self.inorder(root.left)    
    self.traverse_path.append(root.val)    
    self.inorder(root.right) 
# 后序遍历
def postorder(self, root): 
  if root:    
    self.postorder(root.left)    
    self.postorder(root.right)    
    self.traverse_path.append(root.val) 

思维要点：
  1. 不要人肉进行递归
  2. 找到 最近 + 最简 方法，将其 拆解 为可重复解决的问题（可重复 + 最小单元）
  3. 数学归纳法思维

**分治** 分治的代码实现模版：
def divide_conquer(problem, param1, param2, ...):
  # recursion terminator 
  if problem is None:    
    print_result  
    return
  # prepare data  
  data = prepare_data(problem)   
  subproblems = split_problem(problem, data)  
  # conquer subproblems  
  subresult1 = self.divide_conquer(subproblems[0], p1, ...)   
  subresult2 = self.divide_conquer(subproblems[1], p1, ...)   
  subresult3 = self.divide_conquer(subproblems[2], p1, ...)  ...
  # process and generate the final result  
  result = process_result(subresult1, subresult2, subresult3, ...) 
  # revert the current level states 

**回溯** 回溯的代码实现模版：
result = []
def backtrack(路径, 选择列表):
  # terminator
  if 满足结束条件:
    result.add(路径)
    return
  for 选择 in 选择列表:
    做选择
    backtrack(路径, 选择列表)
    撤销选择

**尾递归** 函数返回的时候，调用函数本身，而且return语句不能包含表达式。
尾递归优势：解决递归栈溢出问题
编译器或者解释器就可以把尾递归做优化，使递归本身无论调用多少次，都只占用一个栈帧，不会出现栈溢出的情况

本周尚未实现刷次数，完成全部习题和作业稍有些吃力，下周加上！！！
