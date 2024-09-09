package com.ecom.ecom.exceptions;

public class ResourceExists extends RuntimeException {
    public ResourceExists(String message){
        super(message);
    }
    
}
