package com.chris.datastructure.bst;

import com.chris.datastructure.Stack.LinkedListStack;
import com.chris.datastructure.queue.LinkedListQueue;

import java.util.SplittableRandom;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public BST(E e){
        root = new Node(e);
        size = 1;
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root,e);
    }

    /**
     * 传入Node和e，如果Node为null则用新节点替代Node
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node,E e){
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }
        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)>0){
            return contains(node.right,e);
        }else {
            return contains(node.left,e);
        }
    }

    /**
     * 前序遍历,最常用
     * 深度遍历
     */
    public void preOrder(){
        preOrderNR(root);
    }

    /**
     * 前序遍历，递归实现
     * @param node
     */
    private void preOrder(Node node){
        if(node==null){
            return;
        }
        //遍历实现
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * 前序遍历，非递归实现
     * @param node
     */
    private void preOrderNR(Node node){
        if(node==null){
            return;
        }
        LinkedListStack<Node> stack = new LinkedListStack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历，生成的结果按大小顺序排列
     * 深度遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 中序遍历非递归实现
     * @param node
     */
    private void inOrderNR(Node node){
        if(node==null){
            return;
        }
        LinkedListStack<Node> stack = new LinkedListStack<>();
        stack.push(node);
        boolean skipLeft = false;
        while (!stack.isEmpty()){
            Node cur = stack.peek();
            if(cur==null){
                stack.pop();
                skipLeft = true;
                continue;
            }

            if(cur.left!=null && !skipLeft){
                stack.push(null);
                stack.push(cur.left);
                continue;
            }

            cur = stack.pop();
            System.out.println(cur.e);
            skipLeft = false;

            if(cur.right!=null){
                stack.push(cur.right);
            }
        }
    }

    /**
     * 后序遍历
     * 深度遍历
     * 应用场景：为二分搜索树释放内存
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 后序遍历非递归实现
     * @param node
     */
    private void postOrderNR(Node node){
        if( node == null ){
            return;
        }
        LinkedListStack<Node> stack = new LinkedListStack<>();
        stack.push(node);
        boolean skipAll = false;
        while (!stack.isEmpty()){
            Node cur = stack.peek();
            if(cur==null){
                stack.pop();
                skipAll = true;
                continue;
            }

            if((cur.right!=null||cur.left!=null) && !skipAll){
                stack.push(null);
                if(cur.right!=null){
                    stack.push(cur.right);
                }
                if(cur.left != null){
                    stack.push(cur.left);
                }
                continue;
            }

            cur = stack.pop();
            System.out.println(cur.e);
            skipAll = false;

        }
    }

    /**
     * 二分搜索树层序遍历
     * 广度遍历，优点是:更快找到问题的解，常用于算法设计中-最短路径
     */
    public void levelOrder(){
        levelOrder(root);
    }

    private void levelOrder(Node node){
        if( node == null ){
            return;
        }
        LinkedListQueue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(node);
        while (!queue.isEmpty()){
            Node cur = queue.dequeue();
            if(cur.left!=null){
                queue.enqueue(cur.left);
            }
            if(cur.right!=null){
                queue.enqueue(cur.right);
            }
            System.out.println(cur.e);
        }
    }

    /**
     * 查找二分搜索树最小元素
     * @return
     */
    public E minimum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
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
    public E maximum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    public Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
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

    public E removeMax(){
        E ret = maximum();
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

    /**
     * 删除二叉树中值等于e的节点
     * @param e
     */
    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 递归方法删除二叉树中值等于e的节点
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node,E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }
        else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
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

    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return  res.toString();
    }

    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node==null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }
}
