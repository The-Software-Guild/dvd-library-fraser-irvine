package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    void addDVD(String dvdId, DVD dvd) throws DVDLibraryPersistenceException;

    DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException;

    DVD editDVD(DVD newDVD) throws DVDLibraryPersistenceException;

    List<DVD> listDVDs() throws DVDLibraryPersistenceException;

    DVD getSingleDVD(String dvdId) throws DVDLibraryPersistenceException;

    List<DVD> searchDVD(String title) throws DVDLibraryPersistenceException;

    void loadDVD(String path);

    String getPath();

    void setPath(String path);



}
