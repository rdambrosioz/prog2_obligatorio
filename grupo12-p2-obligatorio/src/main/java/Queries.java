import entities.*;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.MyHash;
import uy.edu.um.prog2.adt.hash.MyOpenedHashImpl;
import uy.edu.um.prog2.adt.list.MyArrayListImpl;
import uy.edu.um.prog2.adt.list.MyList;

import java.util.HashMap;

public class Queries {


    private MyList<Book> booksList;
    private MyHash<AuthorNameHashKey, Author> authorsHash;
    private MyHash<UserNameHashKey, User> usersHash;



    public Queries() {
        this.booksList = new MyArrayListImpl<>(10001);
        this.authorsHash = new MyClosedHashImpl<>(12000, false);
        this.usersHash = new MyClosedHashImpl<>(60000,false);
    }

    public MyList<Book> getBooksList() {
        return booksList;
    }

    public MyHash<AuthorNameHashKey, Author> getAuthorsHash() {
        return authorsHash;
    }

    public void loadData(){
        FileLoader.loadData(this.booksList, this.authorsHash);
    }

}
