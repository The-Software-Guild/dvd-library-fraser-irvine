package org.fraserirvine.dvdlibrary.service;

import org.fraserirvine.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.fraserirvine.dvdlibrary.dto.DVD;
import org.fraserirvine.dvdlibrary.dto.MPAA;

import java.util.List;

public interface DVDLibraryServiceLayer {

    void addDVD(DVD dvd) throws DVDLibraryDataValidationException, DVDLibraryPersistenceException, DVDLibraryDuplicateIdException;

    DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException;

    DVD editDVD(DVD dvd) throws DVDLibraryPersistenceException;

    List<DVD> listDVDs() throws DVDLibraryPersistenceException;

    DVD getSingleDVD(String dvdId) throws DVDLibraryPersistenceException;

    List<DVD> searchByTitle(String title) throws DVDLibraryPersistenceException;

    List<DVD> searchByNYears(int duration) throws DVDLibraryPersistenceException;

    List<DVD> searchByRating(MPAA rating) throws DVDLibraryPersistenceException;

    List<DVD> searchByDirector(String director) throws DVDLibraryPersistenceException;

    List<DVD> searchByStudio(String studio) throws DVDLibraryPersistenceException;

    double getAverageAge() throws DVDLibraryPersistenceException;

    DVD getNewestMovie() throws DVDLibraryPersistenceException;

    DVD getOldestMovie() throws DVDLibraryPersistenceException;

    void loadDVD(String path) throws DVDLibraryPersistenceException;

    String getPath();



}
