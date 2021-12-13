package com.mylearn.generics;

public class GenericsMain {

    public static void main(String[] args) {
        // 编译报错
        // Plate<Fruit> plate = new Plate<Apple>(new Apple());

        Plate<? extends Fruit> plate = new Plate<Apple>(new Apple());

        // 编译报错
        // plate.setItem(new Apple());

        Fruit fruit = plate.getItem();
        Apple apple = (Apple) plate.getItem();


        Plate<? super Fruit> plate1 = new Plate<>(new Apple());
        plate1.setItem(new Apple());
        plate1.setItem(new GreenApple());

     }
}
