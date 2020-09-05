package Stack;

public class SolutionSelfStack {

    public class ArrayGenericsDynamic<E> {

        private E[] data;

        // 最大等于 index = (capacity - 1)
        private int index;

        /**
         * 构造方法
         * @param capacity 容量
         */
        public ArrayGenericsDynamic(int capacity) {
            data = (E[])new Object[capacity];
            index = 0;
        }

        /**
         * 默认构造方法
         */
        public ArrayGenericsDynamic() {
            this(10);
        }


        /**
         * 动态数组
         * @param newCapacity
         */
        private void resize(int newCapacity) {
            E[] newData = (E[])new Object[newCapacity];

            for (int i = 0; i < index; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        /**
         * 获取数组中的元素个数
         * @return index
         */
        public int getSize() {
            return index;
        }


        /**
         * 获取数组中的容量
         * @return data.length
         */
        public int getCapacity() {
            return data.length;
        }


        /**
         * 判断数组是否为空
         * @return bool
         */
        public boolean isEmpty() {
            return index == 0;
        }

        /**
         * 获取index索引位置的元素
         * @param index
         * @returnE
         */
        public E get(int index) {
            if (index < 0 || index >= this.index)
                throw new IllegalArgumentException("Get failed. Index is illegal.");

            return data[index];
        }

        public E getFirst() {
            return get(0);
        }

        public E getLast() {
            return get(index - 1);
        }

        /**
         * 修改index索引位置的元素
         * @param index
         * @param element
         */
        void set(int index, E element) {
            if (index < 0 || index >= this.index)
                throw new IllegalArgumentException("Get failed. Index is illegal.");

            data[index] = element;
        }


        /**
         * 向数组所有元素前添加一个新元素
         * @param element
         */
        public void addFirst(E element) {
            add(0, element);
        }

        /**
         * 向数组所有元素后添加一个新元素
         * @param element
         */
        public void addLast(E element) {
            add(index, element);
        }

        /**
         * 向数组指定位置添加元素
         * @param index
         * @param element
         */
        public void add(int index, E element) {

            if (index < 0 || index > this.index)
                throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

            // 动态数组
            if (index == data.length)
                resize(2 * data.length);

            // 索引指到最后一个元素，每一个元素都向后挪一个位置
            for (int i = this.index - 1; i >= index; i--) {
                // data[index] 存着还是原有元素
                data[i + 1] = data[i];
            }

            data[index] = element;
            this.index++;
        }

        /**
         * 包含-查找数组中是否有包含元素element
         * @param element
         * @return
         */
        public boolean contains(E element) {
            for (int i = 0; i < index; i++) {
                if (data[i].equals(element)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 搜索-查找数组中包含元素element的索引值
         * @param element
         * @return
         */
        public int find(E element) {
            for (int i = 0; i < index; i++) {
                if (data[i].equals(element)) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 删除数组中index位置的元素，返回删除的元素
         * @param index
         * @return
         */
        public E remove(int index) {

            if (index < 0 || index >= this.index)
                throw new IllegalArgumentException("Delete failed. Require index >= 0 and index < size.");

            E element = data[index];
            // index之后的每个元素，都让它向前挪一个位置
            for (int i = index + 1; i < this.index; i++) {
                // data[index] 存着还是原有元素
                data[i - 1] = data[i];
            }

            this.index--;
            data[this.index] = null; // 垃圾回收

            if (this.index == data.length / 4 && data.length / 2 != 0)
                resize(data.length / 2);

            return element;
        }

        /**
         * 删除数组中第一个元素
         * @return
         */
        public E removeFirst() {
            return remove(0);
        }

        /**
         * 删除数组中最后一个元素
         * @return
         */
        public E removeLast() {
            return remove(index - 1);
        }

        /**
         * 从数组中删除元素element
         * @param element
         */
        public void removeElement(E element) {
            int index = find(element);
            if (index != -1) {
                remove(index);
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(String.format("Array.Array: size = %d, capacity = %d\n", index, data.length));

            String ret = "[";
            for (int i = 0; i < index; i++) {
                ret += data[i];
                if (i != index - 1) {
                    ret = ret + ", ";
                }
            }
            ret += "]";
            result.append(ret);

            return result.toString();
        }

    }

    public interface Stack<E> {

        void push(E element);
        E pop();
        // 查看栈顶元素
        E peek();
        int getSize();
        boolean isEmpty();
    }

    public class ArrayStack<E> implements Stack<E> {

        // 动态数组成员变量
        ArrayGenericsDynamic<E> array;

        public ArrayStack(int capacity) {
            array = new ArrayGenericsDynamic<E>(capacity);
        }

        public ArrayStack() {
            array = new ArrayGenericsDynamic<E>();
        }

        public int getCapacity() {
            return array.getCapacity();
        }

        @Override
        public int getSize() {
            return array.getSize();
        }

        @Override
        public boolean isEmpty() {
            return array.isEmpty();
        }

        @Override
        public void push(E element) {
            array.addLast(element);
        }

        @Override
        public E pop() {
            return array.removeLast();
        }

        @Override
        public E peek() {
            return array.getLast();
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Stack: ");

            String ret = "[";
            for (int i = 0; i < array.getSize(); i++) {
                ret += array.get(i);
                if (i != array.getSize() - 1) {
                    ret = ret + ", ";
                }
            }
            ret += "] top";
            result.append(ret);

            return result.toString();
        }
    }


    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
