package uy.edu.um.prog2.adt.stack;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.exeptions.EmptyStack;
import uy.edu.um.prog2.adt.list.MyArrayStackImpl;
import uy.edu.um.prog2.adt.list.MyStack;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MyArrayStackImplTest {

    @Test
    void push() throws EmptyStack {
        MyStack<Integer> staArray=new MyArrayStackImpl<Integer>(6);
        staArray.push(3);
        staArray.push(1);
        staArray.push(6);

        assertEquals((Integer)6, staArray.top());
    }

    @Test
    void pop() throws EmptyStack {
        MyStack<Integer> staArray=new MyArrayStackImpl<>(6);
        staArray.push(3);
        staArray.push(1);
        staArray.push(6);
        assertEquals((Integer)6, staArray.pop());
    }

    @Test
    void top() throws EmptyStack {
        MyStack<Integer> staArray=new MyArrayStackImpl<>(6);
        staArray.push(3);
        staArray.push(1);
        staArray.push(6);
        assertEquals((Integer)6, staArray.top());
    }
}