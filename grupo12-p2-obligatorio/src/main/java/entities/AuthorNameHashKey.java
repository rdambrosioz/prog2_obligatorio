package entities;

import java.util.Objects;

public class AuthorNameHashKey {

    private String name;

    public AuthorNameHashKey(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
//        int hashCode = 3;
//        for (int i=0;i < this.name.length(); i++){
//            hashCode = hashCode*31 + this.name.charAt(i);
//        }
//        return Math.abs(hashCode);
        return Math.abs(Objects.hash(this.name));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorNameHashKey that = (AuthorNameHashKey) o;
        return name.equals(that.name);
    }

}
