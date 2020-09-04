## 简单的复杂度分析


* O(1)、O(n)、O(lgn)、O(nlogn)、O(n^2)

* 大O描述的是算法的运行时间和输入数据之间的关系

```
public static int sum(int[] nums) {
    int sum = 0;
    for (int num: nums) sum += num;
    return sum;
}
```

通常来说这个算法是O(n)：

* 对n的定义：n是nums中的元素个数
* 解释：算法运行时间的多少和nums存在的元素的多少呈线性关系(算法和n呈线性关系)

为什么要用大O，叫做O(n)？

* 忽略常数。实际时间：T = c1*n + c2
* c1：num从nums中取出花费的时间，num运算赋值给sum的时间
* c2：sum的初始化和赋值0的时间，return返回sum值的时间

所以：

```
* T = 2*n + 2                O(n)
* T = 2000*n + 1000          O(n)
* T = 1*n*n + 0              O(n^2)
* T = 2*n*n + 300n + 10      O(n^2)
```

得出：

* 大O表示：渐进时间复杂度
* 渐进表示：描述n趋近于无穷的情况


分析动态数组的时间复杂度：

* 添加操作O(n)

```
addLast(e)         O(1)
addFirst(e)        O(n)           整体:O(n) 通常考虑最坏的情况   resize O(n)
add(index, e)      O(n)
```
```
add(index, e)      O(n/2) = O(n)
严格计算需要一些概率知识
```

* 删除操作O(n)

```
removeLast()       O(1)
removeFirst()      O(n)           整体:O(n)                resize O(n)
remove(index, e)   O(n/2) = O(n)
```

* 修改操作O(1)

```
set(index, e)      O(1)
```

* 查询操作O(n)

```
get(index)         O(1)
contains(e)        O(n)
find(e)            O(n)
```

分析动态数组的时间复杂度结果：

```
* 增：O(n)
           问题：增、删：如果只对最后一个元素操作依然是O(n)?因为resize()?
* 删：O(n)
* 改：已知索引O(1); 位置索引O(n)
* 查：已知索引O(1); 位置索引O(n)
```
