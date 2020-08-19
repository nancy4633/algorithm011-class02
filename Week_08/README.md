学习笔记
#指定位置的位运算#
1. 将x最右边的n位清零：      x&(~0<<n)
2. 获取x的第n位：           (x>>n)&1
3. 获取x的第n位的幂值：      x&(1<<n)
4. 仅将第n位置为1：         x｜(1<<n)
5. 仅将第n位置位0：         x&(~(1<<n))
6. 将最高位至第n位(含)清零： x&((1<<n)-1)
#实战位运算#
1. 判断奇偶
x%2==1    同     x&1==1
2. 
x>>1      同     x/=2
3.清零最低位的1
x=x&(x-1)
4.得到最低位的1
x&(-x)
#java中求二进制的负数#
全部取反，再加一

#布隆过滤器#
优点：
空间效率和查询效率高 & 有一定的误判率（哈希表是精确匹配）
组成：
一个超大的二进制向量（bit数组） & 一系列随机函数(哈希)
应用：
字处理软件中，需要检查一个英语单词是否拼写正确
在 FBI，一个嫌疑人的名字是否已经在嫌疑名单上
在网络爬虫里，一个网址是否被访问过
yahoo, gmail等邮箱垃圾邮件过滤功能
添加元素：
将要添加的元素给k个哈希函数
得到对应于位数组上的k个位置
将这k个位置设为1
查询元素：
将要查询的元素给k个哈希函数
得到对应于位数组上的k个位置
如果k个位置有一个为0，则肯定不在集合中
如果k个位置全部为1，则可能在集合中
#LRU缓存#

#排序算法#
比较类排序：
交换排序(冒泡排序 稳定，快速排序 O(nlogn))
插入排序(简单插入排序 稳定，希尔排序 )
选择排序(简单选择排序 ，堆排序 O(nlogn))
归并排序(二路归并排序 O(nlogn) 稳定，多路归并排序 O(nlogn) 稳定)
非比较类排序：
计数排序 O(n+k) 稳定
基数排序 O(n+k) 稳定
桶排序  O(n+k) 稳定
#排序算法java实现#
快速排序：
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
            counter++;
        }
    }
    int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
    return counter;
}
归并排序：
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }
堆排序：
static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2；
    int largest = i;
    if (left < length && array[left] > array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }
    if (largest != i) {
        int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
        heapify(array, length, largest);
    }
}
public static void heapSort(int[] array) {
    if (array.length == 0) return;
    int length = array.length;
    for (int i = length / 2-1; i >= 0; i-) 
        heapify(array, length, i);
    for (int i = length - 1; i >= 0; i--) {
        int temp = array[0]; array[0] = array[i]; array[i] = temp;
        heapify(array, i, 0);
    }
}