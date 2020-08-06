学习笔记
#Trie tree# - 字典树
用于检索字符串数据集中的键，高效，有多种应用：
    1。 自动补全 - 谷歌的搜索建议
    2。 拼写检查 - 文字处理软件中的拼写检查
    3。 IP路由（最长前缀匹配） - internet协议路由中利用转发表选择路径
    4。 T9（九宫格）打字预测 - 20世纪90年代常用于手机输入
    5。 单词游戏 - 通过剪枝搜索空间来高效解决Boggle单词游戏
#Trie tree代码模版#
public class Trie {
    public boolean isEnd;
    public String val;
    public Trie[] next;
    public Trie() {
        isEnd = false;
        val = null;
        next = new Trie[26];
    }
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        char[] characters = word.toCharArray();
        Trie trie = this;
        for (int i = 0; i < characters.length; i++) {
            int number = characters[i] - 'a';
            if (trie.next[number] == null) trie.next[number] = new Trie();
            trie = trie.next[number];
        }
        trie.isEnd = true;
        trie.val = word;
    }
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    public Trie searchPrefix(String prefix) {
        Trie trie = this;
        char[] characters = prefix.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            trie = trie.next[characters[i] - 'a'];
            if (trie == null) return null;
        }
        return trie;
    }
}
#并查集#
适用场景：组团、配对问题、group or not？
#并查集代码模版#
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	// 每一次循环，p都重新指向祖父节点，直到找到最终的跟节点
	// 路径压缩：while循环后的p都指向了祖父节点作为新的父节点，整体的路径会变小，但是不是最优的。
	// 最好的路径压缩应该是：while循环后所有的子节点都直接指向root
	public int findRoot(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = findRoot(p); 
		int rootQ = findRoot(q); 
		if (rootP == rootQ) return; 
		// parent[rootQ] = rootP; 也是可以滴
		parent[rootP] = rootQ; 
		// 一定要--， 朋友圈的问题，就是直接返回这个count
		count--;
	}
}
