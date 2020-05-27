package uy.edu.um.prog2.adt.hash;

public class Cliente {
    private long cedula;
    private Integer cantidadProductos;
    private long pasaporte;
    private String name;

    public Cliente( long cedula, long pasaporte, String name) {
        this.cantidadProductos = 0;
        this.cedula = cedula;
        this.pasaporte = pasaporte;
        this.name = name;
    }

    public Cliente(int prod){
        cantidadProductos = prod;
    }
    public int getCantidadProductos() {
        return cantidadProductos;
    }
    public int eliminarProductos(int eliminar) {
        int devolver = 0;

        if (this.cantidadProductos >= eliminar){
            this.cantidadProductos = this.cantidadProductos - eliminar;
        } else {
            devolver = eliminar - this.cantidadProductos;
            this.cantidadProductos = 0;
        }

        return devolver;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public long getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(long pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return cantidadProductos.toString();
    }



    @Override
    public int hashCode() {

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cedula == cliente.cedula;
    }
}
