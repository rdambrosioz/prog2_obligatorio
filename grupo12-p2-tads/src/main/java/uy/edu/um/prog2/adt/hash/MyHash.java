package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.list.MyList;

public interface MyHash<K,T> {


    void put(K key, T value);

    T remove(K key);

    T get(K key);

    int size();

    MyList<T> getValues();

}
