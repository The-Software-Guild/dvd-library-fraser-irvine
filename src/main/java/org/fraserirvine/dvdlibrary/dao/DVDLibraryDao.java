package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;
import org.fraserirvine.dvdlibrary.dto.MPAA;
import org.fraserirvine.dvdlibrary.service.DVDLibraryDataValidationException;

import java.util.List;

public interface DVDLibraryDao {

    void addDVD(String dvdId, DVD dvd) throws DVDLibraryPersistenceException;

    DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException;

    DVD editDVD(DVD newDVD) throws DVDLibraryPersistenceException;

    List<DVD> listDVDs() throws DVDLibraryPersistenceException;

    DVD getSingleDVD(String dvdId) throws DVDLibraryPersistenceException;

    List<DVD> searchByTitle(String title) throws DVDLibraryPersistenceException;

    List<DVD> searchByYear(int duration) throws DVDLibraryPersistenceException;

    List<DVD> searchByRating(MPAA rating) throws DVDLibraryPersistenceException;

    List<DVD> searchByDirector(String director) throws DVDLibraryPersistenceException;

    List<DVD> searchByStudio(String studio) throws DVDLibraryPersistenceException;

    double getAverageAge() throws DVDLibraryPersistenceException;

    DVD getNewestMovie() throws DVDLibraryPersistenceException;

    DVD getOldestMovie() throws DVDLibraryPersistenceException;

    void loadDVD(String path);

    String getPath();

    void setPath(String path);



}
