package uy.edu.um.prog2.adt.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.exeptions.EmptyQueue;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.list.MyQueue;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private MyQueue<Integer> myQueue;

    @BeforeEach
    void setUp(){
        myQueue = new MyLinkedListImpl<>();
    }

    @Test
    void testEnqueue() {
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);
    }

    @Test
    void testDequeue() {
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);

        try {
            assertEquals(10, myQueue.dequeue());
            assertEquals(20, myQueue.dequeue());
            assertEquals(13, myQueue.dequeue());
            assertEquals(7, myQueue.dequeue());
        } catch (EmptyQueue emptyQueue) {
            fail();
            emptyQueue.printStackTrace();
        }

        assertThrows(EmptyQueue.class, () ->{
            myQueue.dequeue();
        });
    }

    @Test
    void testEnqueueWithPriority() {
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);
        myQueue.enqueueWithPriority(100);
        myQueue.enqueueWithPriority(200);
        myQueue.enqueueWithPriority(2);
        myQueue.enqueue(44);

        try {
            assertEquals(200, myQueue.dequeue());
            assertEquals(100, myQueue.dequeue());
            assertEquals(10, myQueue.dequeue());
        } catch (EmptyQueue emptyQueue) {
            fail();
            emptyQueue.printStackTrace();
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(myQueue.isEmpty());

        myQueue.enqueue(10);
        myQueue.enqueue(20);

        assertFalse(myQueue.isEmpty());

        try {
            assertEquals(10, myQueue.dequeue());
            assertEquals(20, myQueue.dequeue());

        } catch (EmptyQueue emptyQueue) {
            fail();
            emptyQueue.printStackTrace();
        }

        assertTrue(myQueue.isEmpty());


    }


    @Test
    void testGetSize() {
        assertEquals(0, myQueue.getSize());

        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);

        assertEquals(4, myQueue.getSize());

        try {
            assertEquals(10, myQueue.dequeue());
            assertEquals(20, myQueue.dequeue());
            assertEquals(2, myQueue.getSize());
            assertEquals(13, myQueue.dequeue());
            assertEquals(7, myQueue.dequeue());
        } catch (EmptyQueue emptyQueue) {
            fail();
            emptyQueue.printStackTrace();
        }

        assertEquals(0, myQueue.getSize());
    }

    @Test
    void testVisualize() {
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);


        System.out.println(myQueue);
    }

    @Test
    void testIterator() {

        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);

        Iterator<Integer> myQueueIterator = myQueue.iterator();

        while(myQueueIterator.hasNext()){
            Integer value = myQueueIterator.next();
            System.out.println(value);
        }


    }

    @Test
    void testForEach(){
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(13);
        myQueue.enqueue(7);

        for (Integer temp : myQueue){
            System.out.println(temp);
        }


    }
}