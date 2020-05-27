package uy.edu.um.prog2.adt.hash;

import java.util.Objects;

public class ClienteNameHashKey {
    private String name;

    public ClienteNameHashKey(String name) {
        this.name = name;
    }


    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i=0;i < this.name.length(); i++){
            hashCode += this.name.charAt(i);
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteNameHashKey that = (ClienteNameHashKey) o;
        return Objects.equals(name, that.name);
    }
}
