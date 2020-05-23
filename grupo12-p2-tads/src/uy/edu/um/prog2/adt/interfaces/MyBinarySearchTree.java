package uy.edu.um.prog2.adt.interfaces;

import java.util.List;

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
