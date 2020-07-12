package com.second.zuoye;

import java.util.HashMap;

// 写一个关于 HashMap 的小总结。
public class HashMapSummary {
    /****
     * 哈希表综合了数组和链表的特点。一般采用拉链法实现哈希表；
     * 概括的说，HashMap 是一个关联数组、哈希表，它是线程不安全的，允许key为null,value为null。遍历时无序；
     * 其底层数据结构是数组称之为哈希桶，每个桶里面放的是链表，链表中的每个节点，就是哈希表中的每个元素；
     * 在JDK8中，当链表长度达到8，会转化成红黑树，以提升它的查询、插入效率；
     * 因其底层哈希桶的数据结构是数组，所以也会涉及到扩容的问题。当HashMap的容量达到threshold域值时，就会触发扩容。扩容前后，哈希桶的长度一定会是2的次方。这样在根据key的hash值寻找对应的哈希桶时，可以用位运算替代取余操作，更加高效。
     * 而key的hash值，并不仅仅只是key对象的hashCode()方法的返回值，还会经过扰动函数的扰动，以使hash值更加均衡。
     * 扰动函数就是为了解决hash碰撞的。它会综合hash值高位和低位的特征，并存放在低位，因此在与运算时，相当于高低位一起参与了运算，以减少hash碰撞的概率。（在JDK8之前，扰动函数会扰动四次，JDK8简化了这个操作）
     * 扩容操作时，会new一个新的Node数组作为哈希桶，然后将原哈希表中的所有数据(Node节点)移动到新的哈希桶中，相当于对原哈希表中所有的数据重新做了一个put操作。所以性能消耗很大，可想而知，在哈希表的容量越大时，性能消耗越明显。
     * 扩容时，如果发生过哈希碰撞，节点数小于8个。则要根据链表上每个节点的哈希值，依次放入新哈希桶对应下标位置。
     * 因为扩容是容量翻倍，所以原链表上的每个节点，现在可能存放在原来的下标，即low位， 或者扩容后的下标，即high位。 high位= low位+原哈希桶容量。
     *
     * @param args
     */
    public static void main(String[] args) {
        /***
         * HashMap继承自AbstractMap，实现了Map接口
         *
         * Map接口定义了所有Map子类必须实现的方法。
         * AbstractMap也实现了Map接口，并且提供了两个实现Entry的内部类：SimpleEntry和SimpleImmutableEntry。
         */
        HashMap hashMap = new HashMap();
        /***
         * 成员变量:
         *
         * //默认的初始容量，必须是2的幂。
         * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
         * //最大容量（必须是2的幂且小于2的30次方，传入容量过大将被这个值替换）
         * static final int MAXIMUM_CAPACITY = 1 << 30;
         * //默认装载因子，默认值为0.75，如果实际元素所占容量占分配容量的75%时就要扩容了。如果填充比很大，说明利用的空间很多，但是查找的效率很低，因为链表的长度很大（当然最新版本使用了红黑树后会改进很多），HashMap本来是以空间换时间，所以填充比没必要太大。但是填充比太小又会导致空间浪费。如果关注内存，填充比可以稍大，如果主要关注查找性能，填充比可以稍小。
         * static final float _LOAD_FACTOR = 0.75f;
         *
         * //一个桶的树化阈值
         * //当桶中元素个数超过这个值时，需要使用红黑树节点替换链表节点
         * //这个值必须为 8，要不然频繁转换效率也不高
         * static final int TREEIFY_THRESHOLD = 8;
         *
         * //一个树的链表还原阈值
         * //当扩容时，桶中元素个数小于这个值，就会把树形的桶元素 还原（切分）为链表结构
         * //这个值应该比上面那个小，至少为 6，避免频繁转换
         * static final int UNTREEIFY_THRESHOLD = 6;
         *
         * //哈希表的最小树形化容量
         * //当哈希表中的容量大于这个值时，表中的桶才能进行树形化
         * //否则桶内元素太多时会扩容，而不是树形化
         * //为了避免进行扩容、树形化选择的冲突，这个值不能小于 4 * TREEIFY_THRESHOLD
         * static final int MIN_TREEIFY_CAPACITY = 64;
         *
         * //存储数据的Entry数组，长度是2的幂。
         * transient Entry[] table;
         * //
         * transient Set<Map.Entry<K,V>> entrySet;
         * //map中保存的键值对的数量
         * transient int size;
         * //需要调整大小的极限值（容量*装载因子）
         * int threshold;
         * //装载因子
         * final float loadFactor;
         * //map结构被改变的次数
         * transient volatile int modCount;
         */


    }
}
