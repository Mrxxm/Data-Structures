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
Interface Stack<E>              —implement-->           ArrayStack<E>
    
* void push(E) // 入栈
* E pop() // 出栈
* E peek() // 查看栈顶元素       
* int getSize()
* boolean isEmpty()
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

## 队列实现

队列 Queue

* 队列也是一种线性结构
* 相比数组，队列操作也是数组的子集
* 只能从一端（队尾）添加元素，只能从另一端（队首）取出元素
* 队列是一种先进先出的数据结构（先到先得）
* First In First Out（FIFO）

队列实现：

```
Queue<E>
    void enqueue(E e)  O(1)均摊
    E dequeue()        O(n)
    E getFront()       O(1)
    int getSize()      O(1)
    boolean isEmpty()  O(1)
```

## 循环队列

数组队列产生的问题：dequeue()出队列时间复杂度是O(n),如果是百万级且更大数据，则消耗很大的资源。

解决(循环队列)：引入front，队首指针。入队时，维护tail，出队时，维护front。

队列为空、为满的情况：

* capacity中，需要浪费一个空间
* (tail + 1) % capacity == front 队列满
* 2 % 8 = 2



```
add ((tail + 1) % capacity != front) 队满判断
    tail = (tail + 1) % capacity;
remove (!isEmpty) 队空判断
    front = (front + 1) % capacity;
```

## 数组队列和循环队列的比较

数组队列：

```
ArrayQueue<E>
    void enqueue(E e)  O(1)均摊
    E dequeue()        O(n)
    E getFront()       O(1)
    int getSize()      O(1)
    boolean isEmpty()  O(1)
```

循环队列：


```
LoopQueue<E>
    void enqueue(E e)  O(1)均摊
    E dequeue()        O(1)均摊
    E getFront()       O(1)
    int getSize()      O(1)
    boolean isEmpty()  O(1)
```

十万次数据出队入队耗时结果：

```
ArrayQueue, time: 4.668743653 s
LoopQueue, time: 0.012076687 s
```


## 链表

线性数据结构：

* 动态数组
* 栈
* 队列

```
底层都是依托静态数组；
靠resize解决固定容量问题。
```

链表：

* 真正的动态数据结构
* 最简单的动态数据结构
* 更深入的理解引用（或者指针）   
* 更深入的理解递归
* 辅助组成其他数据结构


连表 Linked List：

* 数据存储在“节点”（Node）中



```
class Node {
    E e;
    Node next;
}
```

* 优点：真正的动态，不需要处理固定容量的问题
* 缺点：丧失了随机访问的能力


数组和链表的对比：

* 数组最好用于索引有语义的情况。
* 最大的优点：支持快速查询。

* 链表不适合用于索引有语意的情况。
* 最大的优点：动态

#### 添加元素

* 在链表头添加元素

```
node.next = head
head = node
```

* 在链表中间添加元素(在索引为2的地方添加元素666)

关键：找到要添加的节点的前一个节点
特殊情况：头位置是没有前一个节点，需要处理

```
node.next = prev.next
prev.next = node
```

* 代码

```
// 在链表的index(0除外)位置添加新的元素e
public void add(int index, E e) {
    if (index < 0 || index > size)
        throw new IllegalArgumentException("Add failed. Illegal index .");


    if (index == 0) {
        addFirst(e);
    } else {
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }


//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev.next);


        size++;
    }
}
```

问题遗留：设立虚拟的链表头节点，处理index == 0的情况。

#### 设立虚拟头节点


* 虚拟头节点

修改点：

```
#1
private Node dummyHead;

#2
public LinkedListDummy() {
    dummyHead = new Node(null, null);
    size = 0;
}

#3
// 在链表的index(0开始)位置添加新的元素e
public void add(int index, E e) {
    if (index < 0 || index > size)
        throw new IllegalArgumentException("Add failed. Illegal index .");


    Node prev = dummyHead;
    for (int i = 0; i < index; i++) {
        prev = prev.next;
    }


//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
    prev.next = new Node(e, prev.next);


    size++;
}
```

#### 元素的删除


* 删除索引为2位置上的元素

```
prev.next = delNode.next
delNode.next = null
```


链表的时间复杂度分析：

* 添加操作O(n)

```
addLast(e)    O(n)
addFirst(e)   O(1)
add(index, e) O(n/2) = O(n)
```

