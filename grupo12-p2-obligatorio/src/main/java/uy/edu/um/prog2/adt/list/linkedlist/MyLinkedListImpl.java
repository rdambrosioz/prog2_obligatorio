package uy.edu.um.prog2.adt.list.linkedlist;

import uy.edu.um.prog2.adt.exeptions.EmptyList;
import uy.edu.um.prog2.adt.exeptions.EmptyQueue;
import uy.edu.um.prog2.adt.exeptions.EmptyStack;
import uy.edu.um.prog2.adt.exeptions.ListOutOfIndex;
import uy.edu.um.prog2.adt.list.*;

import java.util.Iterator;

public class MyLinkedListImpl<T> implements MyList<T>, MyStack<T>, MyQueue<T> {
    private Node<T> primero;
    private Node<T> ultimo;
    private int size;


    public MyLinkedListImpl() {
        this.primero = null;
        this.ultimo = null;
        size = 0;
    }


    /**
     * AQUI SE ENCUENTRAN TODOS LOS DISTINTOS CODIGOS DE AGREGARA AUNA LISTA
     */
    @Override
    public void add(T valor, int posicion) throws ListOutOfIndex {

        if (posicion == 0){
            addFirst(valor);
        } else if (posicion == (size-1)){
            addLast(valor);
        }else{
            Node<T> anterior = devolverNodoPosicion(posicion-1);
            Node<T> temp = new Node<>( valor);
            temp.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(temp);
            size++;
        }

    }       //Operacion de la interfaz. Agrega al final
    @Override
    public void add(T valor) {

        addLast(valor);
    }
    @Override
    public void addFirst(T valor){                     //Agrega al principio


        Node<T> nuevoNodo = new Node<T>(valor);

        if (primero == null){                   //Si la lista esta vacia, agrega solamente
            primero = nuevoNodo;
            size++;
            ultimo = nuevoNodo;
        }else{                                  //Lista no vacia, le agrega puntero al nuevo
            nuevoNodo.setSiguiente(primero);
            primero = nuevoNodo;
            size++;
        }
    }

    /**
     * AQUI SE ENCUENTRAN TODOS LOS DISTINTOS CODIGOS DE REMOVER UN ELEMENTO DE UNA LISTA LISTA
     */
    @Override
    public T remove(int posicion) throws ListOutOfIndex {

        T retorno = null;
        if (posicion == size-1){                                //En este caso se elimina el ultimo indirectamente
            retorno =  removeLast();
        } else if (posicion == 0){
            retorno = primero.getValor();
            Node<T> nuevoPri = primero.getSiguiente();
            primero.setSiguiente(null);
            primero = nuevoPri;
            size--;
        } else{
            Node<T> temp = devolverNodoPosicion(posicion - 1);     //Busca el anterior del que quiero elminar
            Node<T> eliminar = temp.getSiguiente();                //Encuentro el que quiero eliminar
            retorno = eliminar.getValor();
            temp.setSiguiente(eliminar.getSiguiente());         //Le doy al nodo anterior el siguiente del que voy  eliminar
            eliminar.setSiguiente(null);
            size--;
        }
        return retorno;
    }
    @Override
    public T removeLast() {
        T devolver = null;

        if (size == 0){                                    //Si el ultimo es nulo, no hace nada
        } else{
            devolver = ultimo.getValor();
            if (size == 1) {                                 //Si hay 1 solo elemento lo elimina a ese
                primero = null;
                ultimo = null;
                size--;
            } else{
                Node<T> temp = null;
                try {
                    temp = devolverNodoPosicion(size-2);            //Va al penultimo nodo y elimina, sabiendo que no hay forma que de error
                } catch (ListOutOfIndex listOutOfIndex) {
                    listOutOfIndex.printStackTrace();
                }
                temp.setSiguiente(null);
                ultimo = temp;
                size--;
            }
        }
        return devolver;
    }
    @Override
    public T removeValue(T valor) {
        Node<T> anterior = null;
        Node<T> temp = primero;
        for(int i = 0; i < size; i++){
            if (valor.equals(temp.getValor())){
                if(temp.equals(primero)){
                    try {
                        remove(0);
                    } catch (ListOutOfIndex listOutOfIndex) {
                        listOutOfIndex.printStackTrace();
                    }
                } else{
                    if(size > 2) {
                        anterior.setSiguiente(temp.getSiguiente());
                    } else{
                        anterior.setSiguiente(null);
                        ultimo = anterior;
                    }
                    size--;
                }
                return temp.getValor();
            }
            anterior = temp;
            temp = temp.getSiguiente();

        }

        return null;
    }

