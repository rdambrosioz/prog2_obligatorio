import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);;
    }

    public int menuPrincipal (){


        System.out.println("Seleccione la opción que desee:");
        System.out.println("\t1. Carga de datos");
        System.out.println("\t2. Ejecutar consultas");
        System.out.println("\t3. Salir");
        System.out.println();

        return scannOptionBetweenTwoInt(1,3);

    }


    public int menuConsultas(){

        System.out.println("\t1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.");
        System.out.println("\t2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.");
        System.out.println("\t3. Indicar el Top 10 de usuarios que realizaron mayor cantidad de evaluaciones a libros y ordenarlo por rating promedio descendente.");
        System.out.println("\t4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.");
        System.out.println("\t5. Indicar el Top 20 de autores que más publicaciones han hecho por año.");
        System.out.println("\t6. Salir.");
        System.out.println();

        return scannOptionBetweenTwoInt(1,6);

    }

    private int scannOptionBetweenTwoInt(int min, int max){

        int opcion = 0;
        boolean exit;

        do {
            exit = true;
            while (!scanner.hasNextInt()) {
                if (scanner.hasNext()){
                    scanner.next();
                    System.out.println("La opcion ingresada no es valida. \nIntente de nuevo");
                }
            }
            if (scanner.hasNext()){
                opcion = scanner.nextInt();
            }
            if (opcion < min || opcion > max) {
                System.out.println("La opcion ingresada no es valida. \nIntente de nuevo");
                exit = false;
            }
        } while (!exit);

        return opcion;

    }







}
