package uy.edu.um.prog2.adt.list;

import uy.edu.um.prog2.adt.exeptions.EmptyList;
import uy.edu.um.prog2.adt.exeptions.ListOutOfIndex;

public interface MyList<T> extends Iterable<T> {

    void add (T valor, int posicion) throws ListOutOfIndex;
    void add (T valor);
    void addFirst (T valor);

    boolean contains(T valor);

    T remove (int posicion) throws ListOutOfIndex;
    T removeLast ();
    T removeValue (T valor);

    T get (int posicion) throws ListOutOfIndex;
    T getValue (T valueToSearch);


    int getSize();

    void interchange(T valor, int direccion) throws EmptyList;

}
