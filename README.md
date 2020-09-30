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

## 删除二分搜索树的最大元素和最小元素

寻找二分搜索树中最小元素

```
// 寻找二分搜索树的最小元素
public E minimum() {
    if (size == 0)
        throw new IllegalArgumentException("BST is empty");


    return (E)minimum(root).e;
}


// 返回以node为根的二分搜索树的最小值所在的节点
private Node minimum(Node node) {
    if (node.left == null)
        return node;


    return minimum(node.left);
}
```

删除最小元素

```
// 从二分搜索树种删除最小值所在的节点，返回最小值
public E removeMin() {
    E ret = minimum();


    // 删除处理
    root = removeMin(root);


    return ret;
}


// 删除掉以node为根的二分搜索树中的最小节点
// 返回删除节点后新的二分搜索树的根
private Node removeMin(Node node) {


    // 递归到底的情况
    if (node.left == null) {
        Node rightNode = node.right;
        node.right = null;
        size--;


        return rightNode;
    }


    node.left = removeMin(node.left);


    return node;
}
```

测试：

```
// 6-11
Random random = new Random();
int n = 1000;


for (int i = 0; i < n; i++) {
    bst.add(random.nextInt(10000));
}


ArrayList<Integer> nums = new ArrayList<>();
while (!bst.isEmpty()) {
    nums.add(bst.removeMin());
}


System.out.println(nums);
// 检查数组是否从小到大
for (int i = 1; i < nums.size(); i++)
    if (nums.get(i - 1) > nums.get(i))
        throw new IllegalArgumentException("Error");
    
System.out.println("removeMin test completed.");    
```

输出：

```
[3, 10, 25, 33, 46, 64, 65, 82, 86, 92, 97, 98, 124, 145, 151 ...
```

## 删除二分搜索树的任意元素

删除左右孩子都有的节点：

* 待删除节点d
* s = min( d->right )
* s 是 d的后继
* s->right = delMin(d->right)
* s->left = d->left
* 删除d，s是新的子树的根

代码：

```
// 删除已node为根的二分搜索树中值为e的节点，递归算法
// 返回删除节点后新的二分搜索树的根
private Node remove(Node node, E e) {
    if (node == null) {
        return null;
    }


    if (e.compareTo((E) node.e) < 0) {
        node.left = remove(node.left, e);
        return node;
    } else if (e.compareTo((E) node.e) > 0) {
        node.right = remove(node.right, e);
        return node;
    } else { // e == node.e
        // 待删除节点左子树为空的情况
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;


            return rightNode;
        }
        // 待删除节点右子树为空的情况
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;


            return leftNode;
        }
        // 待删除节点左右子树均不为空的情况
        // 找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
        // 用这个节点顶替待删除节点的位置
        Node successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;


        node.left = node.right = null;


        return successor;
    }
}
```

## 集合基础

集合和映射 Set and Map

集合：

* 回忆上一小节实现的二分搜索树
* 不能盛放重复元素
* 非常好的实现“集合”的底层数据结构

```
public interface Set<E> {
    void add(E e); // 不能添加重复元素
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
```

应用：

* 客户统计
* 词汇量统计

## 集合类的复杂度分析


测试二分搜索树实现的集合、链表实现的集合：

```
public class Main {
    public static void main(String[] args) {
        System.out.println(testSet(new BSTSet<Integer>()));
        System.out.println(testSet(new LinkedListSet<Integer>()));
    }


    private static double testSet(Set<Integer> set) {
        long startTime = System.nanoTime();


        Random random = new Random();


        int n = 100000;


        for (int i = 0; i < n; i++) {
            set.add(random.nextInt(10000));
        }


        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.00;
    }
}
```

结果：

```
0.15384551     0.071143627   0.116433828
3.53042609     3.6588218     3.774737535
```

集合复杂度分析：


```
                        LinkedListSet                     BSTSet BSTSet平均(满二叉树) BSTSet最差
增 add                  O(n)                              O(h)   O(logn)             O(n)

查 contains             O(n)                              O(h)   O(logn)             O(n)

删 remove               O(n)                              O(h)   O(logn)             O(n)
```

