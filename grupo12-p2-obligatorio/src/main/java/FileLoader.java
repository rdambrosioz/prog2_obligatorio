import entities.*;
import uy.edu.um.prog2.adt.hash.MyHash;
import uy.edu.um.prog2.adt.list.MyArrayListImpl;
import uy.edu.um.prog2.adt.list.MyList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;


public class FileLoader {




    public static void loadData(MyHash<Book, Book> booksHash, MyHash<Language,Language> languages, MyHash<Author, Author> authorsHash, MyHash<AuthorPublications, AuthorPublications> authorsPublicationsHash, MyHash<User,User> usersHash){

        loadBooksCSV(booksHash, languages, authorsHash, authorsPublicationsHash);


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                loadUsersCSV(booksHash, usersHash);
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                loadRatingsCSV(booksHash,usersHash);
            }
        };

        Thread bookings = new Thread(r1, "to_read");

        Thread ratings = new Thread(r2, "bookings");

        ratings.setPriority(Thread.MAX_PRIORITY);
        bookings.setPriority(Thread.MIN_PRIORITY);

        bookings.start();
        ratings.start();

        System.gc();

        try {
            bookings.join();
            ratings.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.gc();

//        loadUsersCSV(booksHash, usersHash);
//        loadRatingsCSV(booksHash,usersHash);



    }

    private static void loadBooksCSV(MyHash<Book, Book> booksHash, MyHash<Language, Language> languages, MyHash<Author,Author> authorsHash, MyHash<AuthorPublications, AuthorPublications> authorsPublicationsHash){
        Path pathToFile = Paths.get("..\\books.csv");
        MyList<String> authors = null;
        Book newBook = null;
        Book book = null;
        Author newAuthor = null;
        Author author = null;
        Integer yearOfPublication = null;
        Language language = null;
        AuthorPublications newPublication = null;
        AuthorPublications publication = null;



        try (BufferedReader br = Files.newBufferedReader(pathToFile, UTF_8)){

            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,8);

                try{
                    yearOfPublication = Integer.parseInt(arguments.get(3));
                } catch (NumberFormatException error){
                    yearOfPublication = null;
                }


                newBook = new Book(Long.parseLong(arguments.get(0)));
                book = booksHash.get(newBook);

                if (book == null){
                    book = new Book(Long.parseLong(arguments.get(0)), arguments.get(1), yearOfPublication, arguments.get(4), arguments.get(5), arguments.get(7));
                    booksHash.put(book, book);
                }

//                book = new Book(Long.parseLong(arguments.get(0)), arguments.get(1), yearOfPublication, arguments.get(4), arguments.get(5), arguments.get(7));
//                booksHash.put(book, book);

                language = findLanguage(languages, arguments.get(6));
                if (language != null) {
                    language.addBook(book);
                }

                authors = authorParser(arguments.get(2));

                for(String authorName : authors){
                    newAuthor = new Author(authorName);
                    author = authorsHash.get(newAuthor);

                    if (author == null){
                        author = newAuthor;
                        authorsHash.put(author, author);
                    }

                    book.addAuthor(author);

                    if (yearOfPublication != null) {
                        newPublication = new AuthorPublications(author, yearOfPublication);
                        publication = authorsPublicationsHash.get(newPublication);
                        if (publication == null) {
                            publication = newPublication;
                            authorsPublicationsHash.put(publication, publication);
                        }
                        publication.addBook(book);
                    }
                }


            }
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    private static void loadUsersCSV(MyHash<Book,Book> booksHash, MyHash<User,User> usersHash){  // Falta ratings
        Path pathToFile = Paths.get("..\\to_read.csv");
        User newUser = null;
        User user = null;
        Book newToRead = null;
        Long userId = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, UTF_8)) {

            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,2);
                userId = Long.parseLong(arguments.get(0));
                newUser =  new User(userId);
                user = usersHash.get(newUser);

                if (user == null){
                    user = newUser;
                    usersHash.put(user,user);
                }


                newToRead = booksHash.get(new Book(Long.parseLong(arguments.get(1))));
                if (newToRead == null){
                    throw new RuntimeException();
                }



                newToRead.addBooking(user);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }


    }

    private static void loadRatingsCSV(MyHash<Book, Book> booksHash, MyHash<User,User> usersHash){
        Path pathToFile = Paths.get("..\\ratings.csv");

        Book book = null;
        User user = null;
        User newUser = null;



        try (BufferedReader br = Files.newBufferedReader(pathToFile, UTF_8)) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,3);


                book = booksHash.get(new Book(Long.parseLong(arguments.get(1))));
                if (book == null){
                    throw new RuntimeException();
                }


                newUser = new User(Long.parseLong(arguments.get(0)));
                user = usersHash.get(newUser);
                if (user == null){
                    user = newUser;
                    usersHash.put(user, user);
                }


                book.addRatedBy(user);
                user.addRated(book,Integer.parseInt(arguments.get(2)));

            }
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    private static MyList<String> parser(String line, int args){

        MyList<String> attributes = new MyArrayListImpl<>(args);

        boolean inQuotes= false;

        StringBuilder builder = new StringBuilder();



        for (char c : line.toCharArray()) {
            switch (c) {
                case ',':
                    if (inQuotes) {
                        builder.append(c);
                    } else {
                        attributes.add(builder.toString());
                        builder = new StringBuilder();
                    }
                    break;

                case '\"':
                    inQuotes = !inQuotes;
                    break;

                default:
                    builder.append(c);
                    break;
            }
        }
        attributes.add(builder.toString());

        return attributes;
    }

    private static MyList<String> authorParser(String line){
        MyList<String> authors = new MyArrayListImpl<>(5);

        boolean ended= false;

        StringBuilder builder = new StringBuilder();

        for (char c : line.toCharArray()) {
            switch (c) {
                case ',':
                    authors.add(builder.toString());
                    ended = !ended;
                    builder = new StringBuilder();
                    break;
                case ' ':
                    if (!ended){
                        builder.append(c);
                    }
                    break;

                default:
                    if (ended){
                        ended = !ended;
                    }
                    builder.append(c);
                    break;
            }
        }
        if (builder!=null){
            authors.add(builder.toString());
        }

        return authors;
    }

    private static Language findLanguage(MyHash<Language,Language> languages, String lang){
        String languageCode = null;
        Language language = null;
        Language newLanguage = null;

        switch (lang){
            case "eng":
            case "en-US":
            case "en":
            case "en-GB":
            case "en-CA":

                languageCode = "English";
                break;
            case "nan":
                return null;
            case "ara":
                languageCode = "Arabian";
                break;
            case "fre":
                languageCode = "French";
                break;
            case "ind":
                languageCode = "Indonesian";
                break;
            case "spa":
                languageCode = "Spanish";
                break;
            case "ger":
                languageCode = "German";
                break;
            case "per":
                languageCode = "Persian";
                break;
            case "jpn":
                languageCode = "Japanese";
                break;
            case "por":
                languageCode = "Portuguese";
                break;
            case "pol":
                languageCode = "Polish";
                break;
            case "dan":
                languageCode = "Danish";
                break;
            case "ita":
                languageCode = "Italian";
                break;
            case "fil":
                languageCode = "Filipino";
                break;
            case "rus":
                languageCode = "Russian";
                break;
            case "mul":
                languageCode = "Multiple Languages";
                break;
            case "rum":
                languageCode = "Romanian";
                break;
            case "swe":
                languageCode = "Swedish";
                break;
            case "nl":
                languageCode = "Dutch";
                break;
            case "nor":
            languageCode = "Norwegian";
                break;
            case "tur":
                languageCode = "Turkish";
                break;
            case "vie":
                languageCode = "Vietnamese";
                break;
            default:
                return null;
        }


        newLanguage = new Language(languageCode);

        language = languages.get(newLanguage);

        if (language == null){
            language = newLanguage;
            languages.put(language, language);
        }

        return language;


    }


}
