package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    void addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException;

    DVD removeDVD(String dvdId) throws DVDLibraryDaoException;

    DVD editDVD(DVD newDVD) throws DVDLibraryDaoException;

    List<DVD> listDVDs() throws DVDLibraryDaoException;

    DVD getSingleDVD(String dvdId) throws DVDLibraryDaoException;

    List<DVD> searchDVD(String title) throws DVDLibraryDaoException;

    void loadDVD(String path);

    String getPath();

    void setPath(String path);



}
