package uy.edu.um.prog2.adt.list.linkedlist;

import java.util.Iterator;

public class MyLinkedListIterator<T> implements Iterator<T> {


    private Node<T> nodo;

    public MyLinkedListIterator(Node<T> nodo) {
        this.nodo = nodo;
    }


    @Override
    public boolean hasNext() {
        return (nodo != null);
    }

    @Override
    public T next() {
        T valueToReturn = nodo.getValor();
        nodo = nodo.getSiguiente();
        return valueToReturn;
    }
}

