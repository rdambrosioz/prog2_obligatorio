package uy.edu.um.prog2.adt.interfaces;

public interface MyHeap<T extends Comparable<T>> {

    void insert(T value);

    T getMax();

    T deleteMax();

    int size();


}
