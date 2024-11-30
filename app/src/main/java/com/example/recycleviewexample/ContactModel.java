package com.example.recycleviewexample;

public class ContactModel {
    int img;
    String name,number;

    ContactModel(int img, String name, String number){
        this.img = img;
        this.name = name;
        this.number = number;
    }

    ContactModel(String name, String number){
        this.name = name;
        this.number = number;
    }
}
