import entities.Author;
import entities.AuthorNameHashKey;
import entities.Book;
import entities.User;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import uy.edu.um.prog2.adt.hash.MyHash;
import uy.edu.um.prog2.adt.list.MyArrayListImpl;
import uy.edu.um.prog2.adt.list.MyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class FileLoader {




    public static void loadData(MyList<Book> booksList, MyHash<AuthorNameHashKey, Author> authorsHash){

        loadBooksCSV(booksList, authorsHash);


    }

    private static void loadBooksCSV(MyList<Book> booksList, MyHash<AuthorNameHashKey, Author> authorsHash){
        Path pathToFile = Paths.get("..\\books.csv");
        MyList<String> authors = null;
        Book newBook = null;
        Author newAuthor = null;
        Integer yearOfPublication = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,8);

                try{
                    yearOfPublication = Integer.parseInt(arguments.get(3));
                } catch (NumberFormatException error){
                    yearOfPublication = 0;
                }



                newBook = new Book(Long.parseLong(arguments.get(0)), arguments.get(1), yearOfPublication,
                        arguments.get(4), arguments.get(5), arguments.get(6), arguments.get(7));

                authors = authorParser(arguments.get(2));

                for(String author : authors){
                    newAuthor = authorsHash.get(new AuthorNameHashKey(author));
                    if (newAuthor == null){
                        newAuthor = new Author(author);
                        authorsHash.put(new AuthorNameHashKey(author), newAuthor);
                    }
                    newBook.addAuthor(newAuthor);
                }

                booksList.add(newBook);

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
    private static void loadUsersCSV(MyList<Book> booksList, MyHash<userid, User> authorsHash){  // Falta ratings
        Path pathToFile = Paths.get("..\\to_read.csv");
        MyList<String> reservedToRead = null;
        User newUser = null;
        Book newToRead = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                MyList<String> arguments = parser(line,8);
                newUser = new User(Long.parseLong(arguments.get(0)));
             //   users = authorUsers(arguments.get(2));
                usersHash.put(newUser);
                newToRead = booksList.get(arguments.get(1));
                newUser.reservedToRead.add(newToRead);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }





}
