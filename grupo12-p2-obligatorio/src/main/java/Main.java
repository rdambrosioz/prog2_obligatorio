import entities.AuthorPublications;
import entities.Book;
import entities.Language;
import entities.User;
import entities.nodes.BookBookingsHeapNode;
import entities.nodes.BookRatedHeapNode;
import entities.nodes.UserAvgRatingNode;
import uy.edu.um.prog2.adt.hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.hash.MyOpenedHashImpl;
import uy.edu.um.prog2.adt.list.MyList;

public class Main {

    public static void main(String[] args){

        Queries queriesData = null;

        boolean finishProgramme = false;
        long time = 0;
        int option;
        int consulta;

        do {
            option = Menu.menuPrincipal();
            switch (option) {
                case 1:
                    time = System.currentTimeMillis();
                    queriesData = new Queries();
                    queriesData.loadData();

                    System.out.println("El tiempo de carga de datos fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                    System.out.println("La cantidad de libros es: " + queriesData.getBooksList().size());
                    System.out.println("La cantidad de idiomas es: " + queriesData.getLanguagesHash().size());
                    System.out.println("La cantidad de autores es: " + queriesData.getAuthorsHash().size());
                    System.out.println("La cantidad de autores por año es: " + queriesData.getAuthorsPublicationsHash().size());
                    System.out.println("La cantidad de usuarios es: " + queriesData.getUsersHash().size());

                    break;
                case 2:
                    do {
                        consulta = Menu.menuConsultas();
                        switch (consulta){
                            case 1:
                                time = System.currentTimeMillis();
                                MyList<BookBookingsHeapNode> top10Reserved = queriesData.topReserved();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (BookBookingsHeapNode book : top10Reserved){
                                    System.out.println(book);
                                }
                                break;

                            case 2:
                                time = System.currentTimeMillis();
                                MyList<BookRatedHeapNode> top20booksEvaluated = queriesData.top20WithMoreEvaluations();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (BookRatedHeapNode book : top20booksEvaluated){
                                    System.out.println(book);
                                }
                                break;

                            case 3:
                                time = System.currentTimeMillis();
                                MyList<UserAvgRatingNode> top10Raters = queriesData.topRaters();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (UserAvgRatingNode user : top10Raters){
                                    System.out.println(user);
                                }
                                break;

                            case 4:
                                time = System.currentTimeMillis();
                                MyList<Language> top5Languages = queriesData.top5WithMoreReserves();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (Language language : top5Languages){
                                    System.out.println(language);
                                }
                                break;

                            case 5:
                                time = System.currentTimeMillis();
                                MyList<AuthorPublications> top20Authors = queriesData.top20Author();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (AuthorPublications author : top20Authors){
                                    System.out.println(author);
                                }
                                break;
                        }
                    } while (consulta != 6);

                case 3:
                    finishProgramme = !finishProgramme;
                    break;
            }

        }while (!finishProgramme);


    }

}
