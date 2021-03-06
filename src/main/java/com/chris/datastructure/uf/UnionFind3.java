package com.chris.datastructure.uf;

public class UnionFind3 implements UF{

    private int[] parent;
    private int[] sz; //每棵树的节点数

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for(int i=0;i<size;i++){
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //O(h)
    private int find(int p){
        if(p<0 || p>=parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p!= parent[p]){
            p = parent[p];
        }
        return p;
    }

    //看p与q是否属于一个集合 O(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    //进行了优化，虽然也是O(h),但是h小，所以速度很快
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot==qRoot)
            return;

        //把size小的树合并到size大的树里
        if(sz[pRoot]<sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot]+= sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += qRoot;
        }


    }
}