* 删除操作O(n)

```
removeLast(e)     O(n)
removeFirst(e)    O(1)
remove(index, e)  O(n/2) = O(n)
```

* 修改、查找O(n)

```
O(n)
```


只对链表头进行操作：

* 添加O(1)
* 删除O(1)
* 查找O(1)   

## 使用链表实现栈

数组栈和链表栈的性能比较：

数组栈：

```
Stack<E>
    
* void push(E)         O(1) 均摊
* E pop()              O(1) 均摊
* E peek()             O(1)
* int getSize()        O(1)
* boolean isEmpty()    O(1)
```

链表栈：

```
Stack<E>
    
* void push(E)         O(1) 
* E pop()              O(1) 
* E peek()             O(1)
* int getSize()        O(1)
* boolean isEmpty()    O(1)
```

## 使用链表实现队列(带有尾指针的链表)

1.使用链表实现队列，在头部插入还是尾部删除，尾部删除是O(n)；尾部插入头部删除，尾部插入是O(n)；总有一次操作是O(n)的操作。

2.同样的问题，我们在数组实现队列中就遇到，在数组的头部插入或者头部删除也都是O(n)操作，后面就引入了循环队列来解决这个问题。

* 循环队列

3.那么我们如何解决链表的问题：

* 添加尾节点tail

```
在tail节点添加一个节点是非常容易的，因为tail节点就是我们添加节点时的prev节点。
在tail节点删除一个节点的复杂度是O(n)，因为我们删除tail节点需要知道tail前一个节点，还是需要循环。
所以我们将在tail节点，入队。
在head节点，出队。
```

代码部分：

1.由于我们对队列链表都是在head端或tail端一侧完成，所以我们就不使用虚拟的头节点了。  
2.注意点：当链表为空，head和tail都指向空。

## Leetcode 203 删除链表中元素

* Solution1()

```
public ListNode removeElements(ListNode head, int val) {
    // 删除所有相同元素的头节点
    while (head != null && head.val == val) {
        ListNode delNode = head;
        head = head.next;
        delNode.next = null;
    }

    if (head == null) {
        return head;
    }

    ListNode prevNode = head;
    while (prevNode.next != null) {
        if (prevNode.next.val == val) {
//                ListNode delNode = prevNode.next;
//                prevNode.next = delNode.next;
//                delNode.next = null;
            prevNode.next = prevNode.next.next;
        } else {
            prevNode = prevNode.next;
        }

    }

    return head;
}
```

* Solution2(添加虚拟头结点实现)

```
public ListNode removeElements(ListNode head, int val) {
    // 初始dummy head
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    ListNode prevNode = dummyHead;
    while (prevNode.next != null) {
        if (prevNode.next.val == val) {
            prevNode.next = prevNode.next.next;
        } else {
            prevNode = prevNode.next;
        }
    }

    return dummyHead.next;
}
```

* Solution3(递归实现)

```
public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
        return null;
    }
//        # 1
//        ListNode res = removeElements(head.next, val);
//        if (head.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }
//        # 2
//        head.next = removeElements(head.next, val);
//        if (head.val == val) {
//            return head.next;
//        } else {
//            return head;
//        }
    head.next = removeElements(head.next, val);
    return head.val == val ? head.next : head;
}
```
最后简化成四行代码(牛皮啊！！！)

```
public ListNode removeElements(ListNode head, int val) {
    if (head == null) 
        return null;

    head.next = removeElements(head.next, val);
    return head.val == val ? head.next : head;
}
```

## 非线性数据结构-树结构

为什么要有树结构：

* 树结构本身是一种天然的组织结构
* 电脑中的文件夹，家谱等
* 高效
    

树：

* 二分搜索树（Binary Search Tree）
* 平衡二叉树：AVL
* 红黑树

算法对某种数据结构的高效处理：

* 堆
* 并查集
* 线段数 [处理线段]
* Trie（字典树，前缀数）[处理字符串]


## 二分搜索树基础

Binary Search Tree.

二叉树：



* 和链表一样，动态数据结构
* 二叉树具有唯一根节点
* 二叉树中每个节点最多有两个孩子
* 二叉树每个节点最多有一个父亲

```
class Node {
    E e;
    Node left;  // 左孩子
    Node right; // 右孩子
}
```

* 二叉树具有天然的递归结构
* 每个节点的左子树也是二叉树
* 每个节点的右子树也是二叉树


