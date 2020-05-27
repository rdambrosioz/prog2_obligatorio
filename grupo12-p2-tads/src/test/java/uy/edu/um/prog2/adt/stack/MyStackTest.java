package uy.edu.um.prog2.adt.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.exeptions.EmptyStack;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.list.MyStack;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private MyStack<Integer> myStack;

    @BeforeEach
    void setUp(){
        myStack = new MyLinkedListImpl<>();
    }


    @Test
    void testPush(){
        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);

    }

    @Test
    void testTop(){

        assertThrows(EmptyStack.class, () ->{
            myStack.top();
        });

        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);

        try {
            assertEquals(37,myStack.top());
        } catch (EmptyStack emptyStack) {
            fail();
            emptyStack.printStackTrace();
        }

    }

    @Test
    void testPop(){
        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);
        try {
            assertEquals(37,myStack.pop());
            myStack.pop();
            myStack.pop();
            myStack.pop();
        } catch (EmptyStack emptyStack) {
            fail();
            emptyStack.printStackTrace();
        }

        assertThrows(EmptyStack.class, () ->{
            myStack.pop();
        });
    }

    @Test
    void testEmpty(){
        assertTrue(myStack.isEmpty());
        myStack.push(5);
        myStack.push(4);
        assertFalse(myStack.isEmpty());

        myStack.makeEmpty();

        assertTrue(myStack.isEmpty());

    }

    @Test
    void testGetSze(){
        assertEquals(0,myStack.getSize());

        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);

        assertEquals(4,myStack.getSize());

        try {
            assertEquals(37,myStack.pop());
            myStack.pop();
            myStack.pop();
            myStack.pop();
        } catch (EmptyStack emptyStack) {
            fail();
            emptyStack.printStackTrace();
        }

        assertEquals(0,myStack.getSize());

    }

    @Test
    void testToString(){
        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);
        System.out.println(myStack);
    }

    @Test
    void testIterator() {

        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);

        Iterator<Integer> myStackIterator = myStack.iterator();

        while(myStackIterator.hasNext()){
            Integer value = myStackIterator.next();
            System.out.println(value);
        }


    }

    @Test
    void testForEach(){
        myStack.push(5);
        myStack.push(4);
        myStack.push(9);
        myStack.push(37);

        for (Integer temp : myStack){
            System.out.println(temp);
        }


    }
}