import entities.*;
import entities.nodes.BookBookingsHeapNode;
import entities.nodes.BookRatedHeapNode;
import entities.nodes.UserAvgRatingNode;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.MyHash;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyHeapImpl;
import uy.edu.um.prog2.adt.list.MyArrayListImpl;
import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.sorting.Sorting;

public class Queries {


    private MyList<Book> booksList;
    private MyHash<Language, Language> languagesHash;
    private MyHash<Author, Author> authorsHash;
    private MyHash<AuthorPublications, AuthorPublications> authorsPublicationsHash;
    private MyHash<User, User> usersHash;




    public Queries() {
        this.booksList = new MyArrayListImpl<>(10001);
        this.languagesHash = new MyClosedHashImpl<>(50,false);
        this.authorsHash = new MyClosedHashImpl<>(15000, false);
        this.authorsPublicationsHash = new MyClosedHashImpl<>(26000, false);
        this.usersHash = new MyClosedHashImpl<>(60000,false,0.9f);

    }

    public void loadData(){
        FileLoader.loadData(this.booksList, this.languagesHash, this.authorsHash, this.authorsPublicationsHash, this.usersHash);
        
    }

    public MyList<Book> getBooksList() {
        return booksList;
    }

    public MyHash<Author, Author> getAuthorsHash() {
        return authorsHash;
    }

    public MyHash<Language, Language> getLanguagesHash() {
        return languagesHash;
    }

    public MyHash<User, User> getUsersHash() {
        return usersHash;
    }


    public MyHash<AuthorPublications, AuthorPublications> getAuthorsPublicationsHash() {
        return authorsPublicationsHash;
    }

    //CONSULTA 1
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

    //CONSULTA 2

    public MyList<BookRatedHeapNode> top20WithMoreEvaluations(){

        MyList<BookRatedHeapNode> booksMostEvaluated = new MyArrayListImpl<>(10);
        MyHeap<BookRatedHeapNode> booksHeapRated = new MyHeapImpl<>(booksList.getSize());

        for (Book book : booksList){

            booksHeapRated.insert(new BookRatedHeapNode(book));
        }
        for (int i = 0; i<20; i++){

            booksMostEvaluated.add(booksHeapRated.deleteMax());
        }

        return booksMostEvaluated;
    }


    //CONSULTA 3
    public MyList<User> topRaters(){

        MyList<User> usersList = this.usersHash.getValues();
        MyHeap<User> usersHeap = new MyHeapImpl<>(usersList.getSize());
        UserAvgRatingNode[] top10array = new UserAvgRatingNode[10];
        MyList<User> top10 = new MyArrayListImpl<>(10);

        for (User user : usersList){
            usersHeap.insert(user);
        }
        for (int i = 0; i<10; i++){
            top10array[i] = new UserAvgRatingNode(usersHeap.deleteMax());
        }
        Sorting.bubbleSort(top10array);

        for (int i = 0; i<10; i++){
            top10.add(top10array[i].getUser());
        }

        return top10;


    }


    //CONSULTA 4
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



    //CONSULTA 5
    public MyList<AuthorPublications> top20Author(){
        MyList<AuthorPublications> authorsList = authorsPublicationsHash.getValues();
        MyHeap<AuthorPublications> authorsHeap = new MyHeapImpl<>(authorsList.getSize());
        MyList<AuthorPublications> top20 = new MyArrayListImpl<>(20);

        for (AuthorPublications author : authorsList){
            authorsHeap.insert(author);
        }

        for (int i=0; i<20; i++){
            top20.add(authorsHeap.deleteMax());
        }

        return top20;


    }
}
