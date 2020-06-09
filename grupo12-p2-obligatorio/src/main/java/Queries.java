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
    private MyHash<Rating, Rating> ratingsHash;



    public Queries() {
        this.booksList = new MyArrayListImpl<>(10001);
        this.authorsHash = new MyClosedHashImpl<>(15000, false);
        this.usersHash = new MyClosedHashImpl<>(60000,false,0.9f);
        this.ratingsHash = new MyClosedHashImpl<>(55000, true,0.9f);
    }

    public void loadData(){
        FileLoader.loadData(this.booksList, this.authorsHash, this.usersHash, this.ratingsHash);
    }

    public MyList<Book> getBooksList() {
        return booksList;
    }

    public MyHash<AuthorNameHashKey, Author> getAuthorsHash() {
        return authorsHash;
    }

    public MyHash<UserNameHashKey, User> getUsersHash() {
        return usersHash;
    }

    public MyHash<Rating, Rating> getRatingsHash() {
        return ratingsHash;
    }
}
