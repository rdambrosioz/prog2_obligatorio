import entities.Book;
import entities.User;
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
                    System.out.println("La cantidad de libros es: " + queriesData.getBooksList().getSize());
                    System.out.println("La cantidad de idiomas es: " + queriesData.getLanguagesHash().size());
                    System.out.println("La cantidad de autores es: " + queriesData.getAuthorsHash().size());
                    System.out.println("La cantidad de usuarios es: " + queriesData.getUsersHash().size());
                    System.out.println("La cantidad de ratings es: " + queriesData.getRatingsHash().size());

                    break;
                case 2:
                    do {
                        consulta = Menu.menuConsultas();
                        switch (consulta){
                            case 1:
                                time = System.currentTimeMillis();
                                MyList<Book> top10Reserved = queriesData.topReserved();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (Book book : top10Reserved){
                                    System.out.println(book);
                                }
                                break;
                            case 3:
                                time = System.currentTimeMillis();
                                MyList<User> top10Raters = queriesData.topRaters();
                                System.out.println("El tiempo de demora de la consulta fue: " + (System.currentTimeMillis() - time) + " milisegundos" + "\n");
                                for (User user : top10Raters){
                                    System.out.println(user);
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
