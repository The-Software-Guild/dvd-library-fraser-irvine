package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String dvdId, DVD dvd);

    DVD removeDVD(String dvdId);

    DVD editDVD(DVD newDVD);

    List<DVD> listDVDs();

    DVD getSingleDVD(String dvdId);

    List<DVD> searchDVD(String title);

    void loadDVD(String path);




}
