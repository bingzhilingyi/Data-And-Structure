package com.chris.datastructure.uf;

//路径压缩的并查集
public class UnionFind5 implements UF{

    private int[] parent;
    private int[] rank; //每棵树的深度

    public UnionFind5(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i=0;i<size;i++){
            parent[i] = i;
            rank[i] = 1;
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
            //路径压缩 path compression
            parent[p] = parent[parent[p]];
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

        //把rank小的树合并到rank大的树上
        if(rank[pRoot]<rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot]>rank[qRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }


    }
}
