import entities.AuthorPublications;
import entities.Language;
import entities.heapNodes.BookBookingsHeapNode;
import entities.heapNodes.BookRatedHeapNode;
import entities.heapNodes.UserAvgRatingHeapNode;
import uy.edu.um.prog2.adt.list.MyList;

public class Main {

    public static void main(String[] args){

        Queries queriesData = new Queries();

        boolean finishProgramme = false;
        long time = 0;
        int option;
        int consulta;

        do {
            option = Menu.menuPrincipal();
            switch (option) {
                case 1:
                    time = System.currentTimeMillis();
                    queriesData.loadData();

                    System.out.println("El tiempo de carga de datos fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");

                    break;
                case 2:
                    do {
                        consulta = Menu.menuConsultas();
                        switch (consulta){
                            case 1:
                                time = System.currentTimeMillis();
                                MyList<BookBookingsHeapNode> top10Reserved = queriesData.topReserved();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                if(top10Reserved != null) {
                                    for (BookBookingsHeapNode book : top10Reserved) {
                                        System.out.println(book);
                                    }
                                }
                                break;

                            case 2:
                                time = System.currentTimeMillis();
                                MyList<BookRatedHeapNode> top20booksEvaluated = queriesData.top20WithMoreEvaluations();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                if(top20booksEvaluated != null) {
                                    for (BookRatedHeapNode book : top20booksEvaluated) {
                                        System.out.println(book);
                                    }
                                }
                                break;

                            case 3:
                                time = System.currentTimeMillis();
                                MyList<UserAvgRatingHeapNode> top10Raters = queriesData.topRaters();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                if(top10Raters != null) {
                                    for (UserAvgRatingHeapNode user : top10Raters) {
                                        System.out.println(user);
                                    }
                                }
                                break;

                            case 4:
                                time = System.currentTimeMillis();
                                MyList<Language> top5Languages = queriesData.top5WithMoreReserves();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                if(top5Languages != null) {
                                    for (Language language : top5Languages) {
                                        System.out.println(language);
                                    }
                                }
                                break;

                            case 5:
                                time = System.currentTimeMillis();
                                MyList<AuthorPublications> top20Authors = queriesData.top20Author();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                if(top20Authors != null) {
                                    for (AuthorPublications author : top20Authors) {
                                        System.out.println(author);
                                    }
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
