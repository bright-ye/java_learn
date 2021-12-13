package com.mylearn.generics;

public class Plate<T>{

    private T item;

    public Plate() {
    }

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
