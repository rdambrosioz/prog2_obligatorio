package uy.edu.um.prog2.sorting;

public class Sorting {

    public static <T extends Comparable<T>> T[] bubbleSort(T[] elements){
        T temp;
        int length = elements.length;
        int interchanges;

        for (int i=0; i<elements.length; i++){
            interchanges = 0;

            for (int j=0; j<length-i-1; j++){
                if (elements[j].compareTo(elements[j+1]) > 0){
                    temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                    interchanges++;
                }

            }
            if (interchanges == 0){
                break;
            }
        }

        return elements;
    }
}
