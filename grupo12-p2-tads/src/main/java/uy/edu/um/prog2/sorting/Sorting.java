package uy.edu.um.prog2.sorting;

import uy.edu.um.prog2.sorting.exceptions.SortingOutOfIndex;

/**
 * This is a class containing different types of sorting algorithms implementations. All of
 * them order arrays of values, and some of them can order only a number of them in stead of sortigng them all
 */


public class Sorting {

    /**
     * This method uses the BubbleSort algorithm to sort a specific number of elements of an array.
     *
     * @param elements the array of elements to be sorted
     * @param orderedElements the number of elements desired to sort. Must be smaller than array length
     * @param <T> type of the element inputs
     * @return  returns the same array of elements with the orderedElements number of elements sorted
     * @throws SortingOutOfIndex if expected to order more elements than the array length
     */
    public static <T extends Comparable<T>> T[] bubbleSort(T[] elements, int orderedElements){
        T temp;
        int length = elements.length;
        int interchanges;

        if (length < orderedElements){
            throw new SortingOutOfIndex();
        }

        for (int i=0; i<orderedElements; i++){
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


    /**
     * This method uses the BubbleSort algorithm to sort a all the elements of an array.
     * Therefore, will call the method above (bubbleSort) with orderedElements as the array length
     *
     * @param elements the array of elements to be sorted
     * @param <T> type of the element inputs
     * @return returns the same array of elements with all the elements sorted
     */
    public static <T extends Comparable<T>> T[] bubbleSort(T[] elements){
        return bubbleSort(elements, elements.length);
    }


    /**
     * This method uses the HeapSort algorithm to sort a specific number of elements of an array.
     *
     * @param elements the array of elements to be sorted
     * @param orderedElements the number of elements desired to sort. Must be smaller than array length
     * @param <T> type of the element inputs
     * @return returns the same array of elements with the orderedElements number of elements sorted
     * @throws SortingOutOfIndex if expected to order more elements than the array length
     */
    public static <T extends Comparable<T>> T[] heapSort(T[] elements, int orderedElements){
        int valuePosition;
        int newPosition;
        T value;

        if (elements.length < orderedElements){
            throw new SortingOutOfIndex();
        }

        //Reordering vector to represent a vector of a Binary Heap Structure
        for(int i = 1; i < elements.length ; i++){
            valuePosition = i;
            value = elements[i];
            while (valuePosition != 0 && value.compareTo(elements[getHeapFatherPosition(valuePosition)]) > 0) {
                T tempValue = elements[getHeapFatherPosition(valuePosition)];
                elements[getHeapFatherPosition(valuePosition)] = value;
                elements[valuePosition] = tempValue;
                valuePosition = getHeapFatherPosition(valuePosition);
            }
        }

        //Make the number of pseudoDeletes necessary to reorder the number of ordered elements desired
        for (int i = elements.length-1; i >= elements.length-orderedElements;i--){
            value = elements[i];
            elements[i] = elements[0];
            elements[0] = value;
            valuePosition = 0;
            newPosition = valuePosition;
            value = elements[0];
            while(true) {

                if (getHeapChildPosition(valuePosition) < i && value.compareTo(elements[getHeapChildPosition(valuePosition)]) < 0){
                    newPosition = getHeapChildPosition(valuePosition);
                    value = elements[newPosition];
                }
                if (getHeapChildPosition(valuePosition)+1 < i && value.compareTo(elements[getHeapChildPosition(valuePosition)+1]) < 0){
                    newPosition = getHeapChildPosition(valuePosition)+1;
                }

                if (valuePosition != newPosition ){
                    value = elements[valuePosition];
                    elements[valuePosition] = elements[newPosition];
                    elements[newPosition] = value;
                    valuePosition = newPosition;
                } else{
                    break;
                }
            }
        }

        if (orderedElements == elements.length){
            if(elements[0].compareTo(elements[1]) > 0){
                value = elements[0];
                elements[0] = elements[1];
                elements[1] = value;
            }
        }


        return elements;

    }


    /**
     * This method uses the HeapSort algorithm to sort all the elements of an array.
     * Therefore, will call the method above (heapSort) with orderedElements as the array length
     *
     * @param elements the array of elements to be sorted
     * @param <T> type of the element inputs
     * @return returns the same array of elements with all the elements sorted
     */
    public static <T extends Comparable<T>> T[] heapSort(T[] elements){
        return heapSort(elements, elements.length);
    }


    /**
     * Method user by heapSort algorithm to calculate the father position of an entry
     *
     * @param childPosition the position of the children entry in the array
     * @return the position of the father in the array
     */
    private static int getHeapFatherPosition(int childPosition){
        return ((childPosition-1)/2);
    }

    /**
     * Method user by heapSort algorithm to calculate the first son of an entry
     *
     * @param fatherPosition the position of the father entry in the array
     * @return the position of the first child in the array
     */
    private static  int getHeapChildPosition(int fatherPosition){
        return (2*fatherPosition +1);
    }

}
