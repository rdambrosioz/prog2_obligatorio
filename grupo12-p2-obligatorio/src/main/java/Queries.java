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


    private MyHash<Book,Book> booksHash;
    private MyHash<Language, Language> languagesHash;
    private MyHash<Author, Author> authorsHash;
    private MyHash<AuthorPublications, AuthorPublications> authorsPublicationsHash;
    private MyHash<User, User> usersHash;




    public Queries() {
        this.booksHash = new MyClosedHashImpl<>(12000, false, 0.9f);
        this.languagesHash = new MyClosedHashImpl<>(50,false);
        this.authorsHash = new MyClosedHashImpl<>(12000, false);
        this.authorsPublicationsHash = new MyClosedHashImpl<>(25000, false);
        this.usersHash = new MyClosedHashImpl<>(60000,false,0.9f);

    }

    public void loadData(){
        FileLoader.loadData(this.booksHash, this.languagesHash, this.authorsHash, this.authorsPublicationsHash, this.usersHash);
        
    }

    public MyHash<Book, Book> getBooksList() {
        return booksHash;
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
    public MyList<BookBookingsHeapNode> topReserved(){

        MyList<Book> books = booksHash.getValues();
        if(books.getSize() < 10){
            System.out.println("No hay información suficiente para responder la consulta");
            return null;
        }

        MyList<BookBookingsHeapNode> top10 = new MyArrayListImpl<>(10);


        MyHeap<BookBookingsHeapNode> bookHeap = new MyHeapImpl<>(books.getSize());

        for (Book book : books){
            bookHeap.insert(new BookBookingsHeapNode(book));
        }

        for(int i=0; i<10; i++){
            top10.add(bookHeap.deleteMax());
        }

        return top10;

    }

    //CONSULTA 2
    public MyList<BookRatedHeapNode> top20WithMoreEvaluations(){

        MyList<Book> books = booksHash.getValues();
        if(books.getSize() < 20){
            System.out.println("No hay información suficiente para responder la consulta");
            return null;
        }

        MyList<BookRatedHeapNode> booksMostEvaluated = new MyArrayListImpl<>(20);


        MyHeap<BookRatedHeapNode> booksHeapRated = new MyHeapImpl<>(books.getSize());

        for (Book book : books){

            booksHeapRated.insert(new BookRatedHeapNode(book));
        }
        for (int i = 0; i<20; i++){

            booksMostEvaluated.add(booksHeapRated.deleteMax());
        }

        return booksMostEvaluated;
    }


    //CONSULTA 3
    public MyList<UserAvgRatingNode> topRaters(){

        MyList<User> usersList = this.usersHash.getValues();
        if(usersList.getSize() < 10){
            System.out.println("No hay información suficiente para responder la consulta");
            return null;
        }

        MyHeap<User> usersHeap = new MyHeapImpl<>(usersList.getSize());
        UserAvgRatingNode[] top10array = new UserAvgRatingNode[10];
        MyList<UserAvgRatingNode> top10 = new MyArrayListImpl<>(10);

        for (User user : usersList){
            usersHeap.insert(user);
        }
        for (int i = 0; i<10; i++){
            top10array[i] = new UserAvgRatingNode(usersHeap.deleteMax());
        }
        Sorting.heapSort(top10array);

        for (int i = 0; i<10; i++){
            top10.add(top10array[i]);
        }

        return top10;
        

    }


    //CONSULTA 4
    public MyList<Language> top5WithMoreReserves(){

        MyList<Language> languagesList = languagesHash.getValues();
        if(languagesList.getSize() < 5){
            System.out.println("No hay información suficiente para responder la consulta");
            return null;
        }

        MyList<Language> top5 = new MyArrayListImpl<>(5);
        MyHeap<Language> languageHeap = new MyHeapImpl<>(languagesHash.size());


        for (Language language : languagesList){

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
        if(authorsList.getSize() < 20){
            System.out.println("No hay información suficiente para responder la consulta");
            return null;
        }

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
