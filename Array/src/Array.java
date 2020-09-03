public class Array {

    private int[] data;

    private int size;

    /**
     * 构造方法
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 默认构造方法
     */
    public Array() {
        this(10);
    }

    
}
