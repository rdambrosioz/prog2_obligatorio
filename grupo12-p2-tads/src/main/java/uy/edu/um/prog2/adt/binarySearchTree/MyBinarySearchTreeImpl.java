package uy.edu.um.prog2.adt.binarySearchTree;

import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTree;
import uy.edu.um.prog2.adt.binarySearchTree.TreeNode;
import uy.edu.um.prog2.adt.exeptions.EmptyQueue;
import uy.edu.um.prog2.adt.exeptions.ListOutOfIndex;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.MyQueue;

import java.util.Iterator;

public class MyBinarySearchTreeImpl<K extends Comparable<K>, T>
        implements MyBinarySearchTree<K,T> {

    private TreeNode<K, T> root;

    public MyBinarySearchTreeImpl() {
        this.root = null;
    }


    @Override
    public void insert(K key, T value) {

        this.root = insert(key, value, this.root);

    }

    @Override
    public void delete (K key){
        this.root = delete(key, this.root);
    }

    @Override
    public boolean contains(K key) {
        return contains(key, this.root);
    }

    @Override
    public int size(){
        return size(this.root);
    }

    @Override
    public int countLeaf() {
        return countLeaf(this.root);
    }

    @Override
    public int countCompleteElement() {
        return countCompleteElement(this.root);
    }

    @Override
    public MyList<T> inOrder() {
        MyList<T> listaInOrder = new MyLinkedListImpl<>();
        return inOrder(this.root, listaInOrder);
    }

    @Override
    public MyList<T> preOrder() {
        MyList<T> listaPreOrder = new MyLinkedListImpl<>();
        return preOrder(this.root, listaPreOrder);
    }

    @Override
    public MyList<T> postOrder() {
        MyList<T> listaPostOrder = new MyLinkedListImpl<>();
        return postOrder(this.root, listaPostOrder);
    }

    @Override
    public MyList<T> nivel() {
        MyList<T> listaNivel = new MyLinkedListImpl<>();
        MyQueue<TreeNode<K,T>> listaSubTree = new MyLinkedListImpl<>();
        listaSubTree.enqueue(this.root);
        while(! (listaSubTree.isEmpty())){
            TreeNode<K,T> temp = null;
            try {
                temp = listaSubTree.dequeue();
            } catch (EmptyQueue error) {
                error.printStackTrace();
            }
            listaNivel.add(temp.getValue());
            if(temp.getLeft() != null){
                listaSubTree.enqueue(temp.getLeft());
            }
            if(temp.getRight() != null){
                listaSubTree.enqueue(temp.getRight());
            }
        }

        return listaNivel;
    }

    @Override
    public String toString() {
        String str = "";
        MyList<T> lista = this.nivel();

        try {
            if (lista.get(0) instanceof Comparable){
                str = str + "------------------------------------------------" + "\n";
                while(lista.getSize()>0){
                    while (lista.getSize()>1 && ((Comparable) lista.get(0)).compareTo(lista.get(1)) < 0  ){
                        str = str + lista.remove(0).toString() + " ";
                    }
                    if (lista.getSize() > 0) {
                        str = str + lista.remove(0).toString() + " ";
                        str = str + "\n";
                    }
                }
                str = str + "------------------------------------------------";
            }
        } catch (ListOutOfIndex listOutOfIndex) {
            listOutOfIndex.printStackTrace();
        }


        return str;

    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }






    private TreeNode<K, T> insert(K key, T value, TreeNode<K, T> subTree){


        if (subTree == null){
            TreeNode<K, T> newNode = new TreeNode(key, value);
            subTree = newNode;

        } else {
            if (key.compareTo(subTree.getKey()) > 0){
                subTree.setRight(insert(key, value, subTree.getRight()));
            } else if (key.compareTo(subTree.getKey()) < 0){
                subTree.setLeft(insert(key, value, subTree.getLeft()));
            } else{
                // El elementro ya esta insertado, no se hace nada
            }
        }
        return subTree;
    }

    private TreeNode<K,T> delete (K elemento, TreeNode<K,T> subtree){
        TreeNode<K,T> subtreeToReturn = subtree;

        if (elemento.compareTo(subtree.getKey()) == 0){
            if (subtree.getLeft() == null && subtree.getRight() == null){
                subtreeToReturn = null;
            }else if(subtree.getRight() == null){
                subtreeToReturn = subtree.getLeft();
            } else if(subtree.getLeft() == null){
                subtreeToReturn = subtree.getRight();
            }else {
                TreeNode<K,T> min = findMax(subtree.getLeft());
                subtree.setKey(min.getKey());
                subtree.setValue(min.getValue());
                subtree.setLeft(delete(min.getKey(), subtree.getLeft()));


            }
        } else if (elemento.compareTo(subtree.getKey()) > 0){
            subtree.setRight(delete(elemento, subtree.getRight()));


        } else{
            subtree.setLeft(delete(elemento, subtree.getLeft()));


        }

        return subtreeToReturn;

    }

    private boolean contains(K key, TreeNode<K, T> subTree){

        boolean result = false;

        if (subTree != null){
            if(key.compareTo(subTree.getKey()) == 0){
                result = true;
            } else if (key.compareTo(subTree.getKey()) > 0){
                result = contains(key, subTree.getRight());
            } else {
                result = contains(key, subTree.getLeft());
            }
        }

        return result;
    }

    private int size(TreeNode<K,T> subtree) {
        int size = 0;

        if (subtree != null){
            size++;
            if (subtree.getLeft() != null){
                size = size + size(subtree.getLeft());
            }
            if(subtree.getRight() != null){
                size = size + size(subtree.getRight());
            }
        }
        return size;
    }

    private int countLeaf(TreeNode<K, T> subtree){
        int leafs = 0;

        if (subtree != null){
            if (subtree.getRight() != null || subtree.getLeft() != null){
                if (subtree.getRight() != null){
                    leafs = leafs + countLeaf(subtree.getRight());
                }
                if (subtree.getLeft() != null){
                    leafs = leafs + countLeaf(subtree.getLeft());
                }
            } else{
                leafs++;
            }
        }
        return leafs;
    }

    private int countCompleteElement(TreeNode<K,T> subtree){

        int completeElement = 0;

        if(subtree != null){
            if (subtree.getLeft() != null ){
                completeElement = completeElement + countCompleteElement(subtree.getLeft());
            }
            if (subtree.getRight() != null){
                completeElement = completeElement + countCompleteElement(subtree.getRight());
            }
            if (subtree.getLeft() != null && subtree.getRight() != null){
                completeElement++;
            }
        }
        return completeElement;
    }

    private MyList<T> postOrder(TreeNode<K, T> subtree, MyList<T> listaPostOrder){

        if (subtree != null){
            if (subtree.getLeft() != null){
                postOrder(subtree.getLeft(), listaPostOrder);
            }
            if (subtree.getRight() != null){
                postOrder(subtree.getRight(), listaPostOrder);
            }
            listaPostOrder.add(subtree.getValue());
        }
        return listaPostOrder;

    }

    private MyList<T> preOrder(TreeNode<K, T> subtree, MyList<T> listaPreOrder){

        if (subtree != null){
            listaPreOrder.add(subtree.getValue());
            if (subtree.getLeft() != null){
                preOrder(subtree.getLeft(), listaPreOrder);
            }
            if (subtree.getRight() != null){
                preOrder(subtree.getRight(), listaPreOrder);
            }
        }
        return listaPreOrder;
    }

    private MyList<T> inOrder(TreeNode<K,T> subtree, MyList<T> listaInOrder){


        if (subtree != null){
            if (subtree.getLeft() != null){
                inOrder(subtree.getLeft(), listaInOrder);
            }
            listaInOrder.add(subtree.getValue());
            if (subtree.getRight() != null){
                inOrder(subtree.getRight(), listaInOrder);
            }
        }
        return listaInOrder;
    }

    private TreeNode<K,T> findMax (TreeNode<K,T> subtree){
        TreeNode<K,T> treeToReturn = null;

        if (subtree != null){
            if(subtree.getRight() == null){
                treeToReturn = subtree;
            } else{
                treeToReturn = findMax(subtree.getRight());
            }
        }

        return treeToReturn;

    }



}

