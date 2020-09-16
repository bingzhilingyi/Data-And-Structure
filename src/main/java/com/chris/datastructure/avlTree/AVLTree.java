package com.chris.datastructure.avlTree;

import com.chris.datastructure.bst.BST;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V>{

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private Integer size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for(int i=1;i<keys.size();i++)
            if(keys.get(i-1).compareTo(keys.get(i))>0)
                return false;
        return true;
    }

    private void inOrder(Node node,ArrayList<K> keys){
        if(node==null)
            return;
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    //判断该二叉树是否是一颗平衡二叉树
    public boolean isBalance(){
        return isBalance(root);
    }

    private boolean isBalance(Node node){
        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1)
            return false;
        return isBalance(node.left) && isBalance(node.right);
    }

    // 获得节点node的高度
    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    // 获得节点node的平衡因子
    //返回左子树的层高减去右子树层高
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        //更新Height
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }

    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node,K key, V value){

        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(node.key.compareTo(key)>0){
            node.left = add(node.left,key,value);
        }
        else if(node.key.compareTo(key)<0){
            node.right = add(node.right,key,value);
        }
        else if(node.key.compareTo(key)==0) {
            node.value = value;
        }
        //更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            System.out.println("unbalanced:"+balanceFactor);
        }

        //平衡维护
        //LL
        if(balanceFactor>1 && getBalanceFactor(node.left)>=0)
            return rightRotate(node);


        //RR
        if(balanceFactor<-1 && getBalanceFactor(node.right)<=0)
            return leftRotate(node);

        //LR
        if(balanceFactor>1 && getBalanceFactor(node.left)<0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if(balanceFactor<-1 && getBalanceFactor(node.right)>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //返回以Node为根节点的二分搜索树中,key所在的节点
    private Node getNode(Node node, K key){
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

    public boolean contains(K key) {
        return getNode(root,key)!=null;
    }

    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node cur = getNode(root,key);
        if(cur!=null){
            cur.value = value;
        }
        else {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    public Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }


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
}
