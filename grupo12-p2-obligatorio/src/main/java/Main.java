

public class Main {

    public static void main(String[] args){


        int option = Menu.menuPrincipal();
        int consulta;

        switch (option){
            case 1:
                break;

            case 2:
                do {
                    consulta = Menu.menuConsultas();
                } while(consulta != 6);

            case 3:
                break;
        }


    }

}
