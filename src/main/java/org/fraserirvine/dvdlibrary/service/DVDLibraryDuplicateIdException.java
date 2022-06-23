package org.fraserirvine.dvdlibrary.service;

public class DVDLibraryDuplicateIdException extends Exception {

    public DVDLibraryDuplicateIdException(String message) {
        super(message);
    }

    public DVDLibraryDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }

}
