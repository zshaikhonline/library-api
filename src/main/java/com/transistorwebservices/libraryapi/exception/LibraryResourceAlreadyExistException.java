package com.transistorwebservices.libraryapi.exception;

public class LibraryResourceAlreadyExistException extends Exception {

    private String traceId;

    public LibraryResourceAlreadyExistException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
