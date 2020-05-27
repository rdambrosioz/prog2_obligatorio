package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.exeptions.EmptyQueue;

public interface MyQueue<T> extends Iterable<T>{

    void enqueue (T element);

    void enqueueWithPriority (T element);

    T dequeue () throws EmptyQueue;

    boolean isEmpty();

    int getSize();


}
