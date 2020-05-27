package uy.edu.um.prog2.adt.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.exeptions.EmptyList;
import uy.edu.um.prog2.adt.exeptions.ListOutOfIndex;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    private MyList<Integer> myList;

    @BeforeEach
    void setUp() {
        myList = new MyLinkedListImpl<>();
    }


    @Test
    void testAdd() {
        myList.add(10);
        myList.add(12);

        try {
            myList.add(11,1);
        } catch (ListOutOfIndex listOutOfIndex) {
            fail();
            listOutOfIndex.printStackTrace();
        }

        myList.addFirst(44);

        assertThrows(ListOutOfIndex.class, () ->{
            myList.add(22,15);
        });

    }


    @Test
    void testRemove() {
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        try {
            assertEquals(12,myList.remove(1));
        } catch (ListOutOfIndex listOutOfIndex) {
            fail();
            listOutOfIndex.printStackTrace();
        }

        assertThrows(ListOutOfIndex.class, () ->{
            myList.remove(11);
        });

        assertEquals(26,myList.removeLast());

        assertEquals(17,myList.removeValue(17));

        assertEquals(10,myList.removeLast());

        assertNull(myList.removeLast());

    }

    @Test
    void testGet() {
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        try {
            assertEquals(26, myList.get(3));
            assertEquals(26, myList.removeLast());
            assertEquals(12, myList.get(1));
        } catch (ListOutOfIndex listOutOfIndex) {
            fail();
            listOutOfIndex.printStackTrace();
        }

        assertEquals(17, myList.getValue(17));

        assertThrows(ListOutOfIndex.class, () ->{
            myList.get(220);
        });

    }

    @Test
    void testGetSize() {
        assertEquals(0, myList.getSize());
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        assertEquals(4, myList.getSize());

        try {
            assertEquals(12,myList.remove(1));
            assertEquals(26, myList.removeLast());
            assertEquals(17,myList.removeValue(17));
        } catch (ListOutOfIndex listOutOfIndex) {
            fail();
            listOutOfIndex.printStackTrace();
        }

        assertEquals(1, myList.getSize());


    }


    @Test
    void testToString() {
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        System.out.println(myList);

    }

    @Test
    void testContains() {
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        assertTrue(myList.contains(12));
        assertFalse(myList.contains(431));
    }

    @Test
    void testInterchange() {
        assertThrows(EmptyList.class, () ->{
            myList.interchange(10,1);
        });

        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        try {
            myList.interchange(17,1);
            myList.interchange(12,-1);
            assertEquals(17,myList.get(3));
            assertEquals(12,myList.get(0));
        } catch (EmptyList emptyList) {
            fail();
            emptyList.printStackTrace();
        } catch (ListOutOfIndex listOutOfIndex) {
            fail();
            listOutOfIndex.printStackTrace();
        }


    }

    @Test
    void testIterator() {
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);


        Iterator<Integer> myListIterator = myList.iterator();

        while(myListIterator.hasNext()){
            Integer value = myListIterator.next();
            System.out.println(value);
        }


    }

    @Test
    void testForEach(){
        myList.add(10);
        myList.add(12);
        myList.add(17);
        myList.add(26);

        for (Integer temp : myList){
            System.out.println(temp);
        }


    }
}