h和n之间的关系：

```
0层： 1
1层： 2
2层： 4
3层： 8
···
h-1层： 2^(h-1)
```

h层，一共有多少个节点？

```
2^0 + 2^1 + 2^2 + 2^3 + … + 2^h-1
```


h和n之间的关系：

```
h = ··· = O(logn)
```


时间复杂度logn和n的差距：

···

二分搜索树最坏的情况：

* 二分搜索树可能退化成链表

* h = n

## 804.唯一摩尔斯密码词

```
public int uniqueMorseRepresentations(String[] words) {
    String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};


    TreeSet<String> set = new TreeSet<>();


    for (String word: words) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            res.append(codes[word.charAt(i) - 'a']);
        }
        set.add(res.toString());
    }


    return set.size();
}
```

## 映射基础

Map，映射，字典

高中所学的函数：

* 定义域中的一个值，总会在值域中找到对应关系



映射：

* 存储（键，值）数据对的数据结构（Key，Value）
* 根据键（Key），寻找值（Value）
* 非常容易使用链表或者二分搜索树实现

二分搜索树、链表实现的集合：

```
class Node {  
    E e;
    Node left;
    Node right;
}
```

```
class Node {
    E e;
    Node next;
}
```

二分搜索树、链表实现的映射：

```
class Node {  
    K key;
    V Value;
    Node left;
    Node right;
}
```

```
class Node {
    K key;
    V Value;
    Node next;
}
```

接口：

```
public interface Map<K, V> {
    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    int getSize();
    boolean isEmpty();
    V get(K key);
    void set(K key, V value);
}
```

## 映射的复杂度分析

词频统计。

```
基于二分搜索树的映射执行时间是：0.7403 s
基于链表的映射执行时间是：32.2726
```

映射的时间复杂度分析：

```
                 LinkedListMap               BSTMap     BSTMap平均 BSTMap最差(后续引入平衡二叉树解决)
add              O(n)                        O(h)       O(logn)   O(n)

remove           O(n)                        O(h)       O(logn)   O(n)

set              O(n)                        O(h)       O(logn)   O(n)

get              O(n)                        O(h)       O(logn)   O(n)

contains         O(n)                        O(h)       O(logn)   O(n)

```

有序映射和无序映射：

* 有序映射汇总的键具有顺序性  <- 基于搜索树的实现
* 无序映射中的键没有顺序性      <- 基于哈希表实现

多重映射：

* 多重映射中的键可以重复



## Leetcode

349.两个数组的交集
350.两个数组的交集

## 优先队列

堆和优先队列。

什么是优先队列：

* 普通队列：先进先出，后进后出
* 优先队列：出队顺序和入队顺序无关，和优先级相关

应用：

* 操作系统中任务的调度(动态选择优先级最高的任务执行)
* 游戏中自动打怪

```
public interface Queue<E> {            <————      PriortyQueue<E>
    void enqueue(E e);                 implement
    E dequeue();
    E getFront();
    int getSize();                                可以使用不同的底层实现
    boolean isEmpty();
}
```

底层实现分析：

```
                入队              出队(拿出最大元素)
普通线性结构      O(1)              O(n)
顺序线性结构      O(n)              O(1)
    堆          O(logn)           O(logn)

```

## 堆的基础结构

二叉堆(Binary Heap)



* 二叉堆是一棵完全二叉树


* 完全二叉树：把元素顺序按层从左到右排列成树的形状
* 堆中某个节点的值总是不大于其父节点的值(最大堆：相应的可以定义最小堆)

最大堆：



用数组存储二叉堆：

```
parent(i) = i/2

left child (i) = 2 * i
right child (i) = 2 * i + 1
```

数组0位置不置空作为存储空间：

```
parent(i) = (i - 1)/2

left child (i) = 2 * i + 1
right child (i) = 2 * i + 2

```





















