package uy.edu.um.prog2.adt.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.exeptions.HeapOverflow;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapImplTest {

    private MyHeap myHeap;

    @BeforeEach
    void setUp(){
        myHeap = new MyHeapImpl<>(8);
    }

    @Test
    void testInsert() {
        myHeap.insert(45);
        myHeap.insert(15);
        myHeap.insert(33);
        myHeap.insert(38);
        myHeap.insert(8);
        myHeap.insert(11);
        myHeap.insert(19);

        myHeap.insert(7);
        assertThrows(HeapOverflow.class, ()->{
            myHeap.insert(22);
        });
    }

    @Test
    void testSize(){

        myHeap.insert(45);
        myHeap.insert(15);
        myHeap.insert(33);
        myHeap.insert(38);
        myHeap.insert(8);
        myHeap.insert(11);
        myHeap.insert(19);

        assertEquals(7, myHeap.size());
    }

    @Test
    void testDeleteMax(){
        myHeap.insert(45);
        myHeap.insert(15);
        myHeap.insert(33);
        myHeap.insert(38);
        myHeap.insert(8);
        myHeap.insert(11);
        myHeap.insert(19);
        myHeap.insert(50);
        System.out.println(myHeap);

        assertEquals(50, myHeap.deleteMax());
        assertEquals(45, myHeap.getMax());
        assertEquals(7, myHeap.size());
        System.out.println(myHeap);

    }

    @Test
    void testGetMax(){
        myHeap.insert(45);
        myHeap.insert(15);
        myHeap.insert(33);
        myHeap.insert(38);
        myHeap.insert(8);
        myHeap.insert(11);
        myHeap.insert(19);
        myHeap.insert(50);

        assertEquals(50, myHeap.getMax());
        assertEquals(50, myHeap.deleteMax());

        assertEquals(45, myHeap.getMax());

    }

    @Test
    void testToString() {
        myHeap.insert(45);
        myHeap.insert(15);
        myHeap.insert(33);
        myHeap.insert(38);
        myHeap.insert(8);
        myHeap.insert(11);
        myHeap.insert(19);
        myHeap.insert(50);


        assertEquals(50, myHeap.getMax());
        assertEquals(8, myHeap.size());
        System.out.println(myHeap);
    }
}