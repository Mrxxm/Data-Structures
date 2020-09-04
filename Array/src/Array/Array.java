package Array;

public class Array {

    // TODO:类型修改
    private int[] data;

    // 最大等于 index = (capacity - 1)
    private int index;

    /**
     * 构造方法
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = new int[capacity];
        index = 0;
    }

    /**
     * 默认构造方法
     */
    public Array() {
        // TODO:大小修改
        this(10);
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
     * @return
     */
    int get(int index) {
        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException("Get failed. Index is illegal.");

        return data[index];
    }

    /**
     * 修改index索引位置的元素
     * @param index
     * @param element
     */
    void set(int index, int element) {
        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException("Get failed. Index is illegal.");

        data[index] = element;
    }


    /**
     * 向数组所有元素前添加一个新元素
     * @param element
     */
    public void addFirst(int element) {
        add(0, element);
    }

    /**
     * 向数组所有元素后添加一个新元素
     * @param element
     */
    public void addLast(int element) {
        add(index, element);
    }

    /**
     * 向数组指定位置添加元素
     * @param index
     * @param element
     */
    public void add(int index, int element) {
        // TODO:添加失败处理
        if (index == data.length)
            throw new IllegalArgumentException("Add failed. Array.Array is full.");

        if (index < 0 || index > this.index)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

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
    public boolean contains(int element) {
        for (int i = 0; i < index; i++) {
            if (data[i] == element) {
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
    public int find(int element) {
        for (int i = 0; i < index; i++) {
            if (data[i] == element) {
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
    public int remove(int index) {

        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException("Delete failed. Require index >= 0 and index < size.");

        int element = data[index];
        // index之后的每个元素，都让它向前挪一个位置
        for (int i = index + 1; i < this.index; i++) {
            // data[index] 存着还是原有元素
            data[i - 1] = data[i];
        }

        this.index--;

        return element;
    }

    /**
     * 删除数组中第一个元素
     * @return
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组中最后一个元素
     * @return
     */
    public int removeLast() {
        return remove(index - 1);
    }

    /**
     * 从数组中删除元素element
     * @param element
     */
    public void removeElement(int element) {
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
