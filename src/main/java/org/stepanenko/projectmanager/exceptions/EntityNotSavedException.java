package org.stepanenko.projectmanager.exceptions;

public class EntityNotSavedException extends RuntimeException{

    public EntityNotSavedException(String message) {
        super(message);
    }
}
