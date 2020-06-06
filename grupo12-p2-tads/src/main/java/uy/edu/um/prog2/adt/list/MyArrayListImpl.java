package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.exeptions.ListOutOfIndex;

import java.util.Iterator;

public class MyArrayListImpl<T> implements MyList<T> {

    private T[] array;
    private int pointerToLastValue;

    public MyArrayListImpl(int size) {
        this.array = (T[]) new Object[size];
        this.pointerToLastValue = 0;
    }

    @Override
    public void add(T valor, int posicion) throws ListOutOfIndex {
        if (posicion > pointerToLastValue){
            throw new ListOutOfIndex(pointerToLastValue);
        } else{
            if (pointerToLastValue >= this.array.length) {
                incrementArrayLength();
            }
            if (posicion == 0){
                addFirst(valor);
            } else if (posicion == pointerToLastValue){
                this.array[pointerToLastValue] = valor;
                pointerToLastValue++;
            } else {
                shiftRight(posicion);
                this.array[posicion] = valor;
                pointerToLastValue++;
            }
        }
    }

    @Override
    public void add(T valor) {
        if (pointerToLastValue >= this.array.length) {
            incrementArrayLength();
        }
        this.array[pointerToLastValue] = valor;
        pointerToLastValue++;
    }

    @Override
    public void addFirst(T valor) {
        if (pointerToLastValue >= this.array.length) {
            incrementArrayLength();
        }
        shiftRight(0);
        this.array[0] = valor;
        pointerToLastValue++;

    }

    @Override
    public boolean contains(T valor) {
        boolean retorno = false;

        for (int i=0; i<pointerToLastValue; i++){
            if (valor.equals(this.array[i])){
                retorno = true;
                break;
            }
        }


        return retorno;
    }

    @Override
    public T remove(int posicion) throws ListOutOfIndex {
        if (posicion >= pointerToLastValue){
            throw new ListOutOfIndex(pointerToLastValue);
        }
        T retorno = this.array[posicion];
        shiftLeft(posicion);
        this.array[pointerToLastValue-1] = null;
        pointerToLastValue--;

        return retorno;
    }

    @Override
    public T removeLast() {
        T retorno = null;
        try {
            if (this.getSize()>0) {
                retorno = remove(pointerToLastValue - 1);
            }
        } catch (ListOutOfIndex listOutOfIndex) {
            listOutOfIndex.printStackTrace();
        }
        return retorno;
    }

    @Override
    public T removeValue(T valor) {
        T retorno = null;

        for (int i=0; i<pointerToLastValue; i++){
            if(valor.equals(this.array[i])){
                retorno = this.array[i];
                try {
                    remove(i);
                } catch (ListOutOfIndex listOutOfIndex) {
                    listOutOfIndex.printStackTrace();
                }
                break;
            }
        }

        return retorno;
    }

    @Override
    public int get(int posicion) throws ListOutOfIndex {
        if (posicion >= pointerToLastValue){
            throw new ListOutOfIndex(pointerToLastValue);
        }
        return this.array[posicion];
    }

    @Override
    public T getValue(T valueToSearch) {
        T retorno = null;

        for (int i=0; i<pointerToLastValue; i++){
            if (valueToSearch.equals(this.array[i])){
                retorno = this.array[i];
                break;
            }
        }

        return retorno;
    }

    @Override
    public int getSize() {
        return pointerToLastValue;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator<>(this.array, this.pointerToLastValue);
    }




    private void shiftRight(int initialPosition){
        for (int i = pointerToLastValue; i>initialPosition; i--){
            this.array[i] = this.array[i-1];
        }
    }

    private void shiftLeft(int endPosition) {
        for (int i = endPosition; i<pointerToLastValue-1; i++){
            this.array[i] = this.array[i+1];
        }
    }


    private void incrementArrayLength() {

        T[] newArray = (T[]) new Object[(this.array.length) * 2];

        for(int i = 0; i<this.array.length;i++){
            newArray[i] = this.array[i];
        }
        this.array = newArray;

    }


}
