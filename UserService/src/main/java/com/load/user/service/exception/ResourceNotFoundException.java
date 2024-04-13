package com.load.user.service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resouce Not Found on Server");
    }
    public ResourceNotFoundException(String meString){
        super(meString);
    }

}
