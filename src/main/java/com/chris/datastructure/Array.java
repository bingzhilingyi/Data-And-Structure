package com.chris.datastructure;

public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组的容量capacity=10
     */
    public Array(){
        this(10);
    }

    /**
     * 构造函数，传入数组externalData以初始化data
     * @param externalData 外部传入的数组
     */
    public Array(E[] externalData){
        this();
        if(externalData != null && externalData.length > 0 ){
            data = externalData;
            size = externalData.length;
        }
    }

    /**
     * 获取数组的元素个数
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     * @return capacity
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回数组是否为空
     * @return is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在第index个位置插入一个新元素e
     * @param index
     * @param e
     */
    public void add(int index,E e){

        if(index<0 || index > size)
            throw new IllegalArgumentException("add failed. Require index>=0 and index < size");

        if(size == data.length)
            resize(2*data.length);

        for(int i = size-1;i>=index;i--){
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e 新元素
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 向所有元素前添加一个新元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("get failed. Index is illegal");
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    /**
     * 设置index索引位置的元素
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("set failed. Index is illegal");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for(int i=0;i<size;i++){
            if(data[i]!=null && data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，返回-1
     * warn: 如果e存在多个，则只返回排序第一的索引
     * @param e
     * @return
     */
    public int find(E e){
        for(int i=0;i<size;i++){
            if(data[i]!=null && data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找数组中元素e所在的所有索引，如果不存在元素e，返回-1
     * @param e
     * @return
     */
    public int[] findAll(E e){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<size;i++){
            if(data[i]!=null && data[i].equals(e)){
                str.append(i+",");
            }
        }
        if(str.toString().length()==0){
            return null;
        }else{
            String indexsStr = str.toString();
            indexsStr = indexsStr.substring(0,indexsStr.length()-1);
            String[] indexsArr = indexsStr.split(",");
            int[] indexs = new int[indexsArr.length];
            for(int i=0;i<indexsArr.length;i++){
                indexs[i] = new Integer(indexsArr[i]);
            }
            return indexs;
        }
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("delete failed. Index is illegal");
        }

        E ret = data[index];
        for(int i=index;i<size-1;i++){
            data[i] = data[i+1];
        }
        size--;
        data[size] = null;
        if(size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素，返回删除的元素
     * @return
     */
    public E removeFirst(){
        return this.remove(0);
    }

    /**
     * 从数组中删除最后一个元素，返回删除的元素
     * @return
     */
    public E removeLast(){
        return this.remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * warn: 如果数组中存在多个元素等于e，则只会删除其中排名靠前的一个
     * @param e
     */
    public boolean removeElement(E e){
        int index = this.find(e);
        if(index != -1 ){
            this.remove(index);
            return true;
        }
        return false;
    }

    /**
     * 从数组中删除所有元素e
     * @param e
     * @return
     */
    public boolean removeAllElements(E e){
        boolean ret = false;
        int[] indexs = findAll(e);
        if(indexs!=null){
            for(int i=0;i<indexs.length;i++){
                //remove会使被删除元素后的所有元素的索引减一
                //因此每remove一次，下一个要删除元素的位置就会提前一位
                remove(indexs[i]-i);
                ret = true;
            }
        }
        return ret;
    }

    public void swap(int i,int j){
        if(i<0||i>=size||j<0||j>=size){
            throw new IllegalArgumentException("index is illegal");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        res.append('[');
        for(int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i=0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
