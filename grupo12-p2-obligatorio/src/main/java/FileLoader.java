import com.sun.imageio.spi.RAFImageInputStreamSpi;
import entities.*;
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




    public static void loadData(MyList<Book> booksList, MyHash<Language,Language> languages, MyHash<AuthorNameHashKey, Author> authorsHash, MyHash<UserNameHashKey,User> usersHash, MyHash<Rating, Rating> ratingsHash){

        loadBooksCSV(booksList, languages, authorsHash);
        loadUsersCSV(booksList, usersHash);
        loadRatingsCSV(booksList,usersHash,ratingsHash);

    }

    private static void loadBooksCSV(MyList<Book> booksList, MyHash<Language, Language> languages, MyHash<AuthorNameHashKey,Author> authorsHash){
        Path pathToFile = Paths.get("..\\books.csv");
        MyList<String> authors = null;
        Book newBook = null;
        Author newAuthor = null;
        AuthorNameHashKey key = null;
        Integer yearOfPublication = null;
        Language language = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,8);

                try{
                    yearOfPublication = Integer.parseInt(arguments.get(3));
                } catch (NumberFormatException error){
                    yearOfPublication = null;
                }

                language = findLanguage(languages, arguments.get(6));

                newBook = new Book(Long.parseLong(arguments.get(0)), arguments.get(1), yearOfPublication, arguments.get(4), arguments.get(5), arguments.get(7));

                language.addBook(newBook);

                authors = authorParser(arguments.get(2));

                for(String author : authors){
                    key = new AuthorNameHashKey(author);
                    newAuthor = authorsHash.get(key);
                    if (newAuthor == null){
                        newAuthor = new Author(author);
                        authorsHash.put(key, newAuthor);
                    }
                    newBook.addAuthor(newAuthor);
                }

                booksList.add(newBook);

            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static void loadUsersCSV(MyList<Book> booksList, MyHash<UserNameHashKey,User> usersHash){  // Falta ratings
        Path pathToFile = Paths.get("..\\to_read.csv");
        User newUser = null;
        UserNameHashKey key = null;
        Book newToRead = null;
        Long userId = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,2);
                userId = Long.parseLong(arguments.get(0));
                key =  new UserNameHashKey(userId);
                newUser = usersHash.get(key);

                if (newUser == null){
                    newUser = new User(userId);
                    usersHash.put(key,newUser);
                }
                newToRead = booksList.get((Integer.parseInt(arguments.get(1)))-1);

                newToRead.addBooking(newUser);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static void loadRatingsCSV(MyList<Book> booksList, MyHash<UserNameHashKey,User> usersHash, MyHash<Rating, Rating> ratingsHash){
        Path pathToFile = Paths.get("..\\ratings.csv");

        Book book = null;
        User user = null;
        UserNameHashKey key = null;
        Rating newRating = null;
        Rating rating = null;

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                MyList<String> arguments = parser(line,3);

                try {
                    book = booksList.get(Integer.parseInt(arguments.get(1)) - 1);
                } catch (Exception error){
                    break;
                }
                key = new UserNameHashKey(Long.parseLong(arguments.get(0)));
                user = usersHash.get(key);

                if (user == null){
                    user = new User(Long.parseLong(arguments.get(0)));
                    usersHash.put(key, user);
                }

                newRating = new Rating(Integer.parseInt(arguments.get(2)), book);
                rating = ratingsHash.get(newRating);

                if (rating == null){
                    rating = newRating;
                    ratingsHash.put(rating,rating);
                }

                user.getRatings().add(rating);


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
                languageCode = "English";
                break;
            case "en-US":
                languageCode = "English";
                break;
            case "en-GB":
                languageCode = "English";
                break;
            case "en":
                languageCode = "English";
                break;
            case "en-CA":
                languageCode = "English";
                break;
            case "ara":
                languageCode = "Arabian";
                break;
            case "fre":
                languageCode = "French";
                break;
            case "ind":
                languageCode = "Indian";
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
                languageCode = "Portugese";
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
                languageCode = "Filipinian";
                break;
            case "rus":
                languageCode = "Englsh";
                break;
            case "mul":
                languageCode = "Muslims";
                break;
            case "rum":
                languageCode = "Rumanish";
                break;
            case "swe":
                languageCode = "Swdish";
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

                languageCode = "Otro";
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
