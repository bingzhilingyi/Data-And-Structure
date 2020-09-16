package com.chris.datastructure.segmentTree;

@FunctionalInterface
public interface Merger<E> {

    E merge(E a,E b);
}
