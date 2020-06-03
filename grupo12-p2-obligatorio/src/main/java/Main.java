import org.graalvm.compiler.core.phases.EconomyHighTier;

public class Main {

    public static void main(String[] args){

        Menu menu = new Menu();


        int option = menu.menuPrincipal();
        int consulta;

        switch (option){
            case 1:
                break;

            case 2:
                do {
                    consulta = menu.menuConsultas();
                } while(consulta != 6);

            case 3:
                break;
        }


    }

}
