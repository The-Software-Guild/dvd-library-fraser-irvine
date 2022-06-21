package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String dvdId, DVD dvd);

    DVD removeDVD(String dvdId);

    DVD editDVD(DVD newDVD);

    List<DVD> listDVDs();

    DVD getSingleDVD(String dvdId);

    DVD searchDVD(String title);

    DVD loadDVD(String path);




}