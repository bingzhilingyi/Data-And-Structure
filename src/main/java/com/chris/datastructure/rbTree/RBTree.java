package com.chris.datastructure.rbTree;

import com.chris.datastructure.Stack.LinkedListStack;
import com.chris.datastructure.queue.LinkedListQueue;

import java.util.SplittableRandom;

public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //判断节点Node的颜色
    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x = node.right;
        Node T2 = x.left;

        x.left = node;
        node.right = T2;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){

        Node x = node.left;
        Node T1 = x.right;

        // 右旋转
        x.right = node;
        node.left = T1;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


    public void add(K key,V value){
        root = add(root,key,value);
    }

    /**
     * 向以node魏根的红黑树中插入元素key,value，递归算法
     * 返回插入新节点后红黑树的根
     * @param node
     * @param key，value
     * @return
     */
    private Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value); //默认插入红色节点
        }
        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }

        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if(isRed(node.left) & isRed(node.right))
            flipColors(node);

        return node;
    }

    public boolean contains(K key){
        return contains(root,key);
    }

    private boolean contains(Node node,K key){
        if(node==null){
            return false;
        }
        if(key.compareTo(node.key)==0){
            return true;
        }else if(key.compareTo(node.key)>0){
            return contains(node.right,key);
        }else {
            return contains(node.left,key);
        }
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    public Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
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

    /**
     * 删除二叉树中值等于e的节点
     * @param key
     */
    public V remove(K key){
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 递归方法删除二叉树中值等于e的节点
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if(node == null){
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
        else { //e.compareTo(node.e)==0
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
