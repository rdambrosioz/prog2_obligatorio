package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.hash.HashEntry;
import uy.edu.um.prog2.adt.hash.MyHash;

public class MyClosedHashImpl<K,T> implements MyHash<K,T> {



    private HashEntry<K,T>[] hashArray;
    private int size;
    private boolean linear;


    public MyClosedHashImpl(int size, boolean linear){
        this.hashArray = (HashEntry<K,T>[]) new HashEntry[getNextPrimeNumber(size-1)];
        this.linear = linear;
        this.size = 0;
    }




    @Override
    public void put(K key, T value) {

        if (((float) this.size + 1) / ((float) hashArray.length) > 0.5 ){
            this.reHashing();
        }


        int iterations = 0;
        int position = getPosition(key, iterations);

        while (!(hashArray[position] == null || hashArray[position].getKey().equals(key) || iterations  > hashArray.length || hashArray[position].isDeleted() ) ) {
            iterations++;
            position = getPosition(key,iterations);
        }

        if (iterations <= hashArray.length) {
            if (hashArray[position] != null && !hashArray[position].isDeleted()) {
                this.size--;
            }
            hashArray[position] = new HashEntry<>(key, value);
            this.size++;
        }




    }

    @Override
    public T remove(K key) {
        T valueToReturn = null;
        int iterations = 0;
        int position = getPosition(key, iterations);

        while (!(hashArray[position] == null || hashArray[position].getKey().equals(key) || iterations  > hashArray.length || hashArray[position].isDeleted())) {
            iterations++;
            position = getPosition(key, iterations);
        }

        if (iterations <= hashArray.length && !(hashArray[position] == null) && !(hashArray[position].isDeleted())){
            valueToReturn = hashArray[position].getValue();
            hashArray[position].setDeleted(true);
            this.size--;
        }
        return valueToReturn;
    }

    @Override
    public T get(K key) {
        T valueToReturn = null;
        int iterations = 0;
        int position = getPosition(key, iterations);
        while (!(hashArray[position] == null || hashArray[position].getKey().equals(key) || iterations  > hashArray.length)) {
            iterations++;
            position = getPosition(key, iterations);
        }

        if (iterations <= hashArray.length && !(hashArray[position].isDeleted())){
            valueToReturn = hashArray[position].getValue();
        }
        return valueToReturn;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int getPosition(K key, int iterations){
        int position = 0;
        if (linear){
            position = (key.hashCode() % hashArray.length + iterations) % hashArray.length;
        }else{
            position = (key.hashCode() % hashArray.length + (int) Math.pow(iterations,2)) % hashArray.length;
        }
        return position;
    }

    private void reHashing(){

        int newLength = getNextPrimeNumber(this.hashArray.length * 2);

        HashEntry<K,T>[] oldHash = hashArray;
        this.hashArray = (HashEntry<K,T>[]) new HashEntry[newLength];

        int updates = 0;

        for(int i = 0; i<oldHash.length;i++){
            if (oldHash[i] != null && !oldHash[i].isDeleted() ){
                put(oldHash[i].getKey(),oldHash[i].getValue());
                updates++;
            }
            if (updates >= size){
                break;
            }

        }
        this.size = updates;

    }

    private int getNextPrimeNumber(int number){
        int numberToReturn = number + 1;

        while(isNotPrime(numberToReturn)){
            numberToReturn++;
        }

        return numberToReturn;
    }

    private boolean isNotPrime(int number){

        boolean isNotPrime = false;

        for (int i=2; i<number-1; i++){
            if (number % i == 0){
                isNotPrime = true;
                break;
            }
        }

        return isNotPrime;
    }

}
