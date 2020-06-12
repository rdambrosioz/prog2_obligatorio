import com.sun.org.apache.bcel.internal.generic.LADD;
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




    public MyList<User> topRaters(){

        MyList<User> usersList = this.usersHash.getValues();
        MyHeap<User> usersHeap = new MyHeapImpl<>(usersList.getSize());
        MyList<User> top10 = new MyArrayListImpl<>(10);


        for (User user : usersList){
            usersHeap.insert(user);
        }



        for (int i = 0; i<10; i++){
            top10.add(usersHeap.deleteMax());
        }

        //FIXME Falta sorting para oderdenar los 10

        return top10;


    }



    public MyList<Book> topReserved(){

        MyList<Book> top10 = new MyArrayListImpl<>(10);
        MyHeap<BookBookingsHeapNode> bookHeap = new MyHeapImpl<>(booksList.getSize());

        for (Book book : booksList){
            bookHeap.insert(new BookBookingsHeapNode(book));
        }

        for(int i=0; i<10; i++){
            top10.add(bookHeap.deleteMax().getBook());
        }

        return top10;
    }

    public MyList<Language> top5WithMoreReserves(){

        MyList<Language> top5 = new MyArrayListImpl<>(5);
        MyHeap<Language> languageHeap = new MyHeapImpl<>(languagesHash.size());

        for (Language language : languagesHash.getValues()){

            languageHeap.insert(language);

        }

        for (int i = 0; i<5; i++){

            top5.add(languageHeap.deleteMax());

        }


        return top5;
    }
}
