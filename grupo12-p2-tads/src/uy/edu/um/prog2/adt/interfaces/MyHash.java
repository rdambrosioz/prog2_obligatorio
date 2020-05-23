package uy.edu.um.prog2.adt.interfaces;

public interface MyHash<K,T> {


    void put(K key, T value);

    T remove(K key);

    T get(K key);

    int size();
}
