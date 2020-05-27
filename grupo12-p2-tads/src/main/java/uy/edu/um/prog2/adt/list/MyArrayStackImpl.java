package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.list.MyStack;

import java.util.Iterator;

public class MyArrayStackImpl<T> implements MyStack<T> {
    public T[] stackArray;
    public int sizeTotal;
    public int sizeDinamico;



    public MyArrayStackImpl(int sizeTotal) {
        this.stackArray = (T[]) new Object[sizeTotal];
        this.sizeTotal = sizeTotal;
        this.sizeDinamico = 0;
    }
    public void push(T value){
        stackArray[sizeDinamico]=  value;
        sizeDinamico++;
    }

    public T pop(){
        sizeDinamico--;
        T aSacar=  stackArray[sizeDinamico];
        stackArray[sizeDinamico]=null;

        return aSacar;
    }
    public T get(int posicion){
        T toQuit = stackArray[posicion];
        return toQuit;
    }



    public T top() {
        return stackArray[sizeDinamico-1];


    }

    @Override
    public boolean isEmpty() {
        if (sizeDinamico == 0){
            return true;
        }
        return false;
    }

    @Override
    public void makeEmpty() {
        stackArray = (T[]) new Object[sizeTotal];


    }

    @Override
    public int getSize() {
        return sizeDinamico;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
