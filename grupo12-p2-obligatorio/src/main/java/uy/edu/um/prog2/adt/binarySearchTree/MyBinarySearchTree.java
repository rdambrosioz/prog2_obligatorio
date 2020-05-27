package uy.edu.um.prog2.adt.binarySearchTree;

import uy.edu.um.prog2.adt.list.MyList;

public interface MyBinarySearchTree<K extends Comparable<K>, T> extends Iterable<T> {

    void insert (K key, T value);

    void delete (K key);

    boolean contains(K key);

    int size();

    int countLeaf();

    int countCompleteElement();

    MyList<T> inOrder();

    MyList<T> preOrder();

    MyList<T> postOrder();

    MyList<T> nivel();

}
