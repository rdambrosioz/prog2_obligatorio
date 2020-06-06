package uy.edu.um.prog2.adt.exeptions;

public class ListOutOfIndex extends RuntimeException {

    private int indice;

    public ListOutOfIndex(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public String toString(){
        return "La lista solo tiene " + indice + " elementos";
    }
}
