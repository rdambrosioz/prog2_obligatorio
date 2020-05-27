package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.exeptions.EmptyStack;


public interface  MyStack<T> extends Iterable<T> {

    public void push(T element);

    public T pop() throws EmptyStack;

    public T top() throws EmptyStack;

    public boolean isEmpty();

    public void makeEmpty();

    public int getSize();

}


