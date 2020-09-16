package com.chris.datastructure.map;

import com.chris.datastructure.bst.BST;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V>{

    private class Node{
        public K key;
        public V value;
        public BSTMap.Node left;
        public BSTMap.Node right;

        public Node(K key,V value,Node left,Node right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private Integer size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    private Node getNode(Node node,K key){
        if(node==null){
            return null;
        }
        else if(node.key.compareTo(key)==0){
            return node;
        }
        else if(node.key.compareTo(key)>0){
            return getNode(node.left,key);
        }
        else { //node.key.compareTo(key)<0
            return getNode(node.right,key);
        }
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node,K key, V value){
        size++;
        if(node == null){
            return new Node(key,value,null,null);
        }
        if(node.key.compareTo(key)>0){
            node.left = add(node.left,key,value);
        }
        else if(node.key.compareTo(key)<0){
            node.right = add(node.right,key,value);
        }
        else if(node.key.compareTo(key)==0) {
            node.value = value;
            size--;
        }
        return node;
    }
    /**
     * 查找二分搜索树最小元素
     * @return
     */
    public V minimum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).value;
    }

    public Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 查找二分搜索树最大元素
     * @return
     */
    public V maximum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).value;
    }

    public Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    public V removeMin(){
        V ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left==null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V removeMax(){
        V ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right==null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }



    @Override
    public V remove(K key) {

        Node node = getNode(root,key);
        if(node!=null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node,K key){
        if(node==null){
            return null;
        }
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }
        else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }
        else { //key.compareTo(node.key)==0
            //如果左子树为空，则删除自己，返回自己的右子树
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //如果右子树为空，则删除自己，返回自己的左子树
            if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树不为空的情况
            // 找到比待删除节点大的最小节点,即待删除节点右子树的最小节点
            // 用这个节点替代待删除节点的位置
            Node successor = minimum(node.right);
            // 顺序必须是先right再left。
            // 如果先设置successor的left，那么removeMin(node.right)删除的就不是successor
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left=node.right=null;

            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key)!=null;
    }

    @Override
    public V get(K key) {
        Node cur = getNode(root,key);
        if(cur!=null){
            return cur.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node cur = getNode(root,key);
        if(cur!=null){
            cur.value = value;
        }
        else {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size!=0;
    }
}
