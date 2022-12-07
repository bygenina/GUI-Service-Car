package com.company.exception;

public class ProblematicTenantException extends Exception{

    public ProblematicTenantException (String errorMessage){
        super(errorMessage);
    }
}
