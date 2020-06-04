import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

public class FileLoader {







    private static MyList<String> parser(String line){

        MyList<String> attributes = new MyLinkedListImpl<>();

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



}
