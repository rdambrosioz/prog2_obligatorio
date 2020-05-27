package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyClosedLinearHashTest {



    private MyHash<ClienteNameHashKey, Cliente> myHash;

    @BeforeEach
    void setUp(){
        myHash = new MyClosedHashImpl<>(2, true);
    }

    @Test
    void testPut() {
        Cliente cl1 = new Cliente(50047630l, 1866546l, "Juan");
        Cliente cl2 = new Cliente(50047631l, 1866547l, "Pedro");
        Cliente cl3 = new Cliente(50047632l, 1866549l, "Jose");
        Cliente cl4 = new Cliente(50047633l, 1866550l, "Anuj");

        myHash.put(new ClienteNameHashKey(cl1.getName()), cl1);
        myHash.put(new ClienteNameHashKey(cl2.getName()), cl2);
        myHash.put(new ClienteNameHashKey(cl3.getName()), cl3);
        myHash.put(new ClienteNameHashKey(cl4.getName()), cl4);


    }

    @Test
    void testRemove() {
        Cliente cl1 = new Cliente(50047630l, 1866546l, "Juan");
        Cliente cl2 = new Cliente(50047631l, 1866547l, "Pedro");
        Cliente cl3 = new Cliente(50047632l, 1866549l, "Jose");
        Cliente cl4 = new Cliente(50047633l, 1866550l, "Anuj");
        Cliente cl5 = new Cliente(50047634l, 1866551l, "Ajnu");

        myHash.put(new ClienteNameHashKey(cl1.getName()), cl1);
        myHash.put(new ClienteNameHashKey(cl2.getName()), cl2);
        myHash.put(new ClienteNameHashKey(cl3.getName()), cl3);
        myHash.put(new ClienteNameHashKey(cl4.getName()), cl4);


        assertEquals(cl2, myHash.remove(new ClienteNameHashKey(cl2.getName())));
        assertNull(myHash.remove(new ClienteNameHashKey(cl2.getName())));

        assertEquals(cl4, myHash.remove(new ClienteNameHashKey(cl4.getName())));
        assertNull(myHash.remove(new ClienteNameHashKey(cl4.getName())));
        myHash.put(new ClienteNameHashKey(cl5.getName()), cl5);


    }

    @Test
    void testGet() {
        Cliente cl1 = new Cliente(50047630l, 1866546l, "Juan");
        Cliente cl2 = new Cliente(50047631l, 1866547l, "Pedro");
        Cliente cl3 = new Cliente(50047632l, 1866549l, "Jose");
        Cliente cl4 = new Cliente(50047633l, 1866550l, "Anuj");
        Cliente cl5 = new Cliente(50047634l, 1866551l, "Ajnu");

        myHash.put(new ClienteNameHashKey(cl1.getName()), cl1);
        myHash.put(new ClienteNameHashKey(cl2.getName()), cl2);
        myHash.put(new ClienteNameHashKey(cl3.getName()), cl3);
        myHash.put(new ClienteNameHashKey(cl4.getName()), cl4);
        myHash.put(new ClienteNameHashKey(cl5.getName()), cl5);


        assertEquals(cl2, myHash.get(new ClienteNameHashKey(cl2.getName())));
        assertNull(myHash.remove(new ClienteNameHashKey("Unaj")));

        assertEquals(cl4, myHash.get(new ClienteNameHashKey(cl4.getName())));
        assertEquals(cl4,myHash.remove(new ClienteNameHashKey(cl4.getName())));


        assertEquals(cl5, myHash.get(new ClienteNameHashKey(cl5.getName())));

        assertNull(myHash.get(new ClienteNameHashKey(cl4.getName())));
    }

    @Test
    void testSize() {
        Cliente cl1 = new Cliente(50047630l, 1866546l, "Juan");
        Cliente cl2 = new Cliente(50047631l, 1866547l, "Pedro");
        Cliente cl3 = new Cliente(50047632l, 1866549l, "Jose");
        Cliente cl4 = new Cliente(50047633l, 1866550l, "Anuj");
        Cliente cl5 = new Cliente(50047634l, 1866551l, "Ajnu");

        myHash.put(new ClienteNameHashKey(cl1.getName()), cl1);
        myHash.put(new ClienteNameHashKey(cl2.getName()), cl2);
        myHash.put(new ClienteNameHashKey(cl3.getName()), cl3);
        myHash.put(new ClienteNameHashKey(cl4.getName()), cl4);

        assertEquals(4,myHash.size());

        assertEquals(cl4,myHash.remove(new ClienteNameHashKey(cl4.getName())));

        assertEquals(3,myHash.size());


        myHash.put(new ClienteNameHashKey(cl5.getName()), cl5);

        assertEquals(4,myHash.size());

    }

}