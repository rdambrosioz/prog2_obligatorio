import entities.*;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.MyHash;
import uy.edu.um.prog2.adt.hash.MyOpenedHashImpl;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyHeapImpl;
import uy.edu.um.prog2.adt.list.MyArrayListImpl;
import uy.edu.um.prog2.adt.list.MyList;

import java.lang.invoke.LambdaConversionException;
import java.util.HashMap;

public class Queries {


    private MyList<Book> booksList;
    private MyHash<Language, Language> languagesHash;
    private MyHash<AuthorNameHashKey, Author> authorsHash;
    private MyHash<UserNameHashKey, User> usersHash;
    private MyHash<Rating, Rating> ratingsHash;



    public Queries() {
        this.booksList = new MyArrayListImpl<>(10001);
        this.languagesHash = new MyClosedHashImpl<>(50,false);
        this.authorsHash = new MyClosedHashImpl<>(15000, false);
        this.usersHash = new MyClosedHashImpl<>(60000,false,0.9f);
        this.ratingsHash = new MyClosedHashImpl<>(55000, true,0.9f);
    }

    public void loadData(){
        FileLoader.loadData(this.booksList, this.languagesHash, this.authorsHash, this.usersHash, this.ratingsHash);
        MyList<Language> l = this.languagesHash.getValues();
        for (Language lan : l){
            System.out.println(lan.getLanguageCode() + "  " + lan.booksAmount());
        }
    }

    public MyList<Book> getBooksList() {
        return booksList;
    }

    public MyHash<AuthorNameHashKey, Author> getAuthorsHash() {
        return authorsHash;
    }

    public MyHash<Language, Language> getLanguagesHash() {
        return languagesHash;
    }

    public MyHash<UserNameHashKey, User> getUsersHash() {
        return usersHash;
    }

    public MyHash<Rating, Rating> getRatingsHash() {
        return ratingsHash;
    }




    public void topRaters(){
        MyList<User> usersList = this.usersHash.getValues();

        //MyHeap<User> usersHeap = new MyHeapImpl<>(usersList.getSize());

        MyHeap<User> usersHeap = new MyHeapImpl<>(usersList.getSize());

        System.out.println(usersList.getSize());


        for (User user : usersList){
            usersHeap.insert(user);
        }

        System.out.println(usersHeap.size());

        for (int i = 0; i<10; i++){
            System.out.println(usersHeap.deleteMax());
        }

        System.out.println(usersHeap.size());
    }
}