    /**
     * AQUI SE ENCUENTRAN TODOS LOS DISTINTOS CODIGOS QUE PERMITEN OBETNER UN ELEMENTO DE LA LISTA
     * O CHECKEAR QUE UN ELEMENTO ESTE EN LA LISTA
     */
    @Override
    public T get(int posicion) throws ListOutOfIndex {         //Devuelve el valor
        return devolverNodoPosicion(posicion).getValor();
    }
    @Override
    public T getValue(T valueToSearch) {
        T devolver = null;


        for (T temp : this){
            if (valueToSearch.equals(temp)){
                devolver = temp;
                break;
            }
        }
        return devolver;
    }
    @Override
    public boolean contains (T valor){          //Devuelve si el valor esta en la lista

        boolean esta = false;


        Node<T> temp = primero;

        while (temp != null){                           //Va recorriendo y comparando el valor con el de cada nodo. Si lo encuentra corta
            if (temp.getValor().equals(valor)){
                esta = true;
                break;
            }else {
                temp = temp.getSiguiente();
            }
        }

        return esta;
    }

    /**
     * OPERACIONES VARIAS DE LISTA
     */
    @Override
    public Iterator<T> iterator() {

        return new MyLinkedListIterator<T>(primero);
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public String toString() {
        return visualize(" ");
    }

    /**
     * OPERACIONES PARA OPEREAR CON DISTINTAS LISTAS
     */
    @Override
    public void interchange (T valor, int direccion) throws EmptyList {


        if (size == 0){
            throw new EmptyList();
        }
        else if (direccion == 1) {
            Node<T> anterior = null;
            Node<T> temp = primero;
            boolean existe = false;
            while (temp != null) {
                if (temp.getValor().equals(valor)){
                    existe = true;
                    break;
                }
                anterior = temp;
                temp = temp.getSiguiente();
            }
            if (existe && (temp != ultimo)) {
                if (anterior == null) {
                    Node<T> siguiente = temp.getSiguiente();
                    temp.setSiguiente(siguiente.getSiguiente());
                    siguiente.setSiguiente(temp);
                    primero = siguiente;
                } else {
                    Node<T> siguiente = temp.getSiguiente();
                    anterior.setSiguiente(siguiente);
                    temp.setSiguiente(siguiente.getSiguiente());
                    siguiente.setSiguiente(temp);
                }
                if (temp.getSiguiente() == null) {
                    ultimo = temp;
                }
            }
        } else if (direccion == -1){
            Node<T> anterior1 = null;
            Node<T> anterior2 = null;
            Node<T> temp = primero;
            boolean existe = false;
            while (temp != null) {
                if (temp.getValor().equals(valor)){
                    existe = true;
                    break;
                }
                anterior2 = anterior1;
                anterior1 = temp;
                temp = temp.getSiguiente();
            }
            if (existe && (temp!=primero)){
                if (anterior2 == null){
                    anterior1.setSiguiente(temp.getSiguiente());
                    temp.setSiguiente(anterior1);
                    primero = temp;
                } else{
                    anterior2.setSiguiente(temp);
                    anterior1.setSiguiente(temp.getSiguiente());
                    temp.setSiguiente(anterior1);
                }
                if (anterior1.getSiguiente() == null){
                    ultimo = anterior1;
                }
            }
        }
    }


    /**
     * AQUI SE ENCUENTRA EL CODIGO Y OPERACIONES DE LA IMPLEMENTACION DEL STACK
     */
    @Override
    public T pop() throws EmptyStack {
        T value = removeLast();
        if (value == null){
            throw new EmptyStack();
        }
        return value;
    }

    @Override
    public T top() throws EmptyStack {

        if (size == 0){
            throw new EmptyStack();
        } else {
            return ultimo.getValor();
        }
    }
    @Override
    public void push(T element) {

        addLast(element);

    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void makeEmpty() {

        Node<T> temp = this.primero;
        Node<T> siguiente =temp.getSiguiente();


        while(siguiente != null){
            temp.setSiguiente(null);
            temp = siguiente;
            siguiente = temp.getSiguiente();
        }

        primero = null;
        ultimo = null;
        size = 0;
    }

    /**
     * AQUI SE ENCUENTRA EL CODIGO Y OPERACIONES DE LA IMPLEMENTACION DE UNA QUEUE
     */
    @Override
    public void enqueue(T element) {
        addLast(element);
    }
    @Override
    public void enqueueWithPriority(T element) {
        addInOrder(element);
    }
    @Override
    public T dequeue() throws EmptyQueue {
        Node<T> primero = this.primero;

        if(size == 0){
            throw  new EmptyQueue();
        } else{
            try {
                remove(0);
            } catch (ListOutOfIndex listOutOfIndex) {
                listOutOfIndex.printStackTrace();
            }
        }

        return primero.getValor();
    }

    /**
     * OPERACIONES PRIVADAS DE LINKEDLIST
     */
    private void addInOrder(T valor){
        if (size == 0){
            addFirst(valor);
        } else {
            if ( valor instanceof Comparable) {
                if (((Comparable) primero.getValor()).compareTo(valor) < 0) {
                    addFirst(valor);
                } else {
                    Node<T> temp = primero.getSiguiente();
                    Node<T> anterior = primero;
                    boolean seAgrego = false;

                    while (temp != null) {

                        if (((Comparable) temp.getValor()).compareTo(valor) < 0) {
                            Node<T> nuevo = new Node<>(valor);
                            anterior.setSiguiente(nuevo);
                            nuevo.setSiguiente(temp);
                            seAgrego = true;
                            break;
                        } else {
                            anterior = temp;
                            temp = temp.getSiguiente();
                        }
                    }

                    if (seAgrego) {
                        size++;
                    } else {
                        addLast(valor);
                    }
                }
            }
            else{
                addLast(valor);
            }
        }
    }
    private  Node<T> devolverNodoPosicion(int posicion) throws ListOutOfIndex{     //Devuelve el nodo en una posicion especifica

        Node<T> temp = null;

        if (posicion >= size || posicion < 0){          //Ceckea que exista la pos
            throw  new ListOutOfIndex(size);
        }else if (posicion == (size-1)){                //Checkea si es el ultimo
            temp = ultimo;
        }  else{                                        //Recorre la cantidad de veces necesarias y devuelve el ultimo
            temp = primero;
            for (int i = 0; i < posicion ; i++){
                temp = temp.getSiguiente();
            }
        }

        return temp;
    }
    private void addLast(T valor){                         //Agrega al final



        if (primero == null){                   //Si la lista es vacia agrega al principio
            addFirst(valor);
        } else{
            Node<T> nodoNuevo = new Node<T>(valor);   //Si la lista no es vacia, solamente agrega seteando el siguiente del
            ultimo.setSiguiente(nodoNuevo);     //ultimo y renueva el ultimo
            ultimo = nodoNuevo;
            size++;
        }
    }
    private String visualize(String separacion){

        Node<T> temp = primero;
        String str = "";

        while(temp != null){
            str = str + (temp.getValor() + separacion);
            temp = temp.getSiguiente();
        }
        str = str + "\n";
        return str;
    }

}
