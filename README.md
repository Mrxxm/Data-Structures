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

## 均摊复杂度和防止复杂度的震荡

resize的复杂度分析：

```
假设当前capacity = 8,并且每一次添加操作都使用addLast

当进行第九次操作，触发resize
* 操作 8 + 1 次
* resize 8 次
总共进行了17次基本操作

平均，每次addLast操作，进行了两次基本操作
```
```
假设当前capacity = n,进行n + 1次addLast, 触发resize，总共进行2n + 1次基本操作
```
```
平均，每次addList操作，进行2次基本操作
这样均摊计算，时间复杂度是O(1)!
```
```
在这个例子里，这样均摊计算，比计算最坏情况有意义。
```
均摊复杂度：

* addLast的均摊复杂度为O(1)
* 同理，removeLast操作，均摊复杂度为O(1)

复杂度震荡：

```
同时分析addLast和removeLast操作：

capacity = n 
size = n

addLast    -> resize O(n)
removeLast -> resize O(n)
addLast    -> resize O(n)
removeLast -> resize O(n)
```

出现问题原因：

```
removeLast时resize过于着急（Eager）
解决方法：Lazy
当现有容量是总容量的1/4时，我们将数组缩小1/2
```

bug:

```
缩容过程中，data.length有可能等于1，data.length/2有可能等于0；

&& data.length / 2 != 0
```

## 栈的实现

栈的实现：

Stack<E>
    
```
* void push(E) // 入栈
* E pop() // 出栈
* E peek() // 查看栈顶元素       
* int getSize()
* boolean isEmpty()
```

```
Interface Stack<E>              —implement-->           ArrayStack<E>
    
* void push(E) // 入栈
* E pop() // 出栈
* E peek() // 查看栈顶元素       
* int getSize()
* boolean isEmpty()
```

* 调用

```
public static void main(String[] args) {
    ArrayStack<Integer> stack = new ArrayStack<Integer>();


    stack.push(1);
    stack.push(2);
    stack.push(3);


    System.out.println(stack);


    stack.pop();


    System.out.println(stack);


    System.out.println(stack.peek());
}
```

* 结果

```
Stack: [1, 2, 3] top
Stack: [1, 2] top
2
```

* 栈的复杂度分析

```
Stack<E>
    
* void push(E)         O(1) 均摊
* E pop()              O(1) 均摊
* E peek()             O(1)
* int getSize()        O(1)
* boolean isEmpty()    O(1)
```