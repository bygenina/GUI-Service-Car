package com.company.exception;

public class NeverRentException extends Exception{

    public NeverRentException (){
        super("Osoba nigdy nie wynajmowala pomieszczenia");
    }
}
