

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
                    System.out.println("El tiempo de carga de datos fue de: " + (System.currentTimeMillis() - time));
                    System.out.println("La cantidad de libros es de: " + queriesData.getBooksList().getSize());
                    System.out.println("La cantidad de autores es de: " + queriesData.getAuthorsHash().size());
                    break;
                case 2:
                    do {
                        consulta = Menu.menuConsultas();
                    } while (consulta != 6);

                case 3:
                    finishProgramme = !finishProgramme;
                    break;
            }

        }while (!finishProgramme);


    }

}