二分搜索树：

* 二分搜索树是二叉树
* 二分搜索树的每个节点的值：
*   大于其左子树的所有节点的值
*   小于其右子树的所有节点的值
* 每一棵子树也是二分搜索树
* 存储的元素必须有可比较性


基本实现：

```
package BinarySearchTree;


public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;


        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }


    private Node root;
    private int size;


    public BST() {
        root = null;
        size = 0;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }
}
```

### 二分搜索树添加元素

```
// 方法二
public void add(E e) {
    add(root, e);
}


// 方法二
private Node add(Node node, E e) {
    if (node == null) {
        size++;
        return new Node(e);
    }


    if (e.compareTo(node.e) < 0) {
        node.left = add(node.left, e);
    } else if (e.compareTo(node.e) > 0) {
        node.right = add(node.right, e);
    }


    return node;
}
```

### 二分搜索树的查询元素

```
// 二分搜索树的查询
public boolean contains(E e) {
    return contains(root, e);
}


private Boolean contains(Node node, E e) {
    if (node == null) {
        return false;
    }
   
    if (e.compareTo(node.e) == 0) {
      return true;
    } else if (e.compareTo(node.e) < 0) {
        return contains(node.left, e);
    } else if (e.compareTo(node.e) > 0) {
        return contains(node.right, e);
    }
}
```

### 前中后序遍历

前序遍历伪代码：

```
function traverse(node):
    if (node == null)
        return ;

    访问该节点
    traverse(node.left)
    traverse(node.right)
```

前序遍历(先访问根节点，再分别访问左子树节点和右子树节点)[根 左 右]：

* 递归实现
* 最自然的遍历方式
* 最常用的遍历方式

```
// 前序遍历
public void preOrder() {
    preOrder(root);
}


private void preOrder(Node node) {
    if (node == null)
        return;


    System.out.println(node.e);
    preOrder(node.left);
    preOrder(node.right);
}
```

中序遍历：(先访问左子树节点，再访问根节点和右子树节点)[左 根 右]

* 二分搜索树中序遍历的结果是顺序的

伪代码：

```
function traverse(node):
    if (node == null)
        return ;

    traverse(node.left)
    访问该节点
    traverse(node.right)
```

后序遍历的一个应用：

* 为二分搜索树释放内存

图示：

![](https://img9.doubanio.com/view/photo/l/public/p2620395805.jpg)


### 深度优先遍历(非递归)

前序遍历：(前中后都属于深度优先遍历)

* 使用栈的数据结构实现

```
1.先压入根节点
2.弹出根节点，先压入右孩子再压入左孩子
3.弹出左节点，先压入左节点的右孩子再压入左节点的左孩子
4.弹出左节点的左孩子，叶子节点，无需压入
5.弹出左节点的右孩子，叶子节点，无需压入
6.弹出右节点，先压入右节点的右孩子再压入右节点的左孩子
7.弹出右节点的左孩子，叶子节点，无需压入
8.弹出右节点的右孩子，叶子节点，无需压入
9.结束
```

代码：

```
// 前序遍历(非递归)
public void preOrderNR() {
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node cur = stack.pop();
        System.out.println(cur.e);


        if (cur.right != null)
            stack.push(cur.right);
        if (cur.left != null)
            stack.push(cur.left);
    }
}
```

### 广度优先遍历

层序遍历：(广度优先遍历)

* 使用队列数据结构实现遍历

```
1.入队根节点
2.出队根节点，再按从左到右的方式入队
3.出队左节点，入队再按从左到右的方式入队
4.出队右节点，入队再按从左到右的方式入队
5.出队左节点的左孩子，叶子节点，无需入队
6.出队左节点的右孩子，叶子节点，无需入队
7.出队右节点的左孩子，叶子节点，无需入队
8.出队右节点的右孩子，叶子节点，无需入队
9.结束
```

代码：

```
// 层序遍历
public void levelOrder() {
    Queue.LinkedListQueue<Node> queue = new Queue.LinkedListQueue<Node>();
    queue.enqueue(root);


    while (!queue.isEmpty()) {
        Node cur = queue.dequeue();
        System.out.println(cur.e);


        if (cur.left != null)
            queue.enqueue(cur.left);
        if (cur.right != null)
            queue.enqueue(cur.right);
    }
}
```